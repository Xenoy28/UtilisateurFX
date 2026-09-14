package repository;

import database.Database;
import model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;

public class UtilisateurRepository {

    private Connection connexion;

    // Constructeur — récupère la connexion via Database.java
    public UtilisateurRepository() {
        this.connexion = Database.getConnexion();
    }

    // Méthode fournie : Ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs (nom, prenom, email, mdp, role) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    // Récupérer un utilisateur par email
    public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
        return null; // Aucun utilisateur trouvé
    }

    // Récupérer tous les utilisateurs
    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                liste.add(new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération : " + e.getMessage());
        }
        return liste;
    }

    // Supprimer un utilisateur par email
    public void supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM utilisateurs WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    // Mettre à jour un utilisateur
    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, mdp = ?, role = ? " +
                "WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getMdp());
            stmt.setString(4, utilisateur.getRole());
            stmt.setString(5, utilisateur.getEmail());
            stmt.executeUpdate();
            System.out.println("Utilisateur mis à jour avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }
}