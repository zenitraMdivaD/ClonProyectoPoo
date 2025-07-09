package main;
import kernel.SistemaFinanciero;

import javax.swing.SwingUtilities;

import gui.*;
import persistencia.PersistenciaDatos;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaFinanciero sistema = PersistenciaDatos.cargarSistema();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                PersistenciaDatos.guardarSistema(sistema);
            }));

            PlataformaFinancieraUI ventana = new PlataformaFinancieraUI(sistema);
            ventana.setVisible(true);
        });
    }
}
