package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.configuracion.Configuracion;

public class EmailServiceFactory {
    private static EmailService instance;

    public static synchronized EmailService getInstance() {
        if (instance == null) {
            instance = new EmailService(
                    Configuracion.getSmtpHost(),
                    Configuracion.getSmtpPort(),
                    Configuracion.getSmtpUsername(),
                    Configuracion.getSmtpPassword(),
                    Configuracion.isSmtpSsl()
            );
        }
        return instance;
    }

    // Para pruebas puedes inyectar un mock
    public static synchronized void setInstance(EmailService mockService) {
        instance = mockService;
    }
}