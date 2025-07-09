package kernel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una transacción financiera en el sistema.
 * 
 * <p>Principios POO aplicados:
 * <ul>
 *   <li>Encapsulamiento: Atributos finales e inmutables</li>
 *   <li>Polimorfismo: Uso de tipos concretos de TipoMovimiento</li>
 *   <li>Inmutabilidad: Una vez creado no puede modificarse</li>
 * </ul>
 */
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULAMIENTO: Atributos finales para inmutabilidad
    private final TipoMovimiento tipo; // POLIMORFISMO: Puede ser cualquiera de los subtipos
    private final double monto;
    private final LocalDateTime fechaHora;
    private final String descripcion;
    private final String cuentaRelacionada;

    /**
     * Constructor para crear un nuevo movimiento.
     * @param tipo Tipo de movimiento (DEPOSITO, RETIRO, etc.)
     * @param monto Cantidad involucrada
     * @param descripcion Detalles de la operación
     * @param cuentaRelacionada Cuenta relacionada (para transferencias)
     */
    public Movimiento(TipoMovimiento tipo, double monto, String descripcion, String cuentaRelacionada) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.descripcion = (descripcion != null) ? descripcion : "";
        this.cuentaRelacionada = (cuentaRelacionada != null) ? cuentaRelacionada : "";
    }

    // ENCAPSULAMIENTO: Getters para acceso controlado
    public TipoMovimiento getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getDescripcion() { return descripcion; }
    public String getCuentaRelacionada() { return cuentaRelacionada; }
    
    // POLIMORFISMO: Métodos que dependen del tipo concreto de movimiento
    public boolean esDeposito() {
        return tipo instanceof TipoMovimiento.Deposito;
    }
    
    public boolean esRetiro() {
        return tipo instanceof TipoMovimiento.Retiro;
    }
    
    public boolean esTransferenciaEnviada() {
        return tipo instanceof TipoMovimiento.TransferenciaEnviada;
    }
    
    public boolean esTransferenciaRecibida() {
        return tipo instanceof TipoMovimiento.TransferenciaRecibida;
    }
    
    // SOBRESCRITURA: Método toString para representación legible
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        return String.format("%s | %-25s | %-45s | %12s | %s",
                fechaHora.format(dateFormatter),
                tipo.getNombre(),
                descripcion,
                String.format("$%,.2f", monto),
                cuentaRelacionada.isEmpty() ? "N/A" : cuentaRelacionada,
                fechaHora.format(timeFormatter));
    }
}
