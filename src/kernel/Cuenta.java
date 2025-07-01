package kernel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import complementos.Excepciones;
import complementos.Excepciones.CuentaInactivaException;
import complementos.Excepciones.CuentaNoEncontradaException;
import complementos.Excepciones.LimiteSobrepasadoException;
import complementos.Excepciones.MontoInvalidoException;
import complementos.Excepciones.SaldoInsuficienteException;

public class Cuenta {
	private final String numeroCuenta;
	private final Usuario titular;
	private double saldo;
	private final List<Movimiento> movimientos;
	private boolean activa;
	private double limiteDiarioRetiro = 1000000;

	public Cuenta(String numeroCuenta, Usuario titular) {
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = 0.0;
		this.movimientos = new ArrayList<>();
		this.activa = true;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public Usuario getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public boolean isActiva() {
		return activa;
	}

	public List<Movimiento> getMovimientos() {
		return new ArrayList<>(movimientos);
	}

	public List<Movimiento> getMovimientosRecientes(int cantidad) {
		int startIndex = Math.max(0, movimientos.size() - cantidad);
		return new ArrayList<>(movimientos.subList(startIndex, movimientos.size()));
	}

	public List<Movimiento> getMovimientosPorFecha(LocalDate fecha) {
		return movimientos.stream().filter(m -> m.getFechaHora().toLocalDate().equals(fecha))
				.collect(Collectors.toList());
	}

	public void depositar(double monto) throws MontoInvalidoException, CuentaInactivaException {
		validarMonto(monto);
		validarEstadoCuenta();

		saldo += monto;
		movimientos.add(new Movimiento(TipoMovimiento.DEPOSITO, monto, "Depósito en efectivo", null));
	}

	public void depositar(double monto, String origen) throws MontoInvalidoException, CuentaInactivaException {
		validarMonto(monto);
		validarEstadoCuenta();

		saldo += monto;
		movimientos.add(new Movimiento(TipoMovimiento.DEPOSITO, monto, "Depósito desde cuenta: " + origen, origen));
	}

	public void retirar(double monto, String contrasena) throws MontoInvalidoException, CuentaInactivaException,
			SaldoInsuficienteException, LimiteSobrepasadoException {
		validarMonto(monto);
		validarEstadoCuenta();
		titular.verificarContrasena(contrasena);
		validarSaldoSuficiente(monto);
		validarLimiteDiario(monto);

		saldo -= monto;
		movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto, "Retiro en efectivo", null));
	}

	public void transferir(Cuenta destino, double monto, String contrasena)
			throws MontoInvalidoException, CuentaInactivaException, SaldoInsuficienteException, CuentaNoEncontradaException {
		validarMonto(monto);
		validarEstadoCuenta();
		titular.verificarContrasena(contrasena);
		validarSaldoSuficiente(monto);
		validarTransferencia(destino, monto);

		// Realizar transferencia
		this.saldo -= monto;
		destino.saldo += monto;

		// Registrar movimientos
		this.movimientos.add(new Movimiento(TipoMovimiento.TRANSFERENCIA_ENVIADA, monto,
				"Transferencia a cuenta: " + destino.getNumeroCuenta(), destino.getNumeroCuenta()));

		destino.movimientos.add(new Movimiento(TipoMovimiento.TRANSFERENCIA_RECIBIDA, monto,
				"Transferencia desde cuenta: " + this.getNumeroCuenta(), this.getNumeroCuenta()));
	}

	// Métodos de validación
	private void validarMonto(double monto) throws Excepciones.MontoInvalidoException {
		if (monto <= 0) {
			throw new Excepciones.MontoInvalidoException("El monto debe ser mayor a cero");
		}

		// Validación específica para Colombia: depósitos mínimos de $1
		if (monto < 1) {
			throw new Excepciones.MontoInvalidoException("El monto mínimo permitido es $1");
		}
	}

	private void validarSaldoSuficiente(double monto) throws Excepciones.SaldoInsuficienteException {
		if (saldo < monto) {
			throw new Excepciones.SaldoInsuficienteException("Saldo insuficiente. Disponible: $" + saldo);
		}
	}

	private void validarLimiteDiario(double monto) throws Excepciones.LimiteSobrepasadoException {
		double totalRetiradoHoy = movimientos.stream().filter(m -> m.esRetiro())
				.filter(m -> m.getFechaHora().toLocalDate().equals(LocalDate.now())).mapToDouble(Movimiento::getMonto)
				.sum();

		if (totalRetiradoHoy + monto > limiteDiarioRetiro) {
			double disponible = limiteDiarioRetiro - totalRetiradoHoy;
			throw new Excepciones.LimiteSobrepasadoException(
					"Límite diario de retiro excedido. Disponible: $" + disponible);
		}
	}

	private void validarTransferencia(Cuenta destino, double monto) throws CuentaNoEncontradaException, CuentaInactivaException, MontoInvalidoException  {
		if (destino == null) {
			throw new Excepciones.CuentaNoEncontradaException("Cuenta destino no existe");
		}

		if (!destino.isActiva()) {
			throw new Excepciones.CuentaInactivaException("La cuenta destino está inactiva");
		}

		if (this.equals(destino)) {
			throw new Excepciones.MontoInvalidoException("No puede realizar transferencias a la misma cuenta");
		}
	}

	private void validarEstadoCuenta() throws Excepciones.CuentaInactivaException {
		if (!activa) {
			throw new Excepciones.CuentaInactivaException("La cuenta está inactiva");
		}
	}

	// Métodos de gestión de cuenta
	public void desactivarCuenta() {
		this.activa = false;
	}

	public void reactivarCuenta() {
		this.activa = true;
	}

	public void setLimiteDiarioRetiro(double nuevoLimite) {
		this.limiteDiarioRetiro = nuevoLimite;
	}

	public double getLimiteDiarioRetiro() {
		return limiteDiarioRetiro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cuenta cuenta = (Cuenta) o;
		return numeroCuenta.equals(cuenta.numeroCuenta);
	}

	@Override
	public int hashCode() {
		return numeroCuenta.hashCode();
	}
}