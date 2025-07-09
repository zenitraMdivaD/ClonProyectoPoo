package gui;

import javax.swing.*;
import kernel.*;
import complementos.Excepciones;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaDeposito extends JFrame {
	private static final long serialVersionUID = 1L;

	public VentanaDeposito(SistemaFinanciero sistema, Cuenta cuenta) {
		getContentPane().setBackground(new Color(128, 255, 255));
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		setTitle("Depósito");
		setSize(383, 222);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{47, 200, 35, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 25, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JLabel lblMonto = new JLabel("Monto a depositar:");
		lblMonto.setBackground(new Color(240, 240, 240));
		lblMonto.setFont(new Font("Corbel", Font.BOLD, 20));
		GridBagConstraints gbc_lblMonto = new GridBagConstraints();
		gbc_lblMonto.anchor = GridBagConstraints.NORTH;
		gbc_lblMonto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonto.gridx = 1;
		gbc_lblMonto.gridy = 1;
		getContentPane().add(lblMonto, gbc_lblMonto);

		JTextField txtMonto = new JTextField();
		txtMonto.setBackground(new Color(128, 255, 128));
		GridBagConstraints gbc_txtMonto = new GridBagConstraints();
		gbc_txtMonto.anchor = GridBagConstraints.NORTH;
		gbc_txtMonto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMonto.insets = new Insets(0, 0, 5, 5);
		gbc_txtMonto.gridx = 1;
		gbc_txtMonto.gridy = 2;
		getContentPane().add(txtMonto, gbc_txtMonto);

		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.setBackground(new Color(128, 255, 128));
		btnDepositar.setFont(new Font("Corbel", Font.BOLD, 16));
		btnDepositar.addActionListener(e -> {
			try {
				double monto = Double.parseDouble(txtMonto.getText());

				sistema.realizarDeposito(cuenta.getNumeroCuenta(), monto);
				JOptionPane.showMessageDialog(this, "Depósito exitoso.");
				dispose();
				new VentanaMenuPrincipal(sistema, cuenta).setVisible(true);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Excepciones.MontoInvalidoException |
			         Excepciones.CuentaNoEncontradaException |
			         Excepciones.CuentaInactivaException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JLabel lblNoTePreocupes = new JLabel("No te preocupes, está a salvo con nosotros ;)");
		lblNoTePreocupes.setFont(new Font("Corbel", Font.BOLD, 12));
		GridBagConstraints gbc_lblNoTePreocupes = new GridBagConstraints();
		gbc_lblNoTePreocupes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoTePreocupes.gridx = 1;
		gbc_lblNoTePreocupes.gridy = 3;
		getContentPane().add(lblNoTePreocupes, gbc_lblNoTePreocupes);

		GridBagConstraints gbc_btnDepositar = new GridBagConstraints();
		gbc_btnDepositar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDepositar.fill = GridBagConstraints.VERTICAL;
		gbc_btnDepositar.gridx = 1;
		gbc_btnDepositar.gridy = 4;
		getContentPane().add(btnDepositar, gbc_btnDepositar);
	}
}
