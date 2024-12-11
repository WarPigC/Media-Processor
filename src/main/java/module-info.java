module org.project.mediaprocessor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.project.mediaprocessor to javafx.fxml;
    exports org.project.mediaprocessor;
}