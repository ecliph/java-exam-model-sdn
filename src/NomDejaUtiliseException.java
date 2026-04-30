/**
 * RÔLE DU FICHIER : Signal quand un NOM est déjà pris par un autre drone.
 */
public class NomDejaUtiliseException extends GestionFlotteException {
    public NomDejaUtiliseException(String message) {
        super(message);
    }
}
