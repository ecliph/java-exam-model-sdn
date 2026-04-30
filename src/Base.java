/**
 * --- LEÇON 1 : LA CLASSE DE BASE (LES DONNÉES) ---
 * 
 * RÔLE DU FICHIER : 
 * Ce fichier sert à créer des objets "simples" qui contiennent juste des informations.
 * Dans un examen, c'est l'équivalent de la classe "Chambre", "Client", ou "Livre".
 * 
 * CONSEIL EXAMEN : 
 * Commencez toujours par ces classes. C'est le plus facile et ça rapporte des points rapidement.
 */
public class Base {

    /**
     * LES ATTRIBUTS (Le "Savoir" de l'objet)
     * On met 'private' pour que personne ne puisse modifier les données n'importe comment.
     * C'est ce qu'on appelle l'ENCAPSULATION (on cache les détails).
     */
    private String coordGPS;      // Exemple : "48.8, 2.3"
    private double volumeHangar; // Exemple : 500.0 (en m3)

    /**
     * LE CONSTRUCTEUR VIDE
     * C'est comme une boîte vide. On la crée, et on remplira les infos plus tard.
     * Indispensable si on veut pouvoir faire : Base b = new Base();
     */
    public Base() {
        // Il ne fait rien, mais il doit être là si on veut un objet vide.
    }

    /**
     * LE CONSTRUCTEUR AVEC PARAMÈTRES
     * C'est comme une boîte qu'on remplit au moment même où on la fabrique.
     * 'this.coordGPS' désigne l'attribut de la classe.
     * 'coordGPS' désigne la valeur reçue en argument.
     */
    public Base(String coordGPS, double volumeHangar) {
        this.coordGPS = coordGPS;
        this.volumeHangar = volumeHangar;
    }

    /**
     * LES GETTERS (Pour "Lire")
     * Puisque les attributs sont 'private', on a besoin de ces méthodes 'public' 
     * pour que les autres classes puissent VOIR les valeurs.
     */
    public String getCoordGPS() { 
        return coordGPS; 
    }

    public double getVolumeHangar() { 
        return volumeHangar; 
    }

    /**
     * LES SETTERS (Pour "Modifier")
     * On les utilise pour CHANGER la valeur d'un attribut après la création de l'objet.
     */
    public void setCoordGPS(String coordGPS) { 
        this.coordGPS = coordGPS; 
    }

    public void setVolumeHangar(double volumeHangar) { 
        this.volumeHangar = volumeHangar; 
    }

    /**
     * MÉTHODE toString()
     * Très utile pour le debug. Si vous faites System.out.println(maBase), 
     * Java affichera ce que vous écrivez ici au lieu d'un code bizarre.
     */
    @Override
    public String toString() {
        return "Base [Lieu=" + coordGPS + ", Taille=" + volumeHangar + " m3]";
    }
}
