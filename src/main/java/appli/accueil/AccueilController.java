package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Utilisateur;
import session.SessionUtilisateur;
import java.io.IOException;

public class AccueilController {

    @FXML
    private Label nomUtilisateurLabel;

    @FXML
    public void initialize() {
        // Récupération de l'utilisateur connecté via le Singleton
        Utilisateur utilisateurActuel = SessionUtilisateur.getInstance().getUtilisateur();

        if (utilisateurActuel != null) {
            System.out.println("Utilisateur connecté : " + utilisateurActuel.getNom());
            nomUtilisateurLabel.setText(
                    "Bienvenue " + utilisateurActuel.getPrenom()
                            + " " + utilisateurActuel.getNom() + " !"
            );
        }
    }

    @FXML
    protected void handleLogout() throws IOException {
        // Vide la session
        SessionUtilisateur.getInstance().deconnecter();
        System.out.println("Utilisateur déconnecté.");

        // Retour à la page de connexion
        StartApplication.changeScene("accueil/login");
    }
}