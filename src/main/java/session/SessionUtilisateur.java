package session;

import model.Utilisateur;

public class SessionUtilisateur {

    private static SessionUtilisateur instance;
    private Utilisateur utilisateurConnecte;

    // Constructeur privé — empêche l'instanciation directe
    private SessionUtilisateur() { }

    // Récupère l'unique instance du Singleton
    public static SessionUtilisateur getInstance() {
        if (instance == null) {
            instance = new SessionUtilisateur();
        }
        return instance;
    }

    // Sauvegarde l'utilisateur connecté (seulement si pas déjà connecté)
    public void sauvegardeSession(Utilisateur utilisateur) {
        if (this.utilisateurConnecte == null) {
            this.utilisateurConnecte = utilisateur;
        }
    }

    // Retourne l'utilisateur connecté
    public Utilisateur getUtilisateur() {
        return utilisateurConnecte;
    }

    // Vide la session (déconnexion)
    public void deconnecter() {
        utilisateurConnecte = null;
    }
}