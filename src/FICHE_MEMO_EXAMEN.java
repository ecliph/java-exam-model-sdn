/**
 * RÔLE DU FICHIER : Résumé ultra-rapide des patrons de code à recopier.
 * CE FICHIER EST UNE FICHE DE RÉVISION À IMPRIMER.
 */
public class FICHE_MEMO_EXAMEN {

    /* -----------------------------------------------------------
     * 1. INTERFACE (Le contrat)
     * -----------------------------------------------------------
     * public interface IMonObjet {
     *     void action();                      // Abstraite
     *     default void help() { ... }         // Avec code
     *     static void util() { ... }          // Outil de classe
     * }
     */

    /* -----------------------------------------------------------
     * 2. CLASSE ABSTRAITE (Le modèle de base)
     * -----------------------------------------------------------
     * public abstract class MonObjetBase implements IMonObjet {
     *     protected String nom;
     *     public MonObjetBase(String n) { this.nom = n; }
     *     @Override public void action() { ... }
     * }
     */

    /* -----------------------------------------------------------
     * 3. HÉRITAGE (La spécialisation)
     * -----------------------------------------------------------
     * public class MonObjetConcret extends MonObjetBase {
     *     public MonObjetConcret(String n) { super(n); }
     * }
     */

    /* -----------------------------------------------------------
     * 4. TRI (Comparable)
     * -----------------------------------------------------------
     * @Override
     * public int compareTo(Objet autre) {
     *     return this.valeur - autre.valeur; // Négatif si this < autre
     * }
     */

    /* -----------------------------------------------------------
     * 5. ÉGALITÉ (Equals/HashCode)
     * -----------------------------------------------------------
     * @Override
     * public boolean equals(Object o) {
     *     if (!(o instanceof MonType)) return false;
     *     return this.id.equals(((MonType)o).id);
     * }
     */

    /* -----------------------------------------------------------
     * 6. EXCEPTIONS (Gestion d'erreurs)
     * -----------------------------------------------------------
     * public class MonException extends Exception { ... }
     * void maMethode() throws MonException {
     *     if (erreur) throw new MonException("Message");
     * }
     */

    /* -----------------------------------------------------------
     * 7. HASHMAP (Dictionnaire)
     * -----------------------------------------------------------
     * Map<String, Objet> map = new HashMap<>();
     * map.put("cle", obj);
     * map.get("cle");           // Récupérer
     * map.containsKey("cle");   // Vérifier clé
     * map.containsValue(obj);   // Vérifier objet
     * map.values();             // Toutes les valeurs
     */

    /* -----------------------------------------------------------
     * 8. STREAMS (Le tapis roulant)
     * -----------------------------------------------------------
     * list.stream()
     *     .filter(x -> x.isOk())               // Garder
     *     .map(x -> x.getNom())                // Transformer
     *     .mapToDouble(x -> x.getVal())        // En nombres
     *     .average().orElse(0.0)               // Moyenne
     *     .collect(Collectors.toList())        // En liste
     */

    /* -----------------------------------------------------------
     * 9. GÉNÉRIQUES ET WILDCARDS
     * -----------------------------------------------------------
     * MaClasse<T extends Interface>            // T doit être compatible
     * Collection<? extends T>                  // Source (Lecture)
     * Collection<? super T>                    // Destination (Écriture)
     */
}
