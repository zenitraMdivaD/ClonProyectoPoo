package persistencia;

import java.io.*;

import javax.swing.ImageIcon;

import kernel.SistemaFinanciero;

public class PersistenciaDatos {
    private static final String RUTA_ARCHIVO = "sistema_financiero.dat";

    public static void guardarSistema(SistemaFinanciero sistema) {
		

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(sistema);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SistemaFinanciero cargarSistema() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            return (SistemaFinanciero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new SistemaFinanciero(); // Si no existe el archivo, retorna uno nuevo
        }
    }
}

