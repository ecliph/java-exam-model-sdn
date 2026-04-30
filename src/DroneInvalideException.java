/**
 * RÔLE DU FICHIER : Exception qui transporte un objet.
 * MODÈLE D'ANNALE : "L'exception doit contenir la chambre qui a posé problème".
 */
public class DroneInvalideException extends GestionFlotteException {

    private final IDrone droneRejete;

    public DroneInvalideException(IDrone drone) {
        super("Le drone est invalide ou non efficient.");
        this.droneRejete = drone;
    }

    public IDrone getDroneRejete() {
        return droneRejete;
    }
}
