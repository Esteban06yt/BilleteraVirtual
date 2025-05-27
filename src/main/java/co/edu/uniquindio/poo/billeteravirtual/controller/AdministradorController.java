package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministradorController {

    private Administrador administradorLogueado;

    public AdministradorController() {

        administradorLogueado = new Administrador("1", "111111", "Juan Pérez", "juan.perez@mail.com", "3001234567", "1234", "abc", true, RolAdministrador.SOPORTE);
    }

    // Retorna una lista con un solo administrador (el logueado)
    public ObservableList<Administrador> getAdministradorLogueado() {
        return FXCollections.observableArrayList(administradorLogueado);
    }

    public void actualizarAdministrador() {

    }

}
