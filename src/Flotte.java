import java.util.*;
import java.util.stream.Collectors;

/**
 * RÔLE DU FICHIER : Gérer une collection de drones.
 * MODÈLE D'ANNALE : Correspond à la classe "Hotel", "Chaine", "Bibliotheque".
 *
 * NOTION : Généricité <T extends IDrone>, HashMap, TreeMap, TreeSet,
 *          Streams vs Boucles For, Wildcards (? extends / ? super).
 */
public class Flotte<T extends IDrone> {

    private String nomFlotte;

    // HASHMAP : Rapide pour chercher par CLÉ (ici le nom du drone).
    private Map<String, T> dronesParNom = new HashMap<>();

    // TREEMAP : Comme HashMap, mais les CLÉS sont toujours triées.
    private TreeMap<String, T> dronesTriesParNom = new TreeMap<>();

    // TREESET : Stocke des objets uniques et TRIÉS (utilise compareTo).
    private TreeSet<T> dronesOrdonnes = new TreeSet<>();

    // ARRAYLIST : Garde l'ordre d'arrivée.
    private ArrayList<T> historiqueAjouts = new ArrayList<>();

    public Flotte(String nomFlotte) {
        // Validation simple
        if (nomFlotte == null || nomFlotte.isEmpty()) {
            this.nomFlotte = "Flotte Sans Nom";
        } else {
            this.nomFlotte = nomFlotte;
        }
        // ASSERTION : Vérifie que le nom est bien celui attendu après l'opération.
        assert this.nomFlotte.equals(nomFlotte) : "Erreur d'initialisation du nom";
    }

    /**
     * MÉTHODE D'AJOUT : Avec toutes les vérifications d'examen.
     */
    public void ajouterDrone(T drone)
            throws DroneDejaPresentException, NomDejaUtiliseException, DroneInvalideException {

        // 1. Vérifier si le drone est null ou non efficient
        if (drone == null || !drone.isEfficient()) {
            throw new DroneInvalideException(drone);
        }

        // 2. Vérifier si l'OBJET est déjà présent (recherche par valeur)
        if (dronesParNom.containsValue(drone)) {
            throw new DroneDejaPresentException("Ce drone est déjà dans la flotte (même objet).");
        }

        // 3. Vérifier si le NOM est déjà pris (recherche par clé)
        if (dronesParNom.containsKey(drone.getNom())) {
            throw new NomDejaUtiliseException("Un drone porte déjà le nom : " + drone.getNom());
        }

        // 4. Ajouts dans les différentes structures
        dronesParNom.put(drone.getNom(), drone);       // Dans la Map
        dronesTriesParNom.put(drone.getNom(), drone); // Dans la Map triée
        dronesOrdonnes.add(drone);                    // Dans le Set trié (utilise compareTo)
        historiqueAjouts.add(drone);                  // Dans la liste simple

        // 5. Assertion finale
        assert dronesParNom.containsKey(drone.getNom()) : "Le drone aurait dû être ajouté.";
    }

    /**
     * RECHERCHE DIRECTE (par clé)
     */
    public T chercherParNom(String nom) {
        return dronesParNom.get(nom); // Retourne l'objet ou null
    }

    /**
     * RECHERCHE PAR BOUCLE (si on ne connaît pas la clé)
     */
    public T chercherParNomAvecFor(String nom) {
        for (T d : dronesParNom.values()) {
            if (d.getNom().equals(nom)) return d;
        }
        return null;
    }

    /**
     * SUPPRESSION
     */
    public T supprimerParNom(String nom) {
        T supprime = dronesParNom.remove(nom);
        if (supprime != null) {
            dronesTriesParNom.remove(nom);
            dronesOrdonnes.remove(supprime);
            historiqueAjouts.remove(supprime);
        }
        return supprime;
    }

    /**
     * TRI AVEC COLLECTIONS.SORT (Modèle d'examen très courant)
     */
    public List<T> dronesTriesAvecArrayList() {
        List<T> liste = new ArrayList<>(dronesParNom.values());
        Collections.sort(liste); // Utilise le compareTo défini dans Drone
        return liste;
    }

    /* -----------------------------------------------------------
     * SECTION STREAMS VS BOUCLES FOR (Comparaisons directes)
     * ----------------------------------------------------------- */

    // A. Filtrer et Afficher
    public void afficherDronesEfficientsStream() {
        dronesParNom.values().stream()
            .filter(d -> d.isEfficient())
            .forEach(d -> System.out.println(d));
    }
    public void afficherDronesEfficientsFor() {
        for (T d : dronesParNom.values()) {
            if (d.isEfficient()) System.out.println(d);
        }
    }

    // B. Moyenne des batteries
    public double moyenneBatterieStream() {
        return dronesParNom.values().stream()
            .mapToDouble(d -> d.getCapaciteBatterie())
            .average()
            .orElse(0.0);
    }
    public double moyenneBatterieFor() {
        if (dronesParNom.isEmpty()) return 0.0;
        double total = 0;
        for (T d : dronesParNom.values()) {
            total += d.getCapaciteBatterie();
        }
        return total / dronesParNom.size();
    }

    // C. Somme par type
    public double sommeBatterieParTypeStream(TypeDrone t) {
        return dronesParNom.values().stream()
            .filter(d -> d.getType() == t)
            .mapToDouble(d -> d.getCapaciteBatterie())
            .sum();
    }
    public double sommeBatterieParTypeFor(TypeDrone t) {
        double total = 0;
        for (T d : dronesParNom.values()) {
            if (d.getType() == t) total += d.getCapaciteBatterie();
        }
        return total;
    }

    // D. Collecter dans une liste
    public List<T> dronesParTypeStream(TypeDrone t) {
        return dronesParNom.values().stream()
            .filter(d -> d.getType() == t)
            .collect(Collectors.toList());
    }
    public List<T> dronesParTypeFor(TypeDrone t) {
        List<T> resultat = new ArrayList<>();
        for (T d : dronesParNom.values()) {
            if (d.getType() == t) resultat.add(d);
        }
        return resultat;
    }

    /* -----------------------------------------------------------
     * SECTION WILDCARDS (? extends / ? super)
     * ----------------------------------------------------------- */

    /**
     * ? EXTENDS : On veut LIRE des drones depuis une source.
     * La source peut contenir des sous-types de T (ex: une liste de DroneTransport).
     */
    public void absorberDepuis(Collection<? extends T> source) {
        for (T d : source) {
            try {
                this.ajouterDrone(d);
            } catch (GestionFlotteException e) {
                System.out.println("Saut d'un drone : " + e.getMessage());
            }
        }
    }

    /**
     * ? SUPER : On veut DÉPLACER nos drones vers une destination.
     * La destination doit pouvoir accepter des T ou ses parents (ex: une flotte de IDrone).
     */
    public void deplacerVers(Collection<? super T> destination) {
        destination.addAll(dronesParNom.values());
        // On vide la flotte courante après le transfert
        dronesParNom.clear();
        dronesTriesParNom.clear();
        dronesOrdonnes.clear();
        historiqueAjouts.clear();
    }

    @Override
    public String toString() {
        return "Flotte " + nomFlotte + " (" + dronesParNom.size() + " drones)";
    }
}
