package kernel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private final TipoMovimiento tipo;
    private final double monto;
    private final LocalDateTime fechaHora;
    private final String descripcion;
    private final String cuentaRelacionada;

    public Movimiento(TipoMovimiento tipo, double monto, String descripcion, String cuentaRelacionada) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = LocalDateTime.now();
        this.descripcion = (descripcion != null) ? descripcion : "";
        this.cuentaRelacionada = (cuentaRelacionada != null) ? cuentaRelacionada : "";
    }

    public TipoMovimiento getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getDescripcion() { return descripcion; }
    public String getCuentaRelacionada() { return cuentaRelacionada; }
    
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