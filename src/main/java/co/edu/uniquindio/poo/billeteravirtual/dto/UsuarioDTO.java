package co.edu.uniquindio.poo.billeteravirtual.dto;

public class UsuarioDTO {
    private String cedula;
    private String nombreCompleto;
    private String correo;
    private Double saldoTotal;

    public UsuarioDTO(String cedula, String nombreCompleto, String correo, Double saldoTotal) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.saldoTotal = saldoTotal;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", saldoTotal=" + saldoTotal +
                '}';
    }
}