/**
 * RÔLE DU FICHIER : Un autre type de drone pour montrer le polymorphisme.
 * MODÈLE D'ANNALE : Montre qu'on peut avoir plusieurs classes filles.
 *
 * NOTION : Polymorphisme.
 */
public class DroneMedical extends Drone {

    private boolean temperatureControlee;

    public DroneMedical(String nom, double capaciteBatterie, Base base, boolean temp) {
        super(nom, capaciteBatterie, base, TypeDrone.MEDICAL);
        this.temperatureControlee = temp;
    }

    public boolean isTemperatureControlee() { return temperatureControlee; }
    public void setTemperatureControlee(boolean t) { this.temperatureControlee = t; }

    @Override
    public boolean isEfficient() {
        // Un drone médical est efficient s'il a le contrôle de température
        return capaciteBatterie > 0 && temperatureControlee;
    }
}
