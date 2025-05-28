package co.edu.uniquindio.poo.billeteravirtual.strategy;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.EstrategiaTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

public class EstrategiaRecarga implements EstrategiaTransaccion {
    @Override
    public void procesar(Transaccion transaccion, GestorTransacciones gestor) {
        Cuenta cuentaEmisor = gestor.buscarCuentaDeUsuario(transaccion.getEmisor(), transaccion.getEmisor().getCuentas().getFirst().getIdCuenta());

        cuentaEmisor.setMonto(cuentaEmisor.getMonto() + transaccion.getMonto());
        gestor.getNotificador().notificarRecarga(transaccion.getEmisor(), transaccion.getMonto());
    }
}