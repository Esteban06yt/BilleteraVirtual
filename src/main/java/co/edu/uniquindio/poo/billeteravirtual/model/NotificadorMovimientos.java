package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.service.EmailService;

public class NotificadorMovimientos {
    private EmailService emailService;

    public NotificadorMovimientos(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notificarDeposito(Usuario usuario, Double monto) {
        String asunto = "Confirmación de depósito";
        String cuerpo = String.format(
                "Hola %s,\n\nSe ha depositado $%.2f a tu cuenta.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    public void notificarRetiro(Usuario usuario, Double monto) {
        String asunto = "Confirmación de retiro";
        String cuerpo = String.format(
                "Hola %s,\n\nSe ha retirado $%.2f de tu cuenta.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }

    public void notificarTransferencia(Usuario usuario, Double monto, Usuario destino) {
        String asunto = "Confirmación de transferencia";
        String cuerpo = String.format(
                "Hola %s,\n\nHas transferido $%.2f a %s.\nSaldo actual: $%.2f",
                usuario.getNombreCompleto(), monto, destino.getNombreCompleto(), usuario.getSaldo()
        );
        emailService.enviarEmail(usuario.getCorreo(), asunto, cuerpo);
    }
}