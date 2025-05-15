package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.Validar;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    private final String smtpHost;
    private final int smtpPort;
    private final String username;
    private final String password;
    private final boolean sslEnabled;
    private final boolean authEnabled;

    public EmailService(String smtpHost, int smtpPort, String username, String password, boolean sslEnabled) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.sslEnabled = sslEnabled;
        this.authEnabled = true;
    }

    public boolean enviarEmail(String destinatario, String asunto, String cuerpo) {
        return enviarEmail(destinatario, asunto, cuerpo, false);
    }

    public boolean enviarEmail(String destinatario, String asunto, String cuerpo, boolean esHTML) {
        Validar.queNoVacio(destinatario, "El destinatario no puede estar vacío");
        Validar.queCorreoDestinatarioValido(destinatario, "El correo del destinatario no es válido");
        Validar.queNoVacio(asunto, "El asunto no puede estar vacío");
        Validar.queNoVacio(cuerpo, "El cuerpo del mensaje no puede estar vacío");

        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(authEnabled));
        props.put("mail.smtp.starttls.enable", String.valueOf(sslEnabled));
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.connectiontimeout", "5000"); // 5 seconds
        props.put("mail.smtp.timeout", "5000"); // 5 seconds
        props.put("mail.smtp.writetimeout", "5000"); // 5 seconds

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);

            if (esHTML) {
                message.setContent(cuerpo, "text/html; charset=utf-8");
            } else {
                message.setText(cuerpo);
            }

            Transport.send(message);
            LOGGER.info("Correo enviado exitosamente a: " + destinatario);
            return true;
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Error al enviar correo a " + destinatario, e);
            return false;
        }
    }
}