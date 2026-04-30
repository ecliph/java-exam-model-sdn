/**
 * RÔLE DU FICHIER : Transporter l'objet qui a posé problème.
 * 
 * MODÈLE D'ANNALE : "L'exception doit permettre de récupérer le drone fautif".
 */
public class DroneInvalideException extends GestionFlotteException {

    // On stocke l'objet drone directement dans l'exception !
    private final IDrone droneRejete;

    public DroneInvalideException(IDrone drone) {
        super("Le drone " + (drone != null ? drone.getNom() : "NULL") + " est invalide.");
        this.droneRejete = drone;
    }

    /**
     * Permet au développeur de faire : 
     * catch (DroneInvalideException e) { 
     *    IDrone d = e.getDroneRejete(); 
     * }
     */
    public IDrone getDroneRejete() {
        return droneRejete;
    }
}
