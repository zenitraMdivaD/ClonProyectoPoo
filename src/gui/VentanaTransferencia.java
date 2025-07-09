package gui;

import javax.swing.*;
import kernel.*;
import complementos.Excepciones;
import java.awt.Color;
import java.awt.Font;

public class VentanaTransferencia extends JFrame {
	private static final long serialVersionUID = 1L;
	private SistemaFinanciero sistema;
	private Cuenta cuenta;
	/**
	 * @wbp.nonvisual location=71,399
	 */
	private final JLabel label = new JLabel("New label");

	public VentanaTransferencia(SistemaFinanciero sistema, Cuenta cuenta) {
		label.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 18));
		getContentPane().setBackground(new Color(128, 255, 255));
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		this.sistema = sistema;
		this.cuenta = cuenta;

		setTitle("Transferencia");
		setSize(350, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDestino = new JLabel("Cuenta destino:");
		lblDestino.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 18));
		lblDestino.setBounds(23, 30, 127, 25);
		getContentPane().add(lblDestino);

		JTextField txtDestino = new JTextField();
		txtDestino.setFont(new Font("Corbel", Font.PLAIN, 16));
		txtDestino.setBackground(new Color(128, 255, 128));
		txtDestino.setBounds(150, 27, 150, 20);
		getContentPane().add(txtDestino);

		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 18));
		lblMonto.setBounds(23, 70, 127, 17);
		getContentPane().add(lblMonto);

		JTextField txtMonto = new JTextField();
		txtMonto.setBackground(new Color(128, 255, 128));
		txtMonto.setFont(new Font("Corbel", Font.PLAIN, 16));
		txtMonto.setBounds(150, 67, 150, 20);
		getContentPane().add(txtMonto);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 18));
		lblPass.setBounds(23, 110, 127, 20);
		getContentPane().add(lblPass);

		JPasswordField txtPass = new JPasswordField();
		txtPass.setFont(new Font("Corbel", Font.PLAIN, 16));
		txtPass.setBackground(new Color(128, 255, 128));
		txtPass.setBounds(150, 107, 150, 20);
		getContentPane().add(txtPass);

		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.setBackground(new Color(128, 255, 128));
		btnTransferir.setFont(new Font("Corbel", Font.BOLD, 15));
		btnTransferir.setBounds(100, 163, 120, 25);
		btnTransferir.addActionListener(e -> {
			try {
				sistema.realizarTransferencia(cuenta.getNumeroCuenta(), "CT" + txtDestino.getText(), Double.parseDouble(txtMonto.getText()), new String(txtPass.getPassword()));
				JOptionPane.showMessageDialog(this, "Transferencia exitosa.");
				new VentanaMenuPrincipal(sistema, cuenta).setVisible(true);
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		getContentPane().add(btnTransferir);
		
		JLabel lblHasFelzA = new JLabel("Has felíz a alguíen con este regalo :D");
		lblHasFelzA.setFont(new Font("Corbel", Font.BOLD, 17));
		lblHasFelzA.setBounds(33, 141, 279, 25);
		getContentPane().add(lblHasFelzA);
	}
}
