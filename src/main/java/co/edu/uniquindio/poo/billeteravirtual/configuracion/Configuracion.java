package co.edu.uniquindio.poo.billeteravirtual.configuracion;

import java.util.Properties;

public class Configuracion {
    private static final Properties props = new Properties();

    static {
        // Cargar configuración al iniciar la aplicación
        try {
            props.load(Configuracion.class.getClassLoader().getResourceAsStream("email.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración de email", e);
        }
    }

    // Metodos para acceder a las propiedades
    public static String getSmtpHost() {
        return props.getProperty("mail.smtp.host");
    }

    public static int getSmtpPort() {
        return Integer.parseInt(props.getProperty("mail.smtp.port"));
    }

    public static String getSmtpUsername() {
        return props.getProperty("mail.smtp.username");
    }

    public static String getSmtpPassword() {
        return props.getProperty("mail.smtp.password");
    }

    public static boolean isSmtpSsl() {
        return Boolean.parseBoolean(props.getProperty("mail.smtp.ssl"));
    }

    public static boolean isSmtpAuth() {
        return Boolean.parseBoolean(props.getProperty("mail.smtp.auth"));
    }
}