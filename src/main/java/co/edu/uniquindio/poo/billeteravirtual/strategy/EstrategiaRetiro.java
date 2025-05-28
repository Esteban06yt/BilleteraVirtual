package co.edu.uniquindio.poo.billeteravirtual.strategy;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.EstrategiaTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

public class EstrategiaRetiro implements EstrategiaTransaccion {
    @Override
    public void procesar(Transaccion transaccion, GestorTransacciones gestor) {
        Cuenta cuentaEmisor = gestor.buscarCuentaDeUsuario(transaccion.getEmisor(), transaccion.getEmisor().getCuentas().getFirst().getIdCuenta());

        cuentaEmisor.setMonto(cuentaEmisor.getMonto() - transaccion.getMonto());
        gestor.getNotificador().notificarRetiro(transaccion.getEmisor(), transaccion.getMonto());
    }
}