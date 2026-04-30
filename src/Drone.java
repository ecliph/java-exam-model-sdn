/**
 * RÔLE DU FICHIER : Servir de base commune à tous les drones.
 * MODÈLE D'ANNALE : Souvent une question comme "Créer la classe abstraite...".
 *
 * NOTION : Classe abstraite, implements, Comparable.
 */
public abstract class Drone implements IDrone {

    // Attributs communs à tous les drones
    protected String nom;
    protected double capaciteBatterie;
    protected Base base;
    protected TypeDrone type;

    /**
     * CONSTRUCTEUR de la classe abstraite.
     * On ne peut pas faire 'new Drone(...)', mais ses enfants utiliseront 'super(...)'.
     */
    public Drone(String nom, double capaciteBatterie, Base base, TypeDrone type) {
        this.nom = nom;
        this.capaciteBatterie = capaciteBatterie;
        this.base = base;
        this.type = type;
    }

    // Implémentation des getters/setters demandés par l'interface IDrone
    @Override public String getNom() { return nom; }
    @Override public void setNom(String nom) { this.nom = nom; }
    @Override public double getCapaciteBatterie() { return capaciteBatterie; }
    @Override public void setCapaciteBatterie(double c) { this.capaciteBatterie = c; }
    @Override public Base getBase() { return base; }
    @Override public void setBase(Base b) { this.base = b; }
    @Override public TypeDrone getType() { return type; }

    /**
     * COMPARABLE : Indique comment trier deux drones.
     * Ici, on trie par NOM (ordre alphabétique).
     */
    @Override
    public int compareTo(IDrone autre) {
        // compareTo sur String retourne :
        // < 0 si this est avant, 0 si égal, > 0 si this est après.
        return this.nom.compareTo(autre.getNom());
    }

    /**
     * EQUALS : Définit quand deux drones sont "les mêmes".
     * Très important pour les HashMap et HashSet.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Même adresse mémoire ? Oui
        if (!(o instanceof IDrone)) return false; // Pas un drone ? Non
        IDrone autre = (IDrone) o;
        return this.nom.equals(autre.getNom()); // Même nom ? Oui (Règle d'annale classique)
    }

    /**
     * HASHCODE : Doit être redéfini si equals l'est.
     */
    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }

    @Override
    public String toString() {
        return "Drone[" + type + "] " + nom + " (" + capaciteBatterie + " mAh)";
    }
}
