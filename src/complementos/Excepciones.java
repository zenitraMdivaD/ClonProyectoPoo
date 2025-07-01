package complementos;

public class Excepciones {

	// Excepciones relacionadas con usuarios
	public static class UsuarioNoEncontradoException extends Exception {
		public UsuarioNoEncontradoException(String mensaje) {
			super(mensaje);
		}
	}

	public static class DocumentoDuplicadoException extends Exception {
		public DocumentoDuplicadoException(String mensaje) {
			super(mensaje);
		}
	}

	public static class AutenticacionFallidaException extends Exception {
		public AutenticacionFallidaException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepciones relacionadas con operaciones
	public static class SaldoInsuficienteException extends Exception {
		public SaldoInsuficienteException(String mensaje) {
			super(mensaje);
		}
	}

	public static class MontoInvalidoException extends Exception {
		public MontoInvalidoException(String mensaje) {
			super(mensaje);
		}
	}

	public static class LimiteSobrepasadoException extends Exception {
		public LimiteSobrepasadoException(String mensaje) {
			super(mensaje);
		}
	}

	// Excepciones relacionadas con cuentas
	public static class CuentaNoEncontradaException extends Exception {
		public CuentaNoEncontradaException(String mensaje) {
			super(mensaje);
		}
	}

	public static class CuentaInactivaException extends Exception {
		public CuentaInactivaException(String mensaje) {
			super(mensaje);
		}
	}
}
