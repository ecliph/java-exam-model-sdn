/**
 * --- LEÇON 4 : LA CLASSE CONCRÈTE (LA SPÉCIALISATION) ---
 * 
 * RÔLE DU FICHIER : 
 * C'est ici qu'on crée les vrais objets utilisables. 
 * 'extends Drone' signifie que cette classe hérite de tout ce qui est dans 'Drone'.
 */
public class DroneTransport extends Drone {

    // Attribut spécifique : Seul le drone de transport a un nombre de colis.
    private int nbColisMax;

    // CONSTANTE (static final) : Une valeur qui ne change jamais et qui est partagée.
    private static final double SEUIL_EFFICIENCE = 500.0;

    /**
     * LE CONSTRUCTEUR
     * @param nom Reçu et envoyé à la maman (Drone)
     * @param capaciteBatterie Reçu et envoyé à la maman (Drone)
     * @param base Reçu et envoyé à la maman (Drone)
     * @param nbColisMax Gardé ici pour cet objet précis.
     */
    public DroneTransport(String nom, double capaciteBatterie, Base base, int nbColisMax) {
        // 'super' appelle le constructeur de la classe Drone.
        // On précise manuellement le type 'TypeDrone.TRANSPORT'.
        super(nom, capaciteBatterie, base, TypeDrone.TRANSPORT);
        this.nbColisMax = nbColisMax;
    }

    // Getter/Setter spécifique
    public int getNbColisMax() { return nbColisMax; }
    public void setNbColisMax(int n) { this.nbColisMax = n; }

    /**
     * POLYMORPHISME (isEfficient)
     * Chaque enfant de Drone peut avoir sa propre définition de ce qu'est un drone "efficace".
     */
    @Override
    public boolean isEfficient() {
        // Éviter la division par zéro ! (Bonne pratique examen)
        if (nbColisMax <= 0) return false;
        
        // Un drone de transport est efficace si sa batterie par colis est faible.
        return (capaciteBatterie / nbColisMax) <= SEUIL_EFFICIENCE;
    }
}
