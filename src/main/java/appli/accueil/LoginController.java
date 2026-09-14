package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    protected void onConnexionClick() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Récupérer l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.getUtilisateurParEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (utilisateur != null && passwordEncoder.matches(password, utilisateur.getMdp())) {
            System.out.println("Connexion réussie pour : " + utilisateur.getNom());

            // Sauvegarde de la session
            SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);

            errorLabel.setVisible(false);

            // Redirection vers la page d'accueil
            StartApplication.changeScene("accueil/accueil");

        } else {
            System.out.println("Échec de la connexion. Email ou mot de passe incorrect.");
            errorLabel.setText("Email ou mot de passe incorrect.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    protected void onInscriptionClick() throws IOException {
        StartApplication.changeScene("accueil/inscription");
    }

    @FXML
    protected void onMotDePasseOublieClick() {
        System.out.println("Mot de passe oublié cliqué");
    }
}