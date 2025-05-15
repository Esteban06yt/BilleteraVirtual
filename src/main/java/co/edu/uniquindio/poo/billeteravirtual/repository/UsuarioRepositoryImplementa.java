package co.edu.uniquindio.poo.billeteravirtual.repository;

import co.edu.uniquindio.poo.billeteravirtual.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioRepositoryImplementa implements UsuarioRepository {
    private final Map<String, Usuario> usuarios = new HashMap<>();
    private final Map<String, String> cedulaToId = new HashMap<>();
    private final Map<String, String> correoToId = new HashMap<>();

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        cedulaToId.put(usuario.getCedula(), usuario.getId());
        correoToId.put(usuario.getCorreo(), usuario.getId());
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorCedula(String cedula) {
        return Optional.ofNullable(cedulaToId.get(cedula))
                .flatMap(this::buscarPorId);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return Optional.ofNullable(correoToId.get(correo))
                .flatMap(this::buscarPorId);
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void eliminar(String id) {
        buscarPorId(id).ifPresent(usuario -> {
            usuarios.remove(id);
            cedulaToId.remove(usuario.getCedula());
            correoToId.remove(usuario.getCorreo());
        });
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            return guardar(usuario);
        }
        throw new IllegalArgumentException("Usuario no encontrado");
    }

    @Override
    public List<Transaccion> listarTransaccionesPorUsuario(String usuarioId) {
        // Asumiendo que tienes acceso al repositorio de transacciones
        // Esto debería implementarse de acuerdo a tu estructura real
        return Collections.emptyList();
    }

    @Override
    public List<Usuario> buscarPorNombreSimilar(String nombre) {
        String nombreBusqueda = nombre.toLowerCase();
        return usuarios.values().stream()
                .filter(u -> u.getNombreCompleto().toLowerCase().contains(nombreBusqueda))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorCedula(String cedula) {
        return cedulaToId.containsKey(cedula);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return correoToId.containsKey(correo);
    }
}