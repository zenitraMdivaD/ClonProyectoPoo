package kernel;

import java.util.Objects;

public class Usuario {
	private String documentoIdentidad;
	private String nombreCompleto;
	private String contrasena;
	private String correoElectronico;
	private String telefono;
	private boolean activo;

	public Usuario(String documentoIdentidad, String nombreCompleto, String contrasena, String correoElectronico,
			String telefono) {
		this.documentoIdentidad = documentoIdentidad;
		this.nombreCompleto = nombreCompleto;
		this.contrasena = contrasena;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
		this.activo = true;
	}

	// Getters
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isActivo() {
		return activo;
	}

	// Métodos de autenticacion y estado
	public boolean verificarContrasena(String contrasena) {
		return this.contrasena.equals(contrasena);
	}

	public void desactivarUsuario() {
		this.activo = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(documentoIdentidad, usuario.documentoIdentidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(documentoIdentidad);
	}
}