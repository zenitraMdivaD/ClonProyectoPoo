package complementos;

public class Excepciones {

	// Excepciones relacionadas con usuarios
	@SuppressWarnings("serial")
	public static class UsuarioNoEncontradoException extends Exception {
		public UsuarioNoEncontradoException(String mensaje) {
			super(mensaje);
		}
	}

	@SuppressWarnings("serial")
	public static class DocumentoDuplicadoException extends Exception {
		public DocumentoDuplicadoException(String mensaje) {
			super(mensaje);
		}
	}

	@SuppressWarnings("serial")
	public static class AutenticacionFallidaException extends Exception {
		public AutenticacionFallidaException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepciones relacionadas con operaciones
	@SuppressWarnings("serial")
	public static class SaldoInsuficienteException extends Exception {
		public SaldoInsuficienteException(String mensaje) {
			super(mensaje);
		}
	}

	@SuppressWarnings("serial")
	public static class MontoInvalidoException extends Exception {
		public MontoInvalidoException(String mensaje) {
			super(mensaje);
		}
	}

	@SuppressWarnings("serial")
	public static class LimiteSobrepasadoException extends Exception {
		public LimiteSobrepasadoException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepciones relacionadas con cuentas
	@SuppressWarnings("serial")
	public static class CuentaNoEncontradaException extends Exception {
		public CuentaNoEncontradaException(String mensaje) {
			super(mensaje);
		}
	}

	@SuppressWarnings("serial")
	public static class CuentaInactivaException extends Exception {
		public CuentaInactivaException(String mensaje) {
			super(mensaje);
		}
	}
}
