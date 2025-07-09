package kernel;

import java.io.Serializable;

public abstract class TipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String nombre;

	protected TipoMovimiento(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static final class Deposito extends TipoMovimiento {
		private static final long serialVersionUID = 1L;
		public Deposito() {
			super("DEPÓSITO");
		}
	}

	public static final class Retiro extends TipoMovimiento {
		private static final long serialVersionUID = 1L;
		public Retiro() {
			super("RETIRO");
		}
	}

	public static final class TransferenciaEnviada extends TipoMovimiento {
		private static final long serialVersionUID = 1L;
		public TransferenciaEnviada() {
			super("TRANSFERENCIA_ENVIADA");
		}
	}

	public static final class TransferenciaRecibida extends TipoMovimiento {
		private static final long serialVersionUID = 1L;
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