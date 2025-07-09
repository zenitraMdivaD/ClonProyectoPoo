package kernel;

import java.io.Serializable;

/**
 * Jerarquía de tipos de movimientos bancarios usando patrón de objetos constantes.
 * 
 * <p>Principios POO aplicados:
 * <ul>
 *   <li>HERENCIA: Subtipos específicos de movimientos</li>
 *   <li>POLIMORFISMO: Comportamiento según tipo concreto</li>
 *   <li>INMUTABILIDAD: Instancias únicas y constantes</li>
 *   <li>PATRÓN OBJETOS CONSTANTES: Para tipos predefinidos</li>
 * </ul>
 */
public abstract class TipoMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;

    // HERENCIA: Clase base abstracta para tipos específicos
    protected TipoMovimiento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // HERENCIA: Subclases internas para tipos concretos
    
    /**
     * Representa un movimiento de depósito.
     */
    public static final class Deposito extends TipoMovimiento {
        private static final long serialVersionUID = 1L;
        public Deposito() { super("DEPÓSITO"); }
    }

    /**
     * Representa un movimiento de retiro.
     */
    public static final class Retiro extends TipoMovimiento {
        private static final long serialVersionUID = 1L;
        public Retiro() { super("RETIRO"); }
    }

    /**
     * Representa una transferencia enviada.
     */
    public static final class TransferenciaEnviada extends TipoMovimiento {
        private static final long serialVersionUID = 1L;
        public TransferenciaEnviada() { super("TRANSFERENCIA_ENVIADA"); }
    }

    /**
     * Representa una transferencia recibida.
     */
    public static final class TransferenciaRecibida extends TipoMovimiento {
        private static final long serialVersionUID = 1L;
        public TransferenciaRecibida() { super("TRANSFERENCIA_RECIBIDA"); }
    }

    // POLIMORFISMO: Instancias únicas para cada tipo (PATRÓN OBJETOS CONSTANTES)
    public static final Deposito DEPOSITO = new Deposito();
    public static final Retiro RETIRO = new Retiro();
    public static final TransferenciaEnviada TRANSFERENCIA_ENVIADA = new TransferenciaEnviada();
    public static final TransferenciaRecibida TRANSFERENCIA_RECIBIDA = new TransferenciaRecibida();
}
