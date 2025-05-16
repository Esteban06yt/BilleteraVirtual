package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class GestorCuentas {
    private List<Cuenta> cuentas;
    private NotificadorMovimientos notificador;

    public GestorCuentas(NotificadorMovimientos notificador) {
        this.cuentas = new ArrayList<>();
        this.notificador = notificador;
    }

    // Metodo para agregar una cuenta al sistema
    public void agregarCuenta(Cuenta cuenta) {
        Validar.queNoNulo(cuenta, "La cuenta no puede ser nula");
        if (buscarCuentaPorId(cuenta.getIdCuenta()) != null) {
            throw new IllegalArgumentException("La cuenta con este ID ya existe.");
        }
        cuentas.add(cuenta);
    }

    // Metodo para eliminar una cuenta por su ID
    public void eliminarCuenta(String idCuenta) {
        Validar.queNoVacio(idCuenta, "El ID de la cuenta no puede estar vacío");

        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        if (cuenta != null) {
            cuentas.remove(cuenta);
        } else {
            throw new IllegalArgumentException("No se encontró la cuenta con ese ID.");
        }
    }

    // Metodo para buscar una cuenta por su ID
    public Cuenta buscarCuentaPorId(String idCuenta) {
        Validar.queNoVacio(idCuenta, "El ID de la cuenta no puede estar vacío");

        for (Cuenta cuenta : cuentas) {
            if (cuenta.getIdCuenta().equals(idCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    // Metodo para obtener todas las cuentas
    public List<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    // Metodo para obtener las cuentas de un usuario
    public List<Cuenta> obtenerCuentasDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        List<Cuenta> cuentasUsuario = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (usuario.getCuentas().contains(cuenta)) {
                cuentasUsuario.add(cuenta);
            }
        }
        return cuentasUsuario;
    }

    // Metodo para realizar una transferencia entre cuentas
    public void realizarTransferencia(String idCuentaEmisor, String idCuentaDestino, double monto) {
        Validar.quePositivo(monto, "El monto de la transferencia debe ser positivo");

        Cuenta cuentaEmisor = buscarCuentaPorId(idCuentaEmisor);
        Cuenta cuentaDestino = buscarCuentaPorId(idCuentaDestino);

        if (cuentaEmisor == null) {
            throw new IllegalArgumentException("No se encontró la cuenta emisora.");
        }
        if (cuentaDestino == null) {
            throw new IllegalArgumentException("No se encontró la cuenta destinataria.");
        }
        if (cuentaEmisor.getMonto() < monto) {
            throw new IllegalArgumentException("La cuenta emisora no tiene suficientes fondos.");
        }

        cuentaEmisor.transferir(monto, cuentaDestino);
    }

    // Metodo para obtener el saldo total de todas las cuentas
    public double obtenerSaldoTotal() {
        return cuentas.stream().mapToDouble(Cuenta::getMonto).sum();
    }

    // Metodo para obtener el saldo total de las cuentas de un usuario
    public double obtenerSaldoTotalDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getCuentas().stream().mapToDouble(Cuenta::getMonto).sum();
    }

    // Metodo que realiza una transferencia o pago de la cuenta seleccionada
    public void realizarPago(Usuario usuario, Double monto, String idCuentaSeleccionada, String servicio) {
        // Buscar la cuenta seleccionada
        Cuenta cuentaSeleccionada = usuario.getCuentas().stream()
                .filter(c -> c.getIdCuenta().equals(idCuentaSeleccionada))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        // Restar dinero de la cuenta seleccionada
        usuario.restarDeCuenta(monto, cuentaSeleccionada);

        // Realizar la notificación del pago
        notificador.notificarPago(usuario, monto, servicio);
    }
}