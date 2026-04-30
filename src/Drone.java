/**
 * --- LEÇON 3 : LA CLASSE ABSTRAITE (L'HÉRITAGE) ---
 * 
 * RÔLE DU FICHIER : 
 * Une classe ABSTRAITE est un moule incomplet. 
 * On ne peut pas faire 'new Drone()' car un "drone" en général n'existe pas, 
 * il faut qu'il soit soit de "Transport", soit "Médical", etc.
 * 
 * POURQUOI FAIRE ÇA ? 
 * Pour éviter de recopier 10 fois le même code (nom, batterie, base) 
 * dans chaque type de drone. On regroupe tout ici !
 */
public abstract class Drone implements IDrone {

    /**
     * ATTRIBUTS 'protected'
     * Contrairement à 'private', 'protected' permet aux enfants (DroneTransport, etc.) 
     * d'accéder directement à ces variables. C'est très pratique pour l'héritage.
     */
    protected String nom;
    protected double capaciteBatterie;
    protected Base base;
    protected TypeDrone type;

    /**
     * LE CONSTRUCTEUR DE LA CLASSE MÈRE
     * Même si on ne peut pas créer un 'Drone' directement, ce constructeur 
     * sera appelé par les classes filles via 'super(...)'.
     */
    public Drone(String nom, double capaciteBatterie, Base base, TypeDrone type) {
        this.nom = nom;
        this.capaciteBatterie = capaciteBatterie;
        this.base = base;
        this.type = type;
    }

    /**
     * IMPLÉMENTATION DES MÉTHODES DE L'INTERFACE
     * Ici, on code les getters/setters une fois pour toutes. 
     * Les enfants n'auront pas besoin de les réécrire.
     */
    @Override public String getNom() { return nom; }
    @Override public void setNom(String nom) { this.nom = nom; }
    @Override public double getCapaciteBatterie() { return capaciteBatterie; }
    @Override public void setCapaciteBatterie(double c) { this.capaciteBatterie = c; }
    @Override public Base getBase() { return base; }
    @Override public void setBase(Base b) { this.base = b; }
    @Override public TypeDrone getType() { return type; }

    /**
     * NOTION : COMPARABLE (Le Tri)
     * Pour que Java sache trier une liste de drones, on doit lui dire COMMENT comparer.
     * Ici, on compare par NOM (ordre alphabétique).
     */
    @Override
    public int compareTo(IDrone autre) {
        // On utilise le compareTo de la classe String (déjà codé par Java).
        return this.nom.compareTo(autre.getNom());
    }

    /**
     * NOTION : equals() (L'Égalité)
     * Par défaut, Java compare les adresses mémoire. 
     * En examen, on veut souvent comparer par CONTENU (ex: si les noms sont les mêmes).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // C'est le même objet en mémoire ?
        if (!(o instanceof IDrone)) return false; // C'est même pas un drone ?
        IDrone autre = (IDrone) o;
        return this.nom.equals(autre.getNom()); // Ont-ils le même nom ?
    }

    /**
     * hashcode()
     * RÈGLE D'OR : Si vous redéfinissez equals(), vous DEVEZ redéfinir hashCode().
     * Utilisez simplement l'attribut qui sert à la comparaison.
     */
    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }

    @Override
    public String toString() {
        return "Drone [" + type + "] Nom=" + nom + " (" + capaciteBatterie + " mAh)";
    }
}
