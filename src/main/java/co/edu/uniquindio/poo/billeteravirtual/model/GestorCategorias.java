package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorCategorias {

    private List<Categoria> categorias;  // Lista para almacenar las categorías

    // Constructor
    public GestorCategorias() {
        this.categorias = new ArrayList<>();
    }

    // Metodo para agregar una nueva categoría
    public boolean agregarCategoria(Categoria categoria) {
        Validar.queNoNulo(categoria, "La categoría no puede ser nula");
        // Verificamos que no exista una categoría con el mismo nombre
        Optional<Categoria> categoriaExistente = categorias.stream()
                .filter(c -> c.getNombre().equals(categoria.getNombre()))
                .findFirst();

        if (categoriaExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una categoría con el mismo nombre.");
        }

        categorias.add(categoria);
        return false;
    }

    // Metodo para eliminar una categoría
    public boolean eliminarCategoria(String idCategoria) {
        Validar.queNoVacio(idCategoria, "El ID de la categoría no puede estar vacío");
        Categoria categoria = buscarCategoriaPorId(idCategoria);
        if (categoria != null) {
            categorias.remove(categoria);
        } else {
            throw new IllegalArgumentException("La categoría con ID " + idCategoria + " no existe.");
        }
        return false;
    }

    // Metodo para buscar una categoría por su ID
    public Categoria buscarCategoriaPorId(String idCategoria) {
        Validar.queNoVacio(idCategoria, "El ID de la categoría no puede estar vacío");
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria().equals(idCategoria)) {
                return categoria;
            }
        }
        return null; // Si no se encuentra
    }

    // Metodo para buscar una categoría por su nombre
    public Categoria buscarCategoriaPorNombre(String nombre) {
        Validar.queNoVacio(nombre, "El nombre de la categoría no puede estar vacío");
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        return null; // Si no se encuentra
    }

    // Metodo para listar todas las categorías
    public List<Categoria> listarCategorias() {
        return new ArrayList<>(categorias); // Devolvemos una copia de la lista
    }

    // Metodo para verificar si una categoría existe por su ID
    public boolean existeCategoriaPorId(String idCategoria) {
        return buscarCategoriaPorId(idCategoria) != null;
    }

    // Metodo para verificar si una categoría existe por su nombre
    public boolean existeCategoriaPorNombre(String nombre) {
        return buscarCategoriaPorNombre(nombre) != null;
    }

    // Metodo para obtener el número de categorías
    public int obtenerNumeroCategorias() {
        return categorias.size();
    }

    // Metodo para obtener una lista de categorías filtradas por algún criterio (por ejemplo, nombre)
    public List<Categoria> filtrarCategoriasPorNombre(String nombreFiltro) {
        List<Categoria> categoriasFiltradas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().contains(nombreFiltro)) {
                categoriasFiltradas.add(categoria);
            }
        }
        return categoriasFiltradas;
    }
}