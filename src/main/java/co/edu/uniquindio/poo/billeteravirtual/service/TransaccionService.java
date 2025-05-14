package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.dto.TransaccionDTO;
import co.edu.uniquindio.poo.billeteravirtual.model.NotificadorMovimientos;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

public class TransaccionService {
    private NotificadorMovimientos notificador;

    public TransaccionDTO registrarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = new Transaccion(
                transaccionDTO.getTipo(),
                transaccionDTO.getMonto(),
                transaccionDTO.getDescripcion()
        );

        // Lógica de negocio y persistencia
        notificador.notificarTransaccion(transaccion);

        return convertirADTO(transaccion);
    }
}