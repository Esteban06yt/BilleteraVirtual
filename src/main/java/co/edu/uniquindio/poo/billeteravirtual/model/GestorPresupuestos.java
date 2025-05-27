package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorPresupuestos {

    private List<Usuario> usuarios;

    public GestorPresupuestos() {
        this.usuarios = new ArrayList<>();
    }

    // Metodo para agregar un presupuesto a un usuario
    public void agregarPresupuestoAUsuario(Usuario usuario, Presupuesto presupuesto) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoNulo(presupuesto, "El presupuesto no puede ser nulo");

        // Verificar si el presupuesto ya existe para este usuario
        if (buscarPresupuestoPorIdYUsuario(usuario, presupuesto.getIdPresupuesto()) != null) {
            throw new IllegalArgumentException("Este presupuesto ya está asociado a este usuario.");
        }

        usuario.getPresupuestos().add(presupuesto);
    }

    // Metodo para eliminar un presupuesto de un usuario por ID
    public void eliminarPresupuestoDeUsuario(Usuario usuario, String idPresupuesto) {
        Validar.queNoVacio(idPresupuesto, "El ID del presupuesto no puede estar vacío");

        Presupuesto presupuesto = buscarPresupuestoPorIdYUsuario(usuario, idPresupuesto);
        if (presupuesto != null) {
            usuario.getPresupuestos().remove(presupuesto);
        } else {
            throw new IllegalArgumentException("No se encontró un presupuesto con ese ID para este usuario.");
        }
    }

    // Metodo para buscar un presupuesto por ID y usuario
    public Presupuesto buscarPresupuestoPorIdYUsuario(Usuario usuario, String idPresupuesto) {
        Validar.queNoVacio(idPresupuesto, "El ID del presupuesto no puede estar vacío");

        for (Presupuesto presupuesto : usuario.getPresupuestos()) {
            if (presupuesto.getIdPresupuesto().equals(idPresupuesto)) {
                return presupuesto;
            }
        }
        return null;
    }

    // Metodo para obtener todos los presupuestos de un usuario
    public List<Presupuesto> obtenerPresupuestosDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos();
    }

    // Metodo para actualizar el monto gastado de un presupuesto de un usuario
    public void actualizarMontoGastadoDePresupuesto(Usuario usuario, String idPresupuesto, Double montoGastado) {
        Validar.quePositivo(montoGastado, "El monto gastado debe ser positivo");

        Presupuesto presupuesto = buscarPresupuestoPorIdYUsuario(usuario, idPresupuesto);
        if (presupuesto != null) {
            if (presupuesto.getMontoAsignado() >= montoGastado) {
                presupuesto.setMontoGastado(montoGastado);
            } else {
                throw new IllegalArgumentException("El monto gastado no puede ser mayor que el monto asignado.");
            }
        } else {
            throw new IllegalArgumentException("No se encontró el presupuesto con ese ID para este usuario.");
        }
    }

    // Metodo para obtener el presupuesto con el mayor monto restante de un usuario
    public Presupuesto obtenerPresupuestoConMayorDisponibleDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        Optional<Presupuesto> presupuestoOpt = usuario.getPresupuestos().stream()
                .max((p1, p2) -> Double.compare(p1.calcularDisponible(), p2.calcularDisponible()));

        return presupuestoOpt.orElse(null);
    }

    // Metodo para obtener el total asignado a los presupuestos de un usuario
    public double obtenerTotalAsignadoDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream().mapToDouble(Presupuesto::getMontoAsignado).sum();
    }

    // Metodo para obtener el total gastado de los presupuestos de un usuario
    public double obtenerTotalGastadoDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream().mapToDouble(Presupuesto::getMontoGastado).sum();
    }

    // Metodo para obtener el total disponible de los presupuestos de un usuario
    public double obtenerTotalDisponibleDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream().mapToDouble(Presupuesto::calcularDisponible).sum();
    }
}