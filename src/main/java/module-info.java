module com.mycat.catperson {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires javafx.media;

    opens com.mycat.catperson to javafx.fxml;
    exports com.mycat.catperson;
}