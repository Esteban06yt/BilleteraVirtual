package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.service.EmailService;

public class NotificadorMovimientos {
    private EmailService emailService;

    public void notificarTransaccion() {
        public void notificarDeposito(Usuario usuario, double monto) {
            String mensaje = String.format("Depósito de $%.2f realizado. Saldo actual: $%.2f", monto, usuario.getSaldoTotal());
            EmailService.enviarEmail(usuario.getCorreo(), "Notificación de depósito", mensaje);
        }
    }
}