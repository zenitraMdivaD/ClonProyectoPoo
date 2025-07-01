package kernel;

public abstract class TipoMovimiento {
	private final String nombre;

	protected TipoMovimiento(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static final class Deposito extends TipoMovimiento {
		public Deposito() {
			super("DEPÓSITO");
		}
	}

	public static final class Retiro extends TipoMovimiento {
		public Retiro() {
			super("RETIRO");
		}
	}

	public static final class TransferenciaEnviada extends TipoMovimiento {
		public TransferenciaEnviada() {
			super("TRANSFERENCIA_ENVIADA");
		}
	}

	public static final class TransferenciaRecibida extends TipoMovimiento {
		public TransferenciaRecibida() {
			super("TRANSFERENCIA_RECIBIDA");
		}
	}

	// Instancias únicas para cada tipo
	public static final Deposito DEPOSITO = new Deposito();
	public static final Retiro RETIRO = new Retiro();
	public static final TransferenciaEnviada TRANSFERENCIA_ENVIADA = new TransferenciaEnviada();
	public static final TransferenciaRecibida TRANSFERENCIA_RECIBIDA = new TransferenciaRecibida();
}