import java.util.*;
import java.util.stream.Collectors;

/**
 * --- LEÇON 5 : LA GESTION DES DONNÉES (COLLECTIONS & GÉNÉRICITÉ) ---
 * 
 * RÔLE DU FICHIER : 
 * C'est le "cerveau" du projet. Il gère une liste de drones.
 * C'est ici que vous trouverez les réponses aux questions complexes de l'examen.
 * 
 * NOTION : <T extends IDrone>
 * Ça veut dire : "Cette classe travaille avec un type T, mais T DOIT être un drone".
 */
public class Flotte<T extends IDrone> {

    private String nomFlotte;

    /**
     * LES STRUCTURES DE DONNÉES (Le choix dépend du sujet)
     */
    
    // 1. HashMap : On cherche un drone par son NOM. C'est INSTANTANÉ.
    // Syntaxe : Map<Clé, Valeur>
    private Map<String, T> dronesParNom = new HashMap<>();

    // 2. TreeMap : Comme HashMap, mais les noms sont toujours TRIÉS alphabétiquement.
    private TreeMap<String, T> dronesTries = new TreeMap<>();

    // 3. ArrayList : Utile pour garder l'ordre dans lequel on a ajouté les drones.
    private List<T> listeHistorique = new ArrayList<>();

    public Flotte(String nomFlotte) {
        this.nomFlotte = nomFlotte;
    }

    /**
     * MÉTHODE D'AJOUT (La plus importante de l'examen !)
     * Elle contient souvent 3 ou 4 vérifications (if) avant de faire l'ajout.
     */
    public void ajouterDrone(T drone) 
            throws DroneDejaPresentException, NomDejaUtiliseException, DroneInvalideException {

        // VERIF 1 : L'objet est-il nul ou invalide ?
        if (drone == null || !drone.isEfficient()) {
            throw new DroneInvalideException(drone);
        }

        // VERIF 2 : Le NOM est-il déjà pris ? (On regarde les CLÉS de la Map)
        if (dronesParNom.containsKey(drone.getNom())) {
            throw new NomDejaUtiliseException("Le nom " + drone.getNom() + " existe déjà.");
        }

        // AJOUT RÉEL dans nos outils
        dronesParNom.put(drone.getNom(), drone);
        dronesTries.put(drone.getNom(), drone);
        listeHistorique.add(drone);
        
        System.out.println("Ajout réussi de : " + drone.getNom());
    }

    /**
     * RECHERCHE (Très simple avec une Map)
     */
    public T trouver(String nom) {
        return dronesParNom.get(nom); // Retourne l'objet ou null s'il n'existe pas.
    }

    /* -----------------------------------------------------------
     * SECTION STREAMS (Le mode "Moderne")
     * Les Streams sont comme un tapis roulant où on filtre les objets.
     * ----------------------------------------------------------- */

    /**
     * CALCULER UNE MOYENNE (Stream)
     */
    public double moyenneBatterie() {
        return dronesParNom.values().stream()           // On prend tous les drones
            .mapToDouble(d -> d.getCapaciteBatterie())  // On ne garde que leur chiffre de batterie
            .average()                                  // On demande la moyenne
            .orElse(0.0);                               // Si la liste est vide, on rend 0.0
    }

    /**
     * FILTRER DANS UNE LISTE (Stream)
     */
    public List<T> extraireParType(TypeDrone typeCherche) {
        return dronesParNom.values().stream()
            .filter(d -> d.getType() == typeCherche)    // On ne garde que le bon type
            .collect(Collectors.toList());              // On remet tout dans une Liste
    }

    /* -----------------------------------------------------------
     * SECTION BOUCLES (Le mode "Classique")
     * Si vous n'êtes pas à l'aise avec les Streams, utilisez ça !
     * ----------------------------------------------------------- */

    /**
     * CALCULER UNE MOYENNE (Boucle For)
     */
    public double moyenneBatterieClassique() {
        if (dronesParNom.isEmpty()) return 0.0;
        double somme = 0;
        for (T d : dronesParNom.values()) {
            somme += d.getCapaciteBatterie();
        }
        return somme / dronesParNom.size();
    }

    /**
     * FILTRER DANS UNE LISTE (Boucle For)
     */
    public List<T> extraireParTypeClassique(TypeDrone typeCherche) {
        List<T> resultat = new ArrayList<>();
        for (T d : dronesParNom.values()) {
            if (d.getType() == typeCherche) {
                resultat.add(d);
            }
        }
        return resultat;
    }

    /* -----------------------------------------------------------
     * SECTION TRI (Collections.sort)
     * ----------------------------------------------------------- */

    /**
     * TRIER UNE LISTE
     * Pour trier, on doit d'abord copier les données dans une ArrayList.
     */
    public List<T> obtenirToutTrie() {
        List<T> copie = new ArrayList<>(dronesParNom.values());
        // Collections.sort utilise le 'compareTo' que vous avez écrit dans Drone.java
        Collections.sort(copie);
        return copie;
    }

    @Override
    public String toString() {
        return "Flotte '" + nomFlotte + "' avec " + dronesParNom.size() + " drones.";
    }
}
