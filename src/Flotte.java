import java.util.*;
import java.util.stream.Collectors;

/**
 * --- LEÇON 5 : LE CERVEAU DU PROJET (COLLECTIONS & GÉNÉRICITÉ) ---
 * 
 * RÔLE DU FICHIER : 
 * Gérer un groupe de drones avec différentes structures de données.
 * C'est le fichier le plus important pour l'examen.
 * 
 * NOTION : <T extends IDrone>
 * Signifie que T est un type générique qui DOIT implémenter l'interface IDrone.
 */
public class Flotte<T extends IDrone> {

    private String nomFlotte;

    /**
     * LES STRUCTURES DE DONNÉES (Modèles d'annales)
     */
    
    // 1. HashMap : Très rapide pour chercher par NOM (Clé unique).
    private Map<String, T> dronesParNom = new HashMap<>();

    // 2. TreeSet : TRI AUTOMATIQUE et PAS DE DOUBLONS.
    // IMPORTANT : TreeSet utilise la méthode 'compareTo' du drone pour trier.
    private TreeSet<T> dronesOrdonnes = new TreeSet<>();

    // 3. ArrayList : Pour garder une liste simple (ex: historique des ajouts).
    private List<T> listeHistorique = new ArrayList<>();

    /**
     * CONSTRUCTEUR AVEC EXCEPTIONS ET ASSERTION
     */
    public Flotte(String nomFlotte) {
        // Assertion : Vérification interne (utile pour prouver la logique en examen)
        assert nomFlotte != null : "Le nom de la flotte ne doit pas être null";
        
        if (nomFlotte.isEmpty()) {
            this.nomFlotte = "Flotte Anonyme";
        } else {
            this.nomFlotte = nomFlotte;
        }
    }

    /**
     * MÉTHODE D'AJOUT COMPLÈTE (Modèle type annale)
     * Ordre : containsValue -> containsKey -> put -> assert
     */
    public void ajouterDrone(T drone) 
            throws DroneDejaPresentException, NomDejaUtiliseException, DroneInvalideException {

        // 1. VERIF PRIORITAIRE : L'objet est-il nul ou invalide ? (Evite le NullPointerException)
        if (drone == null || !drone.isEfficient()) {
            throw new DroneInvalideException(drone);
        }

        // 2. Vérifier si l'OBJET physique existe déjà (recherche par valeur)
        if (dronesParNom.containsValue(drone)) {
            throw new DroneDejaPresentException("Cet objet drone est déjà présent dans la flotte.");
        }

        // 3. Vérifier si le NOM est déjà pris (recherche par clé)
        if (dronesParNom.containsKey(drone.getNom())) {
            throw new NomDejaUtiliseException("Le nom '" + drone.getNom() + "' est déjà utilisé.");
        }

        // 4. AJOUT dans toutes les structures
        dronesParNom.put(drone.getNom(), drone); // Ajout Map
        dronesOrdonnes.add(drone);               // Ajout TreeSet (Tri auto)
        listeHistorique.add(drone);              // Ajout Liste simple

        // 5. ASSERTION : Vérifier que l'ajout a bien fonctionné
        assert dronesParNom.containsKey(drone.getNom()) : "L'ajout a échoué.";
    }

    /**
     * MÉTHODE DE SUPPRESSION COMPLÈTE
     * Retire l'objet de TOUTES les collections.
     */
    public T supprimerParNom(String nom) {
        // On récupère d'abord l'objet
        T aSupprimer = dronesParNom.get(nom);
        
        if (aSupprimer != null) {
            dronesParNom.remove(nom);           // Retrait Map
            dronesOrdonnes.remove(aSupprimer);  // Retrait TreeSet
            listeHistorique.remove(aSupprimer); // Retrait Liste
        }
        
        return aSupprimer; // On rend l'objet supprimé (ou null)
    }

    /* -----------------------------------------------------------
     * SECTION WILDCARDS (? extends / ? super)
     * ----------------------------------------------------------- */

    /**
     * ? EXTENDS (Source) : "Je lis des drones depuis une collection".
     * On peut lire n'importe quel sous-type de T.
     */
    public void ajouterDepuis(Collection<? extends T> source) {
        for (T d : source) {
            try {
                this.ajouterDrone(d);
            } catch (GestionFlotteException e) {
                System.out.println("Saut du drone : " + e.getMessage());
            }
        }
    }

    /**
     * ? SUPER (Destination) : "Je déplace mes drones vers une structure plus générale".
     * Exemple : Transférer une Flotte de DroneTransport vers une Flotte de IDrone.
     */
    public void deplacerVers(Flotte<? super T> destination) {
        for (T d : dronesParNom.values()) {
            try {
                destination.ajouterDrone(d);
            } catch (GestionFlotteException e) {
                // Gestion erreur transfert
            }
        }
        // Une fois déplacé, on peut vider la flotte actuelle
        this.dronesParNom.clear();
        this.dronesOrdonnes.clear();
        this.listeHistorique.clear();
    }

    /* -----------------------------------------------------------
     * SECTION STREAMS vs BOUCLES FOR (Tous les modèles)
     * ----------------------------------------------------------- */

    // 1. MOYENNE (average)
    public double moyenneBatterieStream() {
        return dronesParNom.values().stream()
            .mapToDouble(d -> d.getCapaciteBatterie())
            .average()
            .orElse(0.0);
    }
    public double moyenneBatterieFor() {
        if (dronesParNom.isEmpty()) return 0.0;
        double somme = 0;
        for (T d : dronesParNom.values()) {
            somme += d.getCapaciteBatterie();
        }
        return somme / dronesParNom.size();
    }

    // 2. SOMME (sum)
    public double sommeBatterieStream() {
        return dronesParNom.values().stream()
            .mapToDouble(d -> d.getCapaciteBatterie())
            .sum();
    }
    public double sommeBatterieFor() {
        double somme = 0;
        for (T d : dronesParNom.values()) {
            somme += d.getCapaciteBatterie();
        }
        return somme;
    }

    // 3. FILTRAGE ET COLLECTE (filter + collect)
    public List<T> filtrerParTypeStream(TypeDrone type) {
        return dronesParNom.values().stream()
            .filter(d -> d.getType() == type)
            .collect(Collectors.toList());
    }
    public List<T> filtrerParTypeFor(TypeDrone type) {
        List<T> res = new ArrayList<>();
        for (T d : dronesParNom.values()) {
            if (d.getType() == type) res.add(d);
        }
        return res;
    }

    // 4. PREMIER ÉLÉMENT (findFirst)
    public Optional<T> trouverPremierParNomStream(String prefixe) {
        return dronesParNom.values().stream()
            .filter(d -> d.getNom().startsWith(prefixe))
            .findFirst();
    }
    public T trouverPremierParNomFor(String prefixe) {
        for (T d : dronesParNom.values()) {
            if (d.getNom().startsWith(prefixe)) return d;
        }
        return null;
    }

    // 5. ACTION SUR TOUS (forEach)
    public void afficherToutStream() {
        dronesParNom.values().stream().forEach(System.out::println);
    }
    public void afficherToutFor() {
        for (T d : dronesParNom.values()) {
            System.out.println(d);
        }
    }

    @Override
    public String toString() {
        return "Flotte [" + nomFlotte + "] Count=" + dronesParNom.size();
    }
}
