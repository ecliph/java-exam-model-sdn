/**
 * RÔLE DU FICHIER : Signal quand un OBJET drone est déjà dans la liste.
 */
public class DroneDejaPresentException extends GestionFlotteException {
    public DroneDejaPresentException(String message) {
        super(message);
    }
}
