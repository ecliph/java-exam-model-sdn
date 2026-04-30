/**
 * --- LEÇON 7 : LA SÉCURITÉ (LES EXCEPTIONS) ---
 * 
 * RÔLE DU FICHIER : 
 * Une Exception est un signal d'alarme. Quand un problème survient, 
 * on "lance" (throw) une exception pour arrêter le programme ou le gérer.
 * 
 * 'extends Exception' signifie que c'est une exception "contrôlée" : 
 * le programmeur est OBLIGÉ de mettre un try/catch.
 */
public class GestionFlotteException extends Exception {

    /**
     * CONSTRUCTEUR SIMPLE
     * Par défaut, on donne un message général.
     */
    public GestionFlotteException() {
        super("Erreur dans la gestion de la flotte");
    }

    /**
     * CONSTRUCTEUR AVEC MESSAGE
     * Permet de préciser quel est le problème exact.
     */
    public GestionFlotteException(String message) {
        super(message);
    }
}
