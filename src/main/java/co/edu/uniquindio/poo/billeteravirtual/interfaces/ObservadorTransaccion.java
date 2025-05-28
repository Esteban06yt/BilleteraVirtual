package co.edu.uniquindio.poo.billeteravirtual.interfaces;

import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

public interface ObservadorTransaccion {
    /**
     * Se invoca cuando llega una nueva transacción.
     * @param transaccion la transacción recién creada.
     */
    void actualizar(Transaccion transaccion);
}