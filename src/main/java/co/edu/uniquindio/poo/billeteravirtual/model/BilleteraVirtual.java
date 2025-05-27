package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.service.EmailServiceFactory;
import co.edu.uniquindio.poo.billeteravirtual.model.NotificadorMovimientos;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorUsuarios;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorAdministradores;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorPresupuestos;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorCategorias;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorCuentas;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorTransacciones;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorEstadisticas;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorReportes;

public class BilleteraVirtual {

    private static BilleteraVirtual instance;

    private final GestorUsuarios gestorUsuarios;
    private final GestorAdministradores gestorAdministradores;
    private final GestorPresupuestos gestorPresupuestos;
    private final GestorCategorias gestorCategorias;
    private final GestorCuentas gestorCuentas;
    private final GestorTransacciones gestorTransacciones;
    private final GestorEstadisticas gestorEstadisticas;
    private final GestorReportes gestorReportes;

    private BilleteraVirtual() {
        NotificadorMovimientos notificador = new NotificadorMovimientos(
                EmailServiceFactory.getInstance()
        );
        this.gestorUsuarios = new GestorUsuarios();
        this.gestorAdministradores = new GestorAdministradores();
        this.gestorPresupuestos = new GestorPresupuestos();
        this.gestorCategorias = new GestorCategorias();
        this.gestorCuentas = new GestorCuentas(notificador);
        this.gestorTransacciones = new GestorTransacciones(notificador);
        this.gestorEstadisticas = new GestorEstadisticas(
                gestorUsuarios.getUsuarios(),
                gestorTransacciones.listarTransacciones()
        );
        this.gestorReportes = new GestorReportes();
    }

    public static BilleteraVirtual getInstance(){
        if (instance == null){
            instance = new BilleteraVirtual();
        }
        return instance;
    }

    public static void setInstance(BilleteraVirtual instance) {
        BilleteraVirtual.instance = instance;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    public GestorAdministradores getGestorAdministradores() {
        return gestorAdministradores;
    }

    public GestorPresupuestos getGestorPresupuestos() {
        return gestorPresupuestos;
    }

    public GestorCategorias getGestorCategorias() {
        return gestorCategorias;
    }

    public GestorCuentas getGestorCuentas() {
        return gestorCuentas;
    }

    public GestorTransacciones getGestorTransacciones() {
        return gestorTransacciones;
    }

    public GestorEstadisticas getGestorEstadisticas() {
        return gestorEstadisticas;
    }

    public GestorReportes getGestorReportes() {
        return gestorReportes;
    }
}