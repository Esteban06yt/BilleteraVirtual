package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.ObservadorTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.interfaces.SujetoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.service.EmailService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificadorMovimientos implements SujetoTransaccion {
    private final EmailService emailService;
    private final List<ObservadorTransaccion> observadores = new CopyOnWriteArrayList<>();

    public NotificadorMovimientos(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void agregarObservador(ObservadorTransaccion obs) {
        observadores.add(obs);
    }

    @Override
    public void eliminarObservador(ObservadorTransaccion obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificarObservadores(Transaccion transaccion) {
        for (ObservadorTransaccion obs : observadores) {
            obs.actualizar(transaccion);
        }
    }

    // Notificación de Depósito
    public void notificarDeposito(Usuario usuario, Double monto) {
        String asunto = "Confirmación de depósito";
        String cuerpo = String.format(
                "Hola %s,\n\nSe ha depositado $%.2f a tu cuenta.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    // Notificación de Retiro
    public void notificarRetiro(Usuario usuario, Double monto) {
        String asunto = "Confirmación de retiro";
        String cuerpo = String.format(
                "Hola %s,\n\nSe ha retirado $%.2f de tu cuenta.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    // Notificación de Transferencia
    public void notificarTransferencia(Usuario usuario, Double monto, Usuario destino) {
        String asunto = "Confirmación de transferencia";
        String cuerpo = String.format(
                "Hola %s,\n\nHas transferido $%.2f a %s.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, destino.getNombreCompleto(), usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    // Notificación de Pago
    public void notificarPago(Usuario usuario, Double monto, String servicio) {
        String asunto = "Confirmación de pago";
        String cuerpo = String.format(
                "Hola %s,\n\nHas realizado un pago de $%.2f por el servicio: %s.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, servicio, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    // Notificación de Recarga
    public void notificarRecarga(Usuario usuario, Double monto) {
        String asunto = "Confirmación de recarga";
        String cuerpo = String.format(
                "Hola %s,\n\nSe ha recargado $%.2f a tu cuenta.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }
}