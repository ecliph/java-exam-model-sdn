/**
 * RÔLE DU FICHIER : Montrer l'usage de 'static'.
 * MODÈLE D'ANNALE : Utile pour les variables partagées par TOUS les objets d'une classe.
 *
 * NOTION : static (appartient à la classe, pas à l'instance).
 */
public class TarifsOfficiels {

    // Attribut static : Existe en un seul exemplaire pour toute l'application.
    private static double prixParMah = 0.05;

    // Getter static : S'appelle par TarifsOfficiels.getPrixParMah()
    public static double getPrixParMah() {
        return prixParMah;
    }

    // Setter static : Si on change ici, tous les drones voient le changement.
    public static void setPrixParMah(double p) {
        prixParMah = p;
    }
}
