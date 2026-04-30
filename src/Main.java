import java.util.ArrayList;
import java.util.List;

/**
 * RÔLE DU FICHIER : Exécuter le code et montrer les résultats.
 * MODÈLE D'ANNALE : Souvent la dernière question "Écrire un main qui...".
 *
 * NOTION : Try/Catch multiple, Instanciation, Appels de méthodes.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("--- DÉBUT DE LA DÉMONSTRATION DRONES ---");

        // 1. Création des bases
        Base baseParis = new Base("48.8, 2.3", 500.0);
        baseLyon = new Base("45.7, 4.8", 300.0);

        // 2. Création de la flotte (Générique)
        Flotte<IDrone> maFlotte = new Flotte<>("Flotte Alpha");

        // 3. Création des drones
        DroneTransport t1 = new DroneTransport("Transporter-1", 1000.0, baseParis, 2); // Efficient (500 per colis)
        DroneTransport t2 = new DroneTransport("Transporter-2", 100.0, baseParis, 10); // Non-Efficient
        DroneMedical m1 = new DroneMedical("Medi-Medic", 500.0, baseLyon, true);      // Efficient

        // 4. Test des ajouts avec gestion des exceptions
        System.out.println("\n[Tests d'ajouts]");
        try {
            maFlotte.ajouterDrone(t1);
            System.out.println("OK : Ajout de t1");

            maFlotte.ajouterDrone(m1);
            System.out.println("OK : Ajout de m1");

            // Test Doublon d'objet
            // maFlotte.ajouterDrone(t1); // Déclencherait DroneDejaPresentException

            // Test Doublon de NOM
            DroneMedical m2 = new DroneMedical("Medi-Medic", 800.0, baseParis, true);
            // maFlotte.ajouterDrone(m2); // Déclencherait NomDejaUtiliseException

            // Test Drone Invalide (t2 n'est pas efficient)
            System.out.println("Tentative ajout t2 (non efficient)...");
            maFlotte.ajouterDrone(t2);

        } catch (NomDejaUtiliseException | DroneDejaPresentException | DroneInvalideException e) {
            System.err.println("ERREUR : " + e.getMessage());
        }

        // 5. Tests des Streams et des boucles For
        System.out.println("\n[Moyenne des batteries]");
        System.out.println("Stream : " + maFlotte.moyenneBatterieStream());
        System.out.println("For    : " + maFlotte.moyenneBatterieFor());

        System.out.println("\n[Drones Médicaux]");
        System.out.println("Stream : " + maFlotte.dronesParTypeStream(TypeDrone.MEDICAL));
        System.out.println("For    : " + maFlotte.dronesParTypeFor(TypeDrone.MEDICAL));

        // 6. Test du tri
        System.out.println("\n[Drones triés par nom (compareTo)]");
        List<IDrone> tries = maFlotte.dronesTriesAvecArrayList();
        for (IDrone d : tries) {
            System.out.println("- " + d.getNom());
        }

        // 7. Test de ? extends
        System.out.println("\n[Test ? extends]");
        List<DroneTransport> catalogueNouveaux = new ArrayList<>();
        catalogueNouveaux.add(new DroneTransport("New-Trans-1", 1500.0, baseParis, 3));
        maFlotte.absorberDepuis(catalogueNouveaux);
        System.out.println("Nouvelle taille flotte : " + maFlotte.chercherParNom("New-Trans-1") != null);

        // 8. Test de ? super
        System.out.println("\n[Test ? super]");
        List<Object> archiveGlobale = new ArrayList<>();
        maFlotte.deplacerVers(archiveGlobale);
        System.out.println("Taille archive : " + archiveGlobale.size());
        System.out.println("Ma flotte est maintenant : " + maFlotte);

        System.out.println("\n--- FIN DE LA DÉMONSTRATION ---");
    }
}
