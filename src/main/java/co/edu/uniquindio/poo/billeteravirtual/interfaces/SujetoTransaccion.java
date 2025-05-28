package co.edu.uniquindio.poo.billeteravirtual.interfaces;

import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

public interface SujetoTransaccion {
    void agregarObservador(ObservadorTransaccion obs);
    void eliminarObservador(ObservadorTransaccion obs);
    void notificarObservadores(Transaccion transaccion);
}