/**
 * RÔLE DU FICHIER : Représenter un type précis de drone.
 * MODÈLE D'ANNALE : Correspond à "ChambreDouble", "GareTGV", etc.
 *
 * NOTION : Héritage (extends), super(), static final (constante), @Override.
 */
public class DroneTransport extends Drone {

    private int nbColisMax;

    // Constante : Valeur fixe partagée par tous les DroneTransport
    private static final double MAX_MAH_PAR_COLIS = 500.0;

    public DroneTransport(String nom, double capaciteBatterie, Base base, int nbColisMax) {
        // super(...) appelle le constructeur de la classe mère (Drone)
        super(nom, capaciteBatterie, base, TypeDrone.TRANSPORT);
        this.nbColisMax = nbColisMax;
    }

    public int getNbColisMax() { return nbColisMax; }
    public void setNbColisMax(int n) { this.nbColisMax = n; }

    /**
     * REDÉFINITION : Chaque drone a sa propre règle d'efficience.
     */
    @Override
    public boolean isEfficient() {
        if (nbColisMax <= 0) return false;
        // Efficient si la batterie par colis n'est pas trop gourmande
        return (capaciteBatterie / nbColisMax) <= MAX_MAH_PAR_COLIS;
    }
}
