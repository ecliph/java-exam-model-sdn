import java.util.ArrayList;
import java.util.List;

/**
 * --- LEÇON 6 : LE TEST FINAL (MAIN) ---
 * 
 * RÔLE DU FICHIER : 
 * C'est ici qu'on "lance" la machine. 
 * En examen, on vous demande souvent d'écrire une classe de test (Main) 
 * pour prouver que votre code fonctionne.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== DÉMARRAGE DU PROGRAMME DE RÉVISION ===");

        // 1. CRÉATION DES OBJETS DE BASE
        // On crée d'abord les dépendances (les objets dont on a besoin pour les autres).
        Base baseParis = new Base("48.8, 2.3", 500.0);
        Base baseLyon = new Base("45.7, 4.8", 300.0);

        // 2. CRÉATION DE LA GESTION (Flotte)
        // On précise que notre flotte va gérer n'importe quel type de IDrone.
        Flotte<IDrone> maFlotte = new Flotte<>("Flotte de Sécurité");

        // 3. CRÉATION DES DRONES (Polymorphisme)
        // On peut stocker un DroneTransport dans une variable de type IDrone !
        IDrone t1 = new DroneTransport("Alpha-1", 1200.0, baseParis, 3);
        IDrone t2 = new DroneTransport("Beta-Error", 10.0, baseParis, 20); // Batterie trop faible -> Inefficace
        IDrone m1 = new DroneMedical("Medi-Safe", 800.0, baseLyon, true);

        // 4. TEST DES AJOUTS AVEC TRY / CATCH
        // C'est ici qu'on gère les erreurs. Si une erreur arrive, le programme 
        // ne "plante" pas, il va directement dans le 'catch'.
        System.out.println("\n[ÉTAPE 1] Ajout des drones...");
        try {
            maFlotte.ajouterDrone(t1);
            maFlotte.ajouterDrone(m1);
            
            // Tentative d'ajout d'un drone inefficace (doit lever une exception)
            System.out.println("Tentative d'ajout d'un drone inefficace...");
            maFlotte.ajouterDrone(t2);

        } catch (DroneInvalideException e) {
            System.err.println("ERREUR PRÉVUE : Le drone " + e.getDroneRejete().getNom() + " a été rejeté.");
        } catch (NomDejaUtiliseException | DroneDejaPresentException e) {
            System.err.println("ERREUR : " + e.getMessage());
        }

        // 5. TEST DES CALCULS (STREAMS / BOUCLES)
        System.out.println("\n[ÉTAPE 2] Statistiques :");
        System.out.println("Moyenne batterie (Stream) : " + maFlotte.moyenneBatterie() + " mAh");
        
        List<IDrone> medicaux = maFlotte.extraireParType(TypeDrone.MEDICAL);
        System.out.println("Nombre de drones médicaux : " + medicaux.size());

        // 6. TEST DU TRI
        System.out.println("\n[ÉTAPE 3] Tri alphabétique des noms :");
        List<IDrone> tries = maFlotte.obtenirToutTrie();
        for (IDrone d : tries) {
            System.out.println(" - " + d.getNom());
        }

        System.out.println("\n=== FIN DE LA DÉMONSTRATION ===");
        System.out.println("CONSEIL : Étudiez bien les blocs try/catch ci-dessus, c'est crucial !");
    }
}
