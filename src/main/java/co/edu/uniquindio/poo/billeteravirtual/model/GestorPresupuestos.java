package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GestorPresupuestos {

    private List<Usuario> usuarios;

    public GestorPresupuestos() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        Validar.queNoNulo(usuarios, "La lista de usuarios no puede ser nula");
        this.usuarios = usuarios;
    }

    /**
     * Agrega un presupuesto a un usuario si no está ya asociado.
     */
    public void agregarPresupuestoAUsuario(Usuario usuario, Presupuesto presupuesto) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoNulo(presupuesto, "El presupuesto no puede ser nulo");

        if (buscarPresupuestoPorIdYUsuario(usuario, presupuesto.getIdPresupuesto()).isPresent()) {
            throw new IllegalArgumentException("Este presupuesto ya está asociado a este usuario.");
        }

        usuario.getPresupuestos().add(presupuesto);
    }

    /**
     * Elimina un presupuesto de un usuario según su ID.
     */
    public void eliminarPresupuestoDeUsuario(Usuario usuario, String idPresupuesto) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoVacio(idPresupuesto, "El ID del presupuesto no puede estar vacío");

        Optional<Presupuesto> presupuesto = buscarPresupuestoPorIdYUsuario(usuario, idPresupuesto);
        if (presupuesto.isPresent()) {
            usuario.getPresupuestos().remove(presupuesto.get());
        } else {
            throw new IllegalArgumentException("No se encontró un presupuesto con ese ID para este usuario.");
        }
    }

    /**
     * Busca un presupuesto por ID dentro de los del usuario.
     */
    public Optional<Presupuesto> buscarPresupuestoPorIdYUsuario(Usuario usuario, String idPresupuesto) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoVacio(idPresupuesto, "El ID del presupuesto no puede estar vacío");

        return usuario.getPresupuestos().stream()
                .filter(p -> idPresupuesto.equals(p.getIdPresupuesto()))
                .findFirst();
    }

    /**
     * Retorna todos los presupuestos del usuario.
     */
    public List<Presupuesto> obtenerPresupuestosDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        return new ArrayList<>(usuario.getPresupuestos()); // Para evitar modificación directa
    }

    /**
     * Actualiza el monto gastado de un presupuesto si es válido.
     */
    public void actualizarMontoGastadoDePresupuesto(Usuario usuario, String idPresupuesto, Double montoGastado) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoVacio(idPresupuesto, "El ID del presupuesto no puede estar vacío");
        Validar.quePositivo(montoGastado, "El monto gastado debe ser positivo");

        Optional<Presupuesto> presupuestoOpt = buscarPresupuestoPorIdYUsuario(usuario, idPresupuesto);
        if (presupuestoOpt.isPresent()) {
            Presupuesto presupuesto = presupuestoOpt.get();
            if (presupuesto.getMontoAsignado() >= montoGastado) {
                presupuesto.setMontoGastado(montoGastado);
            } else {
                throw new IllegalArgumentException("El monto gastado no puede ser mayor que el monto asignado.");
            }
        } else {
            throw new IllegalArgumentException("No se encontró el presupuesto con ese ID para este usuario.");
        }
    }

    /**
     * Retorna el presupuesto con mayor monto disponible.
     */
    public Presupuesto obtenerPresupuestoConMayorDisponibleDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream()
                .max(Comparator.comparingDouble(Presupuesto::calcularDisponible))
                .orElse(null);
    }

    /**
     * Retorna la suma del monto asignado de todos los presupuestos.
     */
    public double obtenerTotalAsignadoDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream()
                .mapToDouble(Presupuesto::getMontoAsignado)
                .sum();
    }

    /**
     * Retorna el total gastado en todos los presupuestos del usuario.
     */
    public double obtenerTotalGastadoDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream()
                .mapToDouble(Presupuesto::getMontoGastado)
                .sum();
    }

    /**
     * Retorna el monto total disponible en los presupuestos del usuario.
     */
    public double obtenerTotalDisponibleDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        return usuario.getPresupuestos().stream()
                .mapToDouble(Presupuesto::calcularDisponible)
                .sum();
    }
}