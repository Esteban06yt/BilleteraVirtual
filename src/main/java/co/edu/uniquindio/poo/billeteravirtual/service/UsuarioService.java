package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.*;
import co.edu.uniquindio.poo.billeteravirtual.repository.TransaccionRepository;
import co.edu.uniquindio.poo.billeteravirtual.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TransaccionRepository transaccionRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TransaccionRepository transaccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoVacio(usuario.getCedula(), "La cédula no puede estar vacía");

        if (usuarioRepository.existePorCedula(usuario.getCedula())) {
            throw new IllegalArgumentException("Ya existe un usuario con esta cédula");
        }

        if (usuarioRepository.existePorCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con este correo");
        }

        return usuarioRepository.guardar(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        Validar.queNoVacio(id, "El ID no puede estar vacío");
        return usuarioRepository.buscarPorId(id);
    }

    public Optional<Usuario> obtenerUsuarioPorCedula(String cedula) {
        Validar.queNoVacio(cedula, "La cédula no puede estar vacía");
        return usuarioRepository.buscarPorCedula(cedula);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.listarTodos();
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");
        Validar.queNoVacio(usuario.getId(), "El ID no puede estar vacío");

        if (!usuarioRepository.buscarPorId(usuario.getId()).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado para actualizar");
        }

        return usuarioRepository.actualizar(usuario);
    }

    public void eliminarUsuario(String id) {
        Validar.queNoVacio(id, "El ID no puede estar vacío");
        usuarioRepository.eliminar(id);
    }

    // Métodos adicionales
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        Validar.queNoVacio(correo, "El correo no puede estar vacío");
        return usuarioRepository.buscarPorCorreo(correo);
    }

    public List<Transaccion> listarTransaccionesUsuario(String usuarioId) {
        Validar.queNoVacio(usuarioId, "El ID de usuario no puede estar vacío");
        return usuarioRepository.listarTransaccionesPorUsuario(usuarioId);
    }

    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        Validar.queNoNulo(nombre, "El nombre no puede ser nulo");
        return usuarioRepository.buscarPorNombreSimilar(nombre);
    }

    public boolean autenticarUsuario(String correo, String contrasenia) {
        Validar.queNoVacio(correo, "El correo no puede estar vacío");
        Validar.queNoVacio(contrasenia, "La contraseña no puede estar vacía");

        return usuarioRepository.buscarPorCorreo(correo)
                .map(u -> u.getContrasenia().equals(contrasenia))
                .orElse(false);
    }

    public void cambiarContrasenia(String usuarioId, String nuevaContrasenia) {
        Validar.queNoVacio(usuarioId, "El ID de usuario no puede estar vacío");
        Validar.queContraseniaSegura(nuevaContrasenia);

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setContrasenia(nuevaContrasenia);
        usuarioRepository.actualizar(usuario);
    }
}