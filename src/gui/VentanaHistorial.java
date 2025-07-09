package gui;

import javax.swing.*;
import kernel.*;

import java.awt.BorderLayout;
import java.util.List;

public class VentanaHistorial extends JFrame {
	private static final long serialVersionUID = 1L;

	public VentanaHistorial(SistemaFinanciero sistema, Cuenta cuenta) {
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		setTitle("Historial de Movimientos");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		DefaultListModel<String> modelo = new DefaultListModel<>();
		JList<String> lista = new JList<>(modelo);
		add(new JScrollPane(lista), BorderLayout.CENTER);

		try {
			List<Movimiento> movimientos = sistema.consultarHistorial(cuenta.getNumeroCuenta());
			for (Movimiento m : movimientos) {
				modelo.addElement(m.toString());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
