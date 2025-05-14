package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.CodigoGenerador;
import co.edu.uniquindio.poo.billeteravirtual.model.Persona;

public class RecuperacionContraseniaService {
    private Persona persona;

    public RecuperacionContraseniaService(Persona persona) {
        this.persona = persona;
    }

    public boolean enviarCodigoPorEmail() {
        String asunto = "Código de recuperación - Billetera Virtual";
        String cuerpo = String.format(
                "Estimado %s,\n\n" +
                "Su código de recuperación es: %s\n\n" +
                "Si no solicitó este código, ignore este mensaje.",
                persona.getNombreCompleto().split(" ")[0], // Primer nombre
                persona.getCodigoRecuperacion()
        );
        return EmailService.enviarEmail(this.persona.getCorreo(), asunto, cuerpo);
    }

    public boolean cambiarContrasenia(String nuevaContrasenia, String codigo) {
        if (this.persona.getCodigoRecuperacion().equals(codigo)) {
            persona.setContrasenia(nuevaContrasenia);
            this.persona.setCodigoRecuperacion(null);
            return true;
        }
        return false;
    }

    private boolean validarFortalezaContrasenia(String contrasenia) {
        return contrasenia.length() >= 8 && contrasenia.matches(".*[A-Z].*") && contrasenia.matches(".*\\d.*");
    }
}