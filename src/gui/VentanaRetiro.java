package gui;

import javax.swing.*;
import kernel.*;
import complementos.Excepciones.*;
import java.awt.*;

public class VentanaRetiro extends JFrame {
	private static final long serialVersionUID = 1L;
	private SistemaFinanciero sistema;
	private Cuenta cuenta;

	public VentanaRetiro(SistemaFinanciero sistema, Cuenta cuenta) {
		getContentPane().setBackground(new Color(128, 255, 255));
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		this.sistema = sistema;
		this.cuenta = cuenta;

		setTitle("Retiro de Dinero");
		setSize(382, 205);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 120, 0, 150, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 43, 0, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JLabel lblMonto = new JLabel("Monto a retirar:");
		lblMonto.setFont(new Font("Corbel", Font.BOLD, 18));
		GridBagConstraints gbc_lblMonto = new GridBagConstraints();
		gbc_lblMonto.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_txtMonto.gridx = 3;
		gbc_txtMonto.gridy = 1;
		getContentPane().add(txtMonto, gbc_txtMonto);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Corbel", Font.BOLD, 18));
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 1;
		gbc_lblPass.gridy = 2;
		getContentPane().add(lblPass, gbc_lblPass);

		JPasswordField txtPass = new JPasswordField();
		txtPass.setBackground(new Color(128, 255, 128));
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.anchor = GridBagConstraints.NORTH;
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.insets = new Insets(0, 0, 5, 5);
		gbc_txtPass.gridx = 3;
		gbc_txtPass.gridy = 2;
		getContentPane().add(txtPass, gbc_txtPass);

		JButton btnRetirar = new JButton("Retirar");
		btnRetirar.setBackground(new Color(128, 255, 128));
		btnRetirar.setFont(new Font("Corbel", Font.BOLD, 16));
		btnRetirar.addActionListener(e -> {
			try {
				double monto = Double.parseDouble(txtMonto.getText());
				String pass = new String(txtPass.getPassword());

				sistema.realizarRetiro(cuenta.getNumeroCuenta(), monto, pass);
				JOptionPane.showMessageDialog(this, "Retiro exitoso.");
				dispose();
				new VentanaMenuPrincipal(sistema, cuenta).setVisible(true);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JLabel lblEsperamosHaberloCuidado = new JLabel("Esperamos haberlo cuidado bien :)");
		lblEsperamosHaberloCuidado.setFont(new Font("Corbel", Font.BOLD, 16));
		GridBagConstraints gbc_lblEsperamosHaberloCuidado = new GridBagConstraints();
		gbc_lblEsperamosHaberloCuidado.gridwidth = 3;
		gbc_lblEsperamosHaberloCuidado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEsperamosHaberloCuidado.gridx = 1;
		gbc_lblEsperamosHaberloCuidado.gridy = 3;
		getContentPane().add(lblEsperamosHaberloCuidado, gbc_lblEsperamosHaberloCuidado);

		GridBagConstraints gbc_btnRetirar = new GridBagConstraints();
		gbc_btnRetirar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRetirar.fill = GridBagConstraints.VERTICAL;
		gbc_btnRetirar.gridwidth = 3;
		gbc_btnRetirar.gridx = 1;
		gbc_btnRetirar.gridy = 4;
		getContentPane().add(btnRetirar, gbc_btnRetirar);
	}
}
