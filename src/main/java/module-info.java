module com.example.utilisateurfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.utilisateurfx to javafx.fxml;
    exports com.example.utilisateurfx;
    exports appli.accueil;
    opens appli.accueil to javafx.fxml;
    exports appli;
    opens appli to javafx.fxml;
}