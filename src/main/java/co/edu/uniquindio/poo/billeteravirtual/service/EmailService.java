package co.edu.uniquindio.poo.billeteravirtual.service;

public class EmailService {
    private String smtpHost;
    private Integer smtpPort;
    private String username;
    private String password;

    public static boolean enviarEmail(String destinatario, String asunto, String cuerpo) {
        // Implementación con JavaMail
        return true;
    }
}