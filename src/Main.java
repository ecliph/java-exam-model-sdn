import java.util.*;

/**
 * --- LEÇON 6 : LE TEST D'INTÉGRATION FINAL ---
 * 
 * RÔLE DU FICHIER : 
 * Prouver que tous les modèles (TreeSet, Wildcards, Streams) fonctionnent.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== TEST DU MODÈLE FINAL CORRIGÉ ===");

        // 1. Initialisation
        Base b1 = new Base("48.8, 2.3", 500.0);
        Flotte<IDrone> flottePrincipale = new Flotte<>("Flotte Principale");

        // 2. Création de drones
        IDrone d1 = new DroneTransport("Z-Drone", 1000.0, b1, 5);
        IDrone d2 = new DroneTransport("A-Drone", 1200.0, b1, 8);
        IDrone d3 = new DroneMedical("M-Health", 800.0, b1, true);

        // 3. Test de l'ajout et du TRI AUTOMATIQUE (TreeSet)
        try {
            flottePrincipale.ajouterDrone(d1); // Ajout Z
            flottePrincipale.ajouterDrone(d2); // Ajout A (doit passer devant Z)
            flottePrincipale.ajouterDrone(d3); // Ajout M
        } catch (GestionFlotteException e) {
            System.err.println("Erreur d'ajout : " + e.getMessage());
        }

        // 4. Test du ? EXTENDS (Ajouter depuis une liste)
        List<DroneTransport> renforts = new ArrayList<>();
        renforts.add(new DroneTransport("Renfort-1", 1500.0, b1, 10));
        flottePrincipale.ajouterDepuis(renforts); // Fonctionne grâce au ? extends

        // 5. Test du ? SUPER (Déplacement vers une structure générale)
        // On crée une flotte qui accepte n'importe quel IDrone (donc parent de DroneTransport)
        Flotte<IDrone> archive = new Flotte<>("Archives");
        flottePrincipale.deplacerVers(archive); 

        System.out.println("\n--- État des lieux ---");
        System.out.println("Taille flotte principale (après déplacement) : " + flottePrincipale.toString());
        System.out.println("Taille archive : " + archive.toString());

        // 6. Test de la SUPPRESSION
        archive.supprimerParNom("A-Drone");
        System.out.println("Après suppression de A-Drone : " + archive.toString());

        // 7. Test des STREAMS vs BOUCLES
        System.out.println("\n--- Test des Calculs ---");
        System.out.println("Moyenne (Stream) : " + archive.moyenneBatterieStream());
        System.out.println("Moyenne (For)    : " + archive.moyenneBatterieFor());
        
        System.out.println("\nSomme batterie : " + archive.sommeBatterieStream());

        // 8. Test de findFirst
        Optional<IDrone> premier = archive.trouverPremierParNomStream("Ren");
        premier.ifPresent(d -> System.out.println("Premier drone commençant par 'Ren' : " + d.getNom()));

        System.out.println("\n=== FIN DES TESTS FINAUX ===");
    }
}
