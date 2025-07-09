package kernel;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa un usuario del sistema financiero.
 * 
 * <p>Principios POO aplicados:
 * <ul>
 *   <li>Encapsulamiento: Atributos privados con getters/setters</li>
 *   <li>Abstracción: Expone funcionalidad sin revelar detalles</li>
 *   <li>Inmutabilidad: Atributos solo modificables mediante métodos controlados</li>
 * </ul>
 */
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULAMIENTO: Atributos privados
    private String documentoIdentidad;
    private String nombreCompleto;
    private String contrasena;
    private String correoElectronico;
    private String telefono;
    private boolean activo;

    /**
     * Constructor para crear un nuevo usuario.
     * @param documentoIdentidad Número de identificación único
     * @param nombreCompleto Nombre completo del usuario
     * @param contrasena Contraseña inicial
     * @param correoElectronico Correo electrónico
     * @param telefono Número de teléfono
     */
    public Usuario(String documentoIdentidad, String nombreCompleto, String contrasena, 
                  String correoElectronico, String telefono) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.activo = true;
    }

    // ENCAPSULAMIENTO: Getters y setters controlados
    
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { 
        this.documentoIdentidad = documentoIdentidad; 
    }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { 
        this.nombreCompleto = nombreCompleto; 
    }
    
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }
    
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { 
        this.correoElectronico = correoElectronico; 
    }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    /**
     * Verifica si la contraseña proporcionada coincide.
     * @param contrasena Contraseña a verificar
     * @return true si coinciden, false en caso contrario
     */
    public boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    /**
     * Desactiva el usuario en el sistema.
     */
    public void desactivarUsuario() {
        this.activo = false;
    }

    // SOBRESCRITURA: Métodos equals y hashCode para comparación por documento
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(documentoIdentidad, usuario.documentoIdentidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentoIdentidad);
    }
}
