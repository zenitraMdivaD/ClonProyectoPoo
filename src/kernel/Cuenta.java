package kernel;

import java.io.Serializable;
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

/**
 * Representa una cuenta bancaria con operaciones financieras básicas.
 * Gestiona depósitos, retiros, transferencias y consultas de movimientos.
 * 
 * <p>Principios POO aplicados:
 * <ul>
 *   <li>Encapsulamiento: Atributos privados con métodos públicos controlados</li>
 *   <li>Composición: Relación con objetos Usuario y Movimiento</li>
 *   <li>Abstracción: Expone operaciones bancarias sin revelar implementación interna</li>
 *   <li>Polimorfismo: Sobrecarga de métodos depositar()</li>
 * </ul>
 */
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULAMIENTO: Atributos privados para control de acceso
    private final String numeroCuenta;
    private final Usuario titular;  // COMPOSICIÓN: Relación con Usuario
    private double saldo;
    private final List<Movimiento> movimientos; // COMPOSICIÓN: Lista de Movimientos
    private boolean activa;
    private double limiteDiarioRetiro = 1000000;

    /**
     * Constructor para crear una nueva cuenta.
     * @param numeroCuenta Número único de la cuenta
     * @param titular Usuario propietario de la cuenta
     */
    public Cuenta(String numeroCuenta, Usuario titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
        this.activa = true;
    }

    // ENCAPSULAMIENTO: Métodos getter para acceso controlado
    public String getNumeroCuenta() { return numeroCuenta; }
    public Usuario getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public boolean isActiva() { return activa; }
    
    /**
     * Obtiene una copia de los movimientos (ENCAPSULAMIENTO: Copia defensiva)
     * @return Lista de movimientos
     */
    public List<Movimiento> getMovimientos() { 
        return new ArrayList<>(movimientos); 
    }

    /**
     * Obtiene los movimientos más recientes.
     * @param cantidad Número de movimientos a obtener
     * @return Lista de movimientos recientes
     */
    public List<Movimiento> getMovimientosRecientes(int cantidad) {
        int startIndex = Math.max(0, movimientos.size() - cantidad);
        return new ArrayList<>(movimientos.subList(startIndex, movimientos.size()));
    }

    /**
     * Obtiene movimientos por fecha específica.
     * @param fecha Fecha a consultar
     * @return Lista de movimientos en esa fecha
     */
    public List<Movimiento> getMovimientosPorFecha(LocalDate fecha) {
        return movimientos.stream()
                .filter(m -> m.getFechaHora().toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    /**
     * Realiza un depósito en la cuenta.
     * @param monto Cantidad a depositar
     * @throws MontoInvalidoException Si el monto es inválido
     * @throws CuentaInactivaException Si la cuenta está inactiva
     */
    public void depositar(double monto) throws MontoInvalidoException, CuentaInactivaException {
        validarMonto(monto);
        validarEstadoCuenta();

        saldo += monto;
        // COMPOSICIÓN: Creación de nuevo objeto Movimiento
        movimientos.add(new Movimiento(TipoMovimiento.DEPOSITO, monto, "Depósito en efectivo", null));
    }

    // POLIMORFISMO: Sobrecarga del método depositar()
    public void depositar(double monto, String origen) throws MontoInvalidoException, CuentaInactivaException {
        validarMonto(monto);
        validarEstadoCuenta();

        saldo += monto;
        movimientos.add(new Movimiento(TipoMovimiento.DEPOSITO, monto, "Depósito desde cuenta: " + origen, origen));
    }

    /**
     * Realiza un retiro de la cuenta.
     * @param monto Cantidad a retirar
     * @param contrasena Contraseña de verificación
     * @throws SaldoInsuficienteException Si no hay saldo suficiente
     * @throws LimiteSobrepasadoException Si se supera el límite diario
     */
    public void retirar(double monto, String contrasena) throws MontoInvalidoException, CuentaInactivaException,
            SaldoInsuficienteException, LimiteSobrepasadoException {
        validarMonto(monto);
        validarEstadoCuenta();
        titular.verificarContrasena(contrasena); // DELEGACIÓN: Uso de método de otro objeto
        validarSaldoSuficiente(monto);
        validarLimiteDiario(monto);

        saldo -= monto;
        movimientos.add(new Movimiento(TipoMovimiento.RETIRO, monto, "Retiro en efectivo", null));
    }

    /**
     * Transfiere fondos a otra cuenta.
     * @param destino Cuenta destino
     * @param monto Cantidad a transferir
     * @param contrasena Contraseña de verificación
     */
    public void transferir(Cuenta destino, double monto, String contrasena)
            throws MontoInvalidoException, CuentaInactivaException, 
                   SaldoInsuficienteException, CuentaNoEncontradaException {
        validarMonto(monto);
        validarEstadoCuenta();
        titular.verificarContrasena(contrasena);
        validarSaldoSuficiente(monto);
        validarTransferencia(destino, monto);

        this.saldo -= monto;
        destino.saldo += monto;

        this.movimientos.add(new Movimiento(TipoMovimiento.TRANSFERENCIA_ENVIADA, monto,
                "Transferencia a cuenta: " + destino.getNumeroCuenta(), destino.getNumeroCuenta()));

        destino.movimientos.add(new Movimiento(TipoMovimiento.TRANSFERENCIA_RECIBIDA, monto,
                "Transferencia desde cuenta: " + this.getNumeroCuenta(), this.getNumeroCuenta()));
    }

    // Métodos de validación privados (ENCAPSULAMIENTO)
    private void validarMonto(double monto) throws Excepciones.MontoInvalidoException {
        if (monto <= 0) {
            throw new Excepciones.MontoInvalidoException("El monto debe ser mayor a cero");
        }
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
        double totalRetiradoHoy = movimientos.stream()
                .filter(m -> m.esRetiro())
                .filter(m -> m.getFechaHora().toLocalDate().equals(LocalDate.now()))
                .mapToDouble(Movimiento::getMonto)
                .sum();

        if (totalRetiradoHoy + monto > limiteDiarioRetiro) {
            double disponible = limiteDiarioRetiro - totalRetiradoHoy;
            throw new Excepciones.LimiteSobrepasadoException(
                    "Límite diario de retiro excedido. Disponible: $" + disponible);
        }
    }

    private void validarTransferencia(Cuenta destino, double monto) 
            throws CuentaNoEncontradaException, CuentaInactivaException, MontoInvalidoException {
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
    public void desactivarCuenta() { this.activa = false; }
    public void reactivarCuenta() { this.activa = true; }
    
    public void setLimiteDiarioRetiro(double nuevoLimite) { 
        this.limiteDiarioRetiro = nuevoLimite; 
    }
    public double getLimiteDiarioRetiro() { 
        return limiteDiarioRetiro; 
    }

    // SOBRESCRITURA: Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return numeroCuenta.equals(cuenta.numeroCuenta);
    }

    @Override
    public int hashCode() {
        return numeroCuenta.hashCode();
    }
    
    // Método especial para inicialización (usar con cuidado)
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
