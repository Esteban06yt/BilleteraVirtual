package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;

import java.util.Collection;

public class CrudUsuarioController {

    private static CrudUsuarioController instance;
    BilleteraVirtual billetera;
    SistemaBilleteraFacade billeteraFacade;

    public CrudUsuarioController(BilleteraVirtual billetera) {
        this.billetera = billetera;
    }

    public boolean crearUsuario(Usuario usuario) {
        return billeteraFacade.registrarUsuario(usuario);
    }

    public boolean eliminarUsuario(String cedula) {
        return billeteraFacade.eliminarUsuario(cedula);
    }

    public boolean actualizarUsuario(String cedula, Usuario usuario) {
        return billeteraFacade.actualizarUsuario(cedula, usuario);
    }

    public Collection<Usuario> obtenerListaUsuarios() {
        return billeteraFacade.listarUsuarios();
    }

    public static CrudUsuarioController getInstance() {
        if (instance == null) {
            //instance = new CrudUsuarioController();
        }
        return instance;
    }

}