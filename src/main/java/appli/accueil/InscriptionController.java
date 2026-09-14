package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    protected void onInscriptionClick() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String motDePasse = passwordField.getText();
        String confirmation = confirmPasswordField.getText();

        // Vérification : champs vides
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()
                || motDePasse.isEmpty() || confirmation.isEmpty()) {
            afficherErreur("Tous les champs sont obligatoires.");
            return;
        }

        // Vérification : mots de passe différents
        if (!motDePasse.equals(confirmation)) {
            afficherErreur("Les mots de passe ne correspondent pas.");
            return;
        }

        // Vérification : email déjà utilisé en base
        if (utilisateurRepository.getUtilisateurParEmail(email) != null) {
            afficherErreur("Cet email est déjà utilisé.");
            return;
        }

        // Hachage du mot de passe avec BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String motDePasseHache = passwordEncoder.encode(motDePasse);

        // Création de l'utilisateur et enregistrement en base
        Utilisateur nouvelUtilisateur = new Utilisateur(
                nom, prenom, email, motDePasseHache, "user"
        );
        utilisateurRepository.ajouterUtilisateur(nouvelUtilisateur);

        System.out.println("Inscription réussie : " + nouvelUtilisateur);
        errorLabel.setText("");
    }

    @FXML
    protected void onRetourClick() throws IOException {
        StartApplication.changeScene("accueil/login");
    }

    private void afficherErreur(String message) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-text-fill: red;");
    }
}