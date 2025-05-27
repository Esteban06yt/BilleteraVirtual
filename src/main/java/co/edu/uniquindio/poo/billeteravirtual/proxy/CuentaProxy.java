package co.edu.uniquindio.poo.billeteravirtual.proxy;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.interfaces.CuentaInterface;
import co.edu.uniquindio.poo.billeteravirtual.model.Cuenta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;

public class CuentaProxy implements CuentaInterface {
    private final Cuenta real;
    private final Usuario usuarioActual;

    public CuentaProxy(Cuenta real, Usuario usuarioActual) {
        this.real = real;
        this.usuarioActual = usuarioActual;
    }

    private void checkAccess() {
        if (!usuarioActual.puedeModificarCuenta(real)) {
            throw new SecurityException("El usuario no tiene permiso sobre la cuenta " + real.getIdCuenta());
        }
    }

    @Override
    public String getIdCuenta() {
        return real.getIdCuenta();
    }

    @Override
    public String getNombreBanco() {
        return real.getNombreBanco();
    }

    @Override
    public Integer getNumeroCuenta() {
        return real.getNumeroCuenta();
    }

    @Override
    public Double getMonto() {
        return real.getMonto();
    }

    @Override
    public TipoCuenta getTipo() {
        return real.getTipo();
    }

    @Override
    public void setMonto(Double monto) {
        checkAccess();
        System.out.println("Logging: Usuario " + usuarioActual.getCedula() + " modifica monto de cuenta " + real.getIdCuenta());
        real.setMonto(monto);
    }

    @Override
    public void setTipo(TipoCuenta tipo) {
        checkAccess();
        System.out.println("Logging: Usuario " + usuarioActual.getCedula() + " modifica tipo de cuenta " + real.getIdCuenta());
        real.setTipo(tipo);
    }

    @Override
    public void transferir(double monto, CuentaInterface destino) {
        checkAccess();
        System.out.println("Logging: Usuario " + usuarioActual.getCedula() + " transfiere " + monto +
                " de cuenta " + real.getIdCuenta() + " a cuenta " + destino.getIdCuenta());
        real.transferir(monto, ((CuentaProxy) destino).real);
    }
}