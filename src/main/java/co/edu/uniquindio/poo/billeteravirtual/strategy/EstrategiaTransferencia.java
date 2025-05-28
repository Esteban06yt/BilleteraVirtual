package co.edu.uniquindio.poo.billeteravirtual.strategy;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.EstrategiaTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

public class EstrategiaTransferencia implements EstrategiaTransaccion {
    @Override
    public void procesar(Transaccion transaccion, GestorTransacciones gestor) {
        Cuenta cuentaEmisor = gestor.buscarCuentaDeUsuario(transaccion.getEmisor(), transaccion.getEmisor().getCuentas().getFirst().getIdCuenta());
        Cuenta cuentaDestino = gestor.buscarCuentaDeUsuario(transaccion.getDestinatario(), transaccion.getDestinatario().getCuentas().getFirst().getIdCuenta());

        cuentaEmisor.setMonto(cuentaEmisor.getMonto() - transaccion.getMonto());
        cuentaDestino.setMonto(cuentaDestino.getMonto() + transaccion.getMonto());

        gestor.getNotificador().notificarTransferencia(transaccion.getEmisor(), transaccion.getMonto(), transaccion.getDestinatario());
    }
}