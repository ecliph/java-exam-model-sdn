/**
 * RÔLE DU FICHIER : Classe mère de toutes nos erreurs.
 * MODÈLE D'ANNALE : Souvent demandé pour créer une hiérarchie d'erreurs.
 */
public class GestionFlotteException extends Exception {

    public GestionFlotteException() {
        super("Erreur dans la gestion de la flotte");
    }

    public GestionFlotteException(String message) {
        super(message);
    }
}
