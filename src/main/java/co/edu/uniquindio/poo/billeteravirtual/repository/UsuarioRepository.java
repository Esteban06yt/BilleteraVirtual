package co.edu.uniquindio.poo.billeteravirtual.repository;

import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(String id);
    Optional<Usuario> buscarPorCedula(String cedula);
    Optional<Usuario> buscarPorCorreo(String correo);
    List<Usuario> listarTodos();
    void eliminar(String id);
    Usuario actualizar(Usuario usuario);
    List<Transaccion> listarTransaccionesPorUsuario(String usuarioId);
    List<Usuario> buscarPorNombreSimilar(String nombre);
    boolean existePorCedula(String cedula);
    boolean existePorCorreo(String correo);
}