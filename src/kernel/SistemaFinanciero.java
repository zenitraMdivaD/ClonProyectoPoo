package kernel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import complementos.Excepciones;
import complementos.Excepciones.AutenticacionFallidaException;
import complementos.Excepciones.CuentaInactivaException;
import complementos.Excepciones.CuentaNoEncontradaException;
import complementos.Excepciones.DocumentoDuplicadoException;
import complementos.Excepciones.LimiteSobrepasadoException;
import complementos.Excepciones.MontoInvalidoException;
import complementos.Excepciones.SaldoInsuficienteException;
import complementos.Excepciones.UsuarioNoEncontradoException;

/**
 * Sistema central que gestiona todas las operaciones financieras.
 * 
 * <p>Principios POO aplicados:
 * <ul>
 *   <li>Encapsulamiento: Colecciones privadas con acceso controlado</li>
 *   <li>Composición: Contiene usuarios y cuentas</li>
 *   <li>Delegación: Delega operaciones a objetos Cuenta</li>
 *   <li>Singleton: Podría implementarse como singleton (aunque aquí no se hace)</li>
 * </ul>
 */
public class SistemaFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULAMIENTO: Estructuras de datos privadas
    private Map<String, Usuario> usuarios; // COMPOSICIÓN: Administra objetos Usuario
    private Map<String, Cuenta> cuentas;   // COMPOSICIÓN: Administra objetos Cuenta
    private List<String> bitacora;         // Registro de eventos

    /**
     * Constructor que inicializa las estructuras de datos.
     */
    public SistemaFinanciero() {
        this.usuarios = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.bitacora = new ArrayList<>();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario Usuario a registrar
     * @throws DocumentoDuplicadoException Si el usuario ya existe
     */
    public void registrarUsuario(Usuario usuario) throws DocumentoDuplicadoException {
        if (usuarios.containsKey(usuario.getDocumentoIdentidad())) {
            registrarEvento("Intento de registro duplicado: " + usuario.getDocumentoIdentidad());
            throw new DocumentoDuplicadoException("Usuario ya registrado");
        }
        usuarios.put(usuario.getDocumentoIdentidad(), usuario);
        registrarEvento("Usuario registrado: " + usuario.getDocumentoIdentidad());
    }

    /**
     * Crea una nueva cuenta asociada a un usuario.
     * @param usuario Usuario titular
     * @param numeroCuenta Número de cuenta único
     * @throws UsuarioNoEncontradoException Si el usuario no existe
     * @throws CuentaNoEncontradaException Si la cuenta ya existe
     */
    public void crearCuenta(Usuario usuario, String numeroCuenta)
            throws UsuarioNoEncontradoException, CuentaNoEncontradaException {
        if (!usuarios.containsValue(usuario)) {
            throw new UsuarioNoEncontradoException("Usuario no registrado");
        }
        if (cuentas.containsKey(numeroCuenta)) {
            registrarEvento("Intento de cuenta duplicada: " + numeroCuenta);
            throw new CuentaNoEncontradaException("Cuenta ya existe");
        }

        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, usuario);
        cuentas.put(numeroCuenta, nuevaCuenta);
        registrarEvento("Cuenta creada: " + numeroCuenta);
    }

    /**
     * Autentica un usuario y devuelve su cuenta principal.
     * @param documento Documento de identidad
     * @param contrasena Contraseña del usuario
     * @return Cuenta asociada al usuario
     * @throws UsuarioNoEncontradoException Si el usuario no existe
     * @throws AutenticacionFallidaException Si la contraseña es incorrecta
     * @throws CuentaNoEncontradaException Si no tiene cuentas asociadas
     */
    public Cuenta autenticarUsuario(String documento, String contrasena)
            throws UsuarioNoEncontradoException, AutenticacionFallidaException, CuentaNoEncontradaException {
        Usuario usuario = usuarios.get(documento);
        if (usuario == null) {
            registrarEvento("Intento de acceso con documento no registrado: " + documento);
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        if (!usuario.verificarContrasena(contrasena)) {
            registrarEvento("Contraseña incorrecta para: " + documento);
            throw new AutenticacionFallidaException("Credenciales inválidas");
        }

        // Buscar cuenta asociada (simplificado: primera cuenta encontrada)
        Cuenta cuentaUsuario = cuentas.values().stream()
                .filter(c -> c.getTitular().equals(usuario))
                .findFirst()
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no existe"));

        registrarEvento("Sesión iniciada: " + documento);
        return cuentaUsuario;
    }

    // DELEGACIÓN: Las operaciones se delegan a los objetos Cuenta
    
    /**
     * Realiza un depósito en una cuenta.
     * @param numeroCuenta Cuenta destino
     * @param monto Cantidad a depositar
     */
    public void realizarDeposito(String numeroCuenta, double monto) 
            throws CuentaNoEncontradaException, MontoInvalidoException, CuentaInactivaException {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.depositar(monto); // DELEGACIÓN: Llamada a método de objeto Cuenta
        registrarEvento("Depósito realizado: " + numeroCuenta + " - " + monto);
    }

    /**
     * Realiza un retiro de una cuenta.
     * @param numeroCuenta Cuenta origen
     * @param monto Cantidad a retirar
     * @param contrasena Contraseña de verificación
     */
    public void realizarRetiro(String numeroCuenta, double monto, String contrasena) 
            throws CuentaNoEncontradaException, MontoInvalidoException, 
                   CuentaInactivaException, SaldoInsuficienteException, 
                   LimiteSobrepasadoException {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.retirar(monto, contrasena); // DELEGACIÓN
        registrarEvento("Retiro realizado: " + numeroCuenta + " - " + monto);
    }

    /**
     * Transfiere fondos entre cuentas.
     * @param origen Cuenta origen
     * @param destino Cuenta destino
     * @param monto Cantidad a transferir
     * @param contrasena Contraseña de verificación
     */
    public void realizarTransferencia(String origen, String destino, double monto, String contrasena) 
            throws CuentaNoEncontradaException, MontoInvalidoException, 
                   CuentaInactivaException, SaldoInsuficienteException {
        Cuenta cuentaOrigen = obtenerCuenta(origen);
        Cuenta cuentaDestino = obtenerCuenta(destino);
        cuentaOrigen.transferir(cuentaDestino, monto, contrasena); // DELEGACIÓN
        registrarEvento("Transferencia realizada: " + origen + " -> " + destino + " - " + monto);
    }

    // Consultas
    
    /**
     * Consulta el saldo de una cuenta.
     * @param numeroCuenta Número de cuenta
     * @return Saldo actual
     */
    public double consultarSaldo(String numeroCuenta) throws CuentaNoEncontradaException {
        return obtenerCuenta(numeroCuenta).getSaldo();
    }

    /**
     * Obtiene el historial completo de movimientos de una cuenta.
     * @param numeroCuenta Número de cuenta
     * @return Lista de movimientos
     */
    public List<Movimiento> consultarHistorial(String numeroCuenta) throws CuentaNoEncontradaException {
        return obtenerCuenta(numeroCuenta).getMovimientos();
    }

    // Métodos internos
    
    /**
     * Obtiene una cuenta por su número (ENCAPSULAMIENTO: Validación interna)
     * @param numeroCuenta Número de cuenta
     * @return Objeto Cuenta
     * @throws CuentaNoEncontradaException Si la cuenta no existe
     */
    private Cuenta obtenerCuenta(String numeroCuenta) throws CuentaNoEncontradaException {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Cuenta no existe");
        }
        return cuenta;
    }

    /**
     * Registra un evento en la bitácora (ENCAPSULAMIENTO: Acceso privado)
     * @param evento Descripción del evento
     */
    private void registrarEvento(String evento) {
        String logEntry = java.time.LocalDateTime.now() + " - " + evento;
        bitacora.add(logEntry);
    }

    // ENCAPSULAMIENTO: Getter con copia defensiva
    public List<String> getBitacora() {
        return new ArrayList<>(bitacora);
    }
    
    /**
     * Obtiene todas las cuentas de un usuario.
     * @param documento Documento del usuario
     * @return Lista de cuentas
     */
    public List<Cuenta> getCuentasUsuario(String documento) {
        return cuentas.values().stream()
                .filter(c -> c.getTitular().getDocumentoIdentidad().equals(documento))
                .collect(Collectors.toList());
    }
}
