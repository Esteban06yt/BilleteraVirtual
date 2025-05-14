package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.EnumSet;
import java.util.Set;

public enum RolAdministrador {
    SOPORTE(EnumSet.of(Permiso.CONSULTAS)),
    GESTOR(EnumSet.of(Permiso.CREAR_USUARIOS, Permiso.ELIMINAR_USUARIOS)),
    SUPERADMIN(EnumSet.allOf(Permiso.class));

    private final Set<Permiso> permisos;

    RolAdministrador(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    public boolean tienePermiso(Permiso permiso) {
        return permisos.contains(permiso);
    }
}