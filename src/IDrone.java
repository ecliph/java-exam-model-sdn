import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RÔLE DU FICHIER : Définir le contrat (ce que les drones savent faire).
 * MODÈLE D'ANNALE : "Écrire l'interface IHotel", "Compléter l'interface...".
 *
 * NOTION : Interface, méthodes abstraites, default, static.
 */
public interface IDrone extends Comparable<IDrone> {

    // Méthodes abstraites (sans corps) : Les classes devront les coder.
    String getNom();
    void setNom(String nom);
    double getCapaciteBatterie();
    void setCapaciteBatterie(double capaciteBatterie);
    Base getBase();
    void setBase(Base base);
    TypeDrone getType();
    boolean isEfficient();

    /**
     * MÉTHODE DEFAULT : A déjà un code.
     * Les classes qui implémentent IDrone en héritent automatiquement.
     */
    default double coutMaintenance() {
        // Utilise la méthode statique de TarifsOfficiels
        return getCapaciteBatterie() * TarifsOfficiels.getPrixParMah();
    }

    /**
     * MÉTHODE STATIC : Appartient à l'interface elle-même.
     * S'appelle par IDrone.dronesEfficients(...)
     * @param drones Une collection de drones (ou de types qui héritent de IDrone : ? extends)
     */
    static List<IDrone> dronesEfficients(Collection<? extends IDrone> drones) {
        return drones.stream()
                     .filter(d -> d.isEfficient()) // Garde ceux qui sont efficients
                     .collect(Collectors.toList()); // Transforme en liste
    }
}
