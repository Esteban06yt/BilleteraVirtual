package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.CodigoGenerador;
import co.edu.uniquindio.poo.billeteravirtual.model.Persona;

public class RecuperacionContraseniaService {
    private Persona persona;

    public RecuperacionContraseniaService(Persona persona) {
        this.persona = persona;
    }

    public void generarCodigoRecuperacion() {
        persona.setCodigoRecuperacion(CodigoGenerador.generarCodigo6Chars());
    }

    public boolean enviarCodigoPorEmail() {
        String asunto = "Código de recuperación";
        String cuerpo = "Su código es: " + this.persona.getCodigoRecuperacion();
        return EmailService.enviarEmail(this.persona.getCorreo(), asunto, cuerpo);
    }

    public boolean cambiarContrasenia(String nuevaContrasenia, String codigo) {
        if (this.persona.getCodigoRecuperacion().equals(codigo)) {
            persona.setContrasenia(nuevaContrasenia);
            return true;
        }
        return false;
    }
}