package kernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import complementos.Excepciones;
import complementos.Excepciones.AutenticacionFallidaException;
import complementos.Excepciones.CuentaInactivaException;
import complementos.Excepciones.CuentaNoEncontradaException;
import complementos.Excepciones.LimiteSobrepasadoException;
import complementos.Excepciones.MontoInvalidoException;
import complementos.Excepciones.SaldoInsuficienteException;
import complementos.Excepciones.UsuarioNoEncontradoException;

public class SistemaFinanciero {
	private Map<String, Usuario> usuarios;
	private Map<String, Cuenta> cuentas;
	private List<String> bitacora;

	public SistemaFinanciero() {
		this.usuarios = new HashMap<>();
		this.cuentas = new HashMap<>();
		this.bitacora = new ArrayList<>();
	}

	// Registro de usuarios
	public void registrarUsuario(Usuario usuario) throws Excepciones.DocumentoDuplicadoException {
		if (usuarios.containsKey(usuario.getDocumentoIdentidad())) {
			registrarEvento("Intento de registro duplicado: " + usuario.getDocumentoIdentidad());
			throw new Excepciones.DocumentoDuplicadoException("Usuario ya registrado");
		}
		usuarios.put(usuario.getDocumentoIdentidad(), usuario);
		registrarEvento("Usuario registrado: " + usuario.getDocumentoIdentidad());
	}

	// Creación de cuentas
	public void crearCuenta(Usuario usuario, String numeroCuenta)
			throws UsuarioNoEncontradoException, CuentaNoEncontradaException {
		if (!usuarios.containsValue(usuario)) {
			throw new Excepciones.UsuarioNoEncontradoException("Usuario no registrado");
		}
		if (cuentas.containsKey(numeroCuenta)) {
			registrarEvento("Intento de cuenta duplicada: " + numeroCuenta);
			throw new Excepciones.CuentaNoEncontradaException("Cuenta ya existe");
		}

		Cuenta nuevaCuenta = new Cuenta(numeroCuenta, usuario);
		cuentas.put(numeroCuenta, nuevaCuenta);
		registrarEvento("Cuenta creada: " + numeroCuenta);
	}

	// Autenticación
	public Cuenta autenticarUsuario(String documento, String contrasena)
			throws UsuarioNoEncontradoException, AutenticacionFallidaException, Exception {
		Usuario usuario = usuarios.get(documento);
		if (usuario == null) {
			registrarEvento("Intento de acceso con documento no registrado: " + documento);
			throw new Excepciones.UsuarioNoEncontradoException("Usuario no encontrado");
		}
		if (!usuario.verificarContrasena(contrasena)) {
			registrarEvento("Contraseña incorrecta para: " + documento);
			throw new Excepciones.AutenticacionFallidaException("Credenciales inválidas");
		}

		// Buscar cuenta asociada (simplificado: primera cuenta encontrada)
		Cuenta cuentaUsuario = cuentas.values().stream().filter(c -> c.getTitular().equals(usuario)).findFirst()
				.orElseThrow(() -> new Excepciones.CuentaNoEncontradaException("Cuenta no existe"));

		registrarEvento("Sesión iniciada: " + documento);
		return cuentaUsuario;
	}

	// Operaciones financieras delegadas
	public void realizarDeposito(String numeroCuenta, double monto) throws CuentaNoEncontradaException, MontoInvalidoException, CuentaInactivaException {
		Cuenta cuenta = obtenerCuenta(numeroCuenta);
		cuenta.depositar(monto);
		registrarEvento("Depósito realizado: " + numeroCuenta + " - " + monto);
	}

	public void realizarRetiro(String numeroCuenta, double monto, String contrasena) throws CuentaNoEncontradaException, MontoInvalidoException, CuentaInactivaException, SaldoInsuficienteException, LimiteSobrepasadoException{
		Cuenta cuenta = obtenerCuenta(numeroCuenta);
		cuenta.retirar(monto, contrasena);
		registrarEvento("Retiro realizado: " + numeroCuenta + " - " + monto);
	}

	public void realizarTransferencia(String origen, String destino, double monto, String contrasena) throws CuentaNoEncontradaException, MontoInvalidoException, CuentaInactivaException, SaldoInsuficienteException
			 {
		Cuenta cuentaOrigen = obtenerCuenta(origen);
		Cuenta cuentaDestino = obtenerCuenta(destino);
		cuentaOrigen.transferir(cuentaDestino, monto, contrasena);
		registrarEvento("Transferencia realizada: " + origen + " -> " + destino + " - " + monto);
	}

	// Consultas
	public double consultarSaldo(String numeroCuenta) throws CuentaNoEncontradaException  {
		return obtenerCuenta(numeroCuenta).getSaldo();
	}

	public List<Movimiento> consultarHistorial(String numeroCuenta) throws CuentaNoEncontradaException  {
		return obtenerCuenta(numeroCuenta).getMovimientos();
	}

	// Métodos internos
	private Cuenta obtenerCuenta(String numeroCuenta) throws Excepciones.CuentaNoEncontradaException {
		Cuenta cuenta = cuentas.get(numeroCuenta);
		if (cuenta == null) {
			throw new Excepciones.CuentaNoEncontradaException("Cuenta no existe");
		}
		return cuenta;
	}

	private void registrarEvento(String evento) {
		String logEntry = java.time.LocalDateTime.now() + " - " + evento;
		bitacora.add(logEntry);
	}

	// Getters para bitácora (usado en reportes)
	public List<String> getBitacora() {
		return new ArrayList<>(bitacora);
	}
}