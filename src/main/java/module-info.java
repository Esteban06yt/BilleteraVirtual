module co.edu.uniquindio.poo.billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.mail;

    opens co.edu.uniquindio.poo.billeteravirtual to javafx.fxml;
    exports co.edu.uniquindio.poo.billeteravirtual;
    exports co.edu.uniquindio.poo.billeteravirtual.controller;
    opens co.edu.uniquindio.poo.billeteravirtual.controller to javafx.fxml;
}