package co.edu.uniquindio.poo.billeteravirtual.interfaces;

import co.edu.uniquindio.poo.billeteravirtual.model.GestorTransacciones;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

public interface EstrategiaTransaccion {
    void procesar(Transaccion transaccion, GestorTransacciones gestor);
}