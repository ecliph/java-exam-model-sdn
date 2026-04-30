import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- LEÇON 2 : L'INTERFACE (LE CONTRAT) ---
 * 
 * RÔLE DU FICHIER : 
 * Une interface est une LISTE DE RÈGLES. Elle dit ce qu'un objet DOIT savoir faire, 
 * mais elle ne dit pas COMMENT le faire.
 * C'est comme une fiche technique ou un menu au restaurant.
 * 
 * CONSEIL EXAMEN : 
 * On vous demandera souvent "Écrire l'interface IHotel" ou "Compléter l'interface...".
 * Rappelez-vous : une interface ne contient normalement pas de variables (attributs), 
 * seulement des méthodes sans corps (sauf cas particuliers).
 */
public interface IDrone extends Comparable<IDrone> {

    /**
     * MÉTHODES ABSTRAITES
     * Ce sont des promesses. Les classes qui utiliseront cette interface 
     * DEVROUT obligatoirement coder ces méthodes.
     */
    String getNom();
    void setNom(String nom);
    double getCapaciteBatterie();
    void setCapaciteBatterie(double capaciteBatterie);
    Base getBase();
    void setBase(Base base);
    TypeDrone getType();
    
    // Une méthode personnalisée pour l'exercice
    boolean isEfficient();

    /**
     * MÉTHODE 'default' (Apparue en Java 8)
     * C'est une méthode dans une interface qui A DÉJÀ un code.
     * Utile si vous voulez donner un comportement par défaut à TOUS les drones 
     * sans les forcer à le coder eux-mêmes.
     */
    default double coutMaintenance() {
        // On utilise les méthodes de l'interface pour calculer un coût.
        return getCapaciteBatterie() * TarifsOfficiels.getPrixParMah();
    }

    /**
     * MÉTHODE 'static'
     * Elle appartient à l'Interface elle-même, pas aux objets drones.
     * On l'appelle par : IDrone.dronesEfficients(...)
     * 
     * NOTION : '? extends IDrone' (Wildcard)
     * Cela signifie "une collection de n'importe quoi, tant que ce n'importe quoi est un IDrone".
     * Ça permet d'accepter une Liste de DroneTransport, ou une Liste de DroneMedical.
     */
    static List<IDrone> dronesEfficients(Collection<? extends IDrone> drones) {
        return drones.stream()
                     .filter(d -> d.isEfficient()) // On ne garde que les "bons"
                     .collect(Collectors.toList()); // On en fait une liste
    }
}
