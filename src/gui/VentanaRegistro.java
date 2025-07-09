package gui;

import javax.swing.*;
import kernel.*;
import java.awt.Color;
import java.awt.Font;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public VentanaRegistro(SistemaFinanciero sistema) {
		getContentPane().setBackground(new Color(128, 255, 128));
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		setTitle("Registro de Usuario");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDoc = new JLabel("Documento:");
		lblDoc.setFont(new Font("Corbel", Font.BOLD, 16));
		lblDoc.setBounds(30, 30, 100, 17);
		getContentPane().add(lblDoc);

		JTextField txtDoc = new JTextField();
		txtDoc.setBackground(new Color(128, 255, 255));
		txtDoc.setFont(new Font("Corbel", Font.BOLD, 16));
		txtDoc.setBounds(150, 27, 200, 20);
		getContentPane().add(txtDoc);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Corbel", Font.BOLD, 16));
		lblNombre.setBounds(30, 70, 100, 17);
		getContentPane().add(lblNombre);

		JTextField txtNombre = new JTextField();
		txtNombre.setBackground(new Color(128, 255, 255));
		txtNombre.setFont(new Font("Corbel", Font.BOLD, 16));
		txtNombre.setBounds(150, 67, 200, 20);
		getContentPane().add(txtNombre);

		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Corbel", Font.BOLD, 16));
		lblPass.setBounds(30, 110, 100, 20);
		getContentPane().add(lblPass);

		JPasswordField txtPass = new JPasswordField();
		txtPass.setBackground(new Color(128, 255, 255));
		txtPass.setFont(new Font("Corbel", Font.BOLD, 16));
		txtPass.setBounds(150, 107, 200, 20);
		getContentPane().add(txtPass);

		JLabel lblCorreo = new JLabel("Correo Electrónico:");
		lblCorreo.setFont(new Font("Corbel", Font.BOLD, 16));
		lblCorreo.setBounds(10, 149, 143, 17);
		getContentPane().add(lblCorreo);

		JTextField txtCorreo = new JTextField();
		txtCorreo.setBackground(new Color(128, 255, 255));
		txtCorreo.setFont(new Font("Corbel", Font.BOLD, 16));
		txtCorreo.setBounds(150, 147, 200, 20);
		getContentPane().add(txtCorreo);

		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setFont(new Font("Corbel", Font.BOLD, 16));
		lblTel.setBounds(30, 184, 100, 20);
		getContentPane().add(lblTel);

		JTextField txtTel = new JTextField();
		txtTel.setBackground(new Color(128, 255, 255));
		txtTel.setFont(new Font("Corbel", Font.BOLD, 16));
		txtTel.setBounds(150, 187, 200, 20);
		getContentPane().add(txtTel);

		JButton btnRegistrar = new JButton("¡Empieza ahora!");
		btnRegistrar.setFont(new Font("Corbel", Font.BOLD, 16));
		btnRegistrar.setBackground(new Color(128, 255, 255));
		btnRegistrar.setBounds(108, 241, 152, 25);
		btnRegistrar.addActionListener(e -> {
			try {
				Usuario nuevo = new Usuario(txtDoc.getText(), txtNombre.getText(), new String(txtPass.getPassword()), txtCorreo.getText(), txtTel.getText());
				sistema.registrarUsuario(nuevo);
				sistema.crearCuenta(nuevo, "CT" + txtDoc.getText());
				JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
				dispose();
				new PlataformaFinancieraUI(sistema).setVisible(true);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		getContentPane().add(btnRegistrar);
		
		JLabel lblNewLabel = new JLabel("Nunca es tarde para empezar a cuidar tu dinero  ");
		lblNewLabel.setFont(new Font("Corbel", Font.BOLD, 16));
		lblNewLabel.setBounds(30, 215, 329, 20);
		getContentPane().add(lblNewLabel);
	}
}
