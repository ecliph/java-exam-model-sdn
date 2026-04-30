/**
 * RÔLE DU FICHIER : Représenter une classe utilitaire simple.
 * MODÈLE D'ANNALE : Correspond aux classes comme "Chambre", "Gare", "Etape".
 *
 * NOTION : Classe simple, attributs privés, constructeurs, getters/setters, toString.
 */
public class Base {

    // Attributs : Ce que l'objet "connaît" d'elle-même.
    // private = encapsulation (protection des données)
    private String coordGPS;
    private double volumeHangar;

    /**
     * CONSTRUCTEUR VIDE (par défaut)
     * Règle d'examen : Toujours utile d'en avoir un si on ne veut pas forcer les paramètres.
     */
    public Base() {
    }

    /**
     * CONSTRUCTEUR PARAMÉTRÉ
     * Sert à initialiser l'objet dès sa création avec 'new'.
     * @param coordGPS La position géographique.
     * @param volumeHangar La taille du hangar.
     */
    public Base(String coordGPS, double volumeHangar) {
        this.coordGPS = coordGPS;
        this.volumeHangar = volumeHangar;
    }

    // GETTERS : Pour "lire" la valeur d'un attribut privé.
    public String getCoordGPS() { return coordGPS; }
    public double getVolumeHangar() { return volumeHangar; }

    // SETTERS : Pour "modifier" la valeur d'un attribut privé.
    public void setCoordGPS(String coordGPS) { this.coordGPS = coordGPS; }
    public void setVolumeHangar(double volumeHangar) { this.volumeHangar = volumeHangar; }

    /**
     * TOSTRING : Comment l'objet s'affiche dans la console (ex: System.out.println).
     */
    @Override
    public String toString() {
        return "Base{" + "coordGPS='" + coordGPS + '\'' + ", volumeHangar=" + volumeHangar + '}';
    }
}
