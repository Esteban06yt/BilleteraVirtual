package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;

import java.util.Collection;

public class AdministradorController {

    private static AdministradorController instance;
    BilleteraVirtual billetera;
    SistemaBilleteraFacade billeteraFacade;

    public AdministradorController(BilleteraVirtual billetera) {
        this.billetera = billetera;
    }

    public Collection<Administrador> obtenerListaAdministradores() {
        return billeteraFacade.listarAdministradores();
    }

    public static AdministradorController getInstance() {
        if (instance == null) {
            //instance = new AdministradorController();
        }
        return instance;
    }
}
