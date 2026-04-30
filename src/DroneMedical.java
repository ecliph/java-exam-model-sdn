/**
 * --- LEÇON 4 (BIS) : UNE AUTRE SPÉCIALISATION ---
 * 
 * RÔLE DU FICHIER : 
 * Montre qu'on peut avoir plusieurs "enfants" différents pour une même classe mère.
 */
public class DroneMedical extends Drone {

    private boolean temperatureControlee;

    public DroneMedical(String nom, double capaciteBatterie, Base base, boolean temp) {
        // Appelle le constructeur de Drone avec le type MEDICAL
        super(nom, capaciteBatterie, base, TypeDrone.MEDICAL);
        this.temperatureControlee = temp;
    }

    public boolean isTemperatureControlee() { return temperatureControlee; }
    public void setTemperatureControlee(boolean t) { this.temperatureControlee = t; }

    /**
     * POLYMORPHISME : L'efficience médicale est différente du transport.
     */
    @Override
    public boolean isEfficient() {
        // Un drone médical est efficace s'il a une batterie > 0 ET le contrôle de température.
        return capaciteBatterie > 0 && temperatureControlee;
    }
}
