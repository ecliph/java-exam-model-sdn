/**
 * --- LA FICHE RÉFLEXE (À LIRE EN DERNIER RECOURS) ---
 * 
 * RÔLE DU FICHIER : 
 * Si tu es bloqué sur une question de syntaxe, cherche le thème ici.
 * Ce fichier est un condensé de "Comment on écrit X en Java ?".
 */
public class FICHE_MEMO_EXAMEN {

    /* 
     * 1. CRÉER UNE CLASSE (La structure)
     * ----------------------------------
     * public class NomClasse {
     *     private Type nomAttr;                     // Attribut
     *     public NomClasse(Type n) { this.nomAttr=n; } // Constructeur
     *     public Type getNom() { return nomAttr; }     // Getter
     * }
     */

    /* 
     * 2. L'HÉRITAGE (Le lien "est un")
     * -------------------------------
     * public class Enfant extends Parent {
     *     public Enfant(String n) {
     *         super(n); // TOUJOURS appeler le constructeur du parent !
     *     }
     * }
     */

    /* 
     * 3. L'INTERFACE (Le lien "sait faire")
     * ------------------------------------
     * public interface IActions {
     *     void faireQuelqueChose(); // Pas de corps { }
     * }
     * public class MaClasse implements IActions { ... }
     */

    /* 
     * 4. LES COLLECTIONS (Où stocker ?)
     * ---------------------------------
     * List<Type> l = new ArrayList<>();   // Pour une liste simple
     * Map<String, Type> m = new HashMap<>(); // Pour chercher par un nom/ID
     * m.put("ID123", objet);             // Ajouter
     * m.get("ID123");                    // Récupérer
     */

    /* 
     * 5. LES BOUCLES (Parcourir une liste)
     * ------------------------------------
     * for (Type element : maListe) {
     *     // Faire quelque chose avec 'element'
     * }
     */

    /* 
     * 6. LES STREAMS (Calculer vite)
     * ------------------------------
     * maListe.stream()
     *        .filter(x -> x.getValeur() > 10) // Filtrer
     *        .map(x -> x.getNom())            // Transformer
     *        .mapToDouble(x -> x.getVal())    // En nombres
     *        .average().orElse(0.0)           // Moyenne
     *        .collect(Collectors.toList())    // En liste
     */

    /* 
     * 7. LES EXCEPTIONS (Gérer les problèmes)
     * ---------------------------------------
     * try {
     *    methodeQuiRisqueDePlanter();
     * } catch (MonException e) {
     *    System.out.println(e.getMessage());
     * }
     */
}
