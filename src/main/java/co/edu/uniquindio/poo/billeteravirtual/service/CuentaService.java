package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.dto.CuentaDTO;
import co.edu.uniquindio.poo.billeteravirtual.model.Cuenta;

public class CuentaService {
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNombreBanco(cuentaDTO.getNombreBanco());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipo(cuentaDTO.getTipo());
        cuenta.setMonto(cuentaDTO.getMonto());

        // Persistir y retornar DTO
        return convertirADTO(cuenta);
    }

    private CuentaDTO convertirADTO(Cuenta cuenta) {
        // Conversión de entidad a DTO
    }
}