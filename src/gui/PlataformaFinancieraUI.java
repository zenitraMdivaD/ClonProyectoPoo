package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import kernel.*;
import complementos.Excepciones;

public class PlataformaFinancieraUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDocumento;
	private JPasswordField txtContrasena;

	private SistemaFinanciero sistema; 


	public PlataformaFinancieraUI(SistemaFinanciero sistema) {
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		this.sistema=sistema;
		setTitle("Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 385);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 10, 109, 0};
		gbl_contentPane.rowHeights = new int[]{57, 20, 0, 0, 20, 33, 25, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
								
								JLabel label = new JLabel("");
								GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
								gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel.gridx = 1;
								gbc_lblNewLabel.gridy = 0;
								contentPane.add(label, gbc_lblNewLabel);
								
								ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/recursos/logo.png"));
								Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon iconoEscalado = new ImageIcon(imagenRedimensionada);
								label.setIcon(iconoEscalado);
																		
																		JLabel lblEsUnPlacer = new JLabel("Es un placer conocerte ;)");
																		lblEsUnPlacer.setFont(new Font("Corbel", Font.BOLD, 16));
																		GridBagConstraints gbc_lblEsUnPlacer = new GridBagConstraints();
																		gbc_lblEsUnPlacer.insets = new Insets(0, 0, 5, 5);
																		gbc_lblEsUnPlacer.gridx = 1;
																		gbc_lblEsUnPlacer.gridy = 1;
																		contentPane.add(lblEsUnPlacer, gbc_lblEsUnPlacer);
																		
																		JLabel lblIniciaSesinSi = new JLabel("Inicia sesión si ya nos conoces");
																		lblIniciaSesinSi.setFont(new Font("Corbel", Font.BOLD, 16));
																		GridBagConstraints gbc_lblIniciaSesinSi = new GridBagConstraints();
																		gbc_lblIniciaSesinSi.insets = new Insets(0, 0, 5, 5);
																		gbc_lblIniciaSesinSi.gridx = 1;
																		gbc_lblIniciaSesinSi.gridy = 2;
																		contentPane.add(lblIniciaSesinSi, gbc_lblIniciaSesinSi);
																		
																		JLabel lblOResgistratePara = new JLabel("O resgistrate para empezar a conocernos ;)");
																		lblOResgistratePara.setBackground(new Color(128, 255, 255));
																		lblOResgistratePara.setFont(new Font("Corbel", Font.BOLD, 16));
																		GridBagConstraints gbc_lblOResgistratePara = new GridBagConstraints();
																		gbc_lblOResgistratePara.insets = new Insets(0, 0, 5, 5);
																		gbc_lblOResgistratePara.gridx = 1;
																		gbc_lblOResgistratePara.gridy = 3;
																		contentPane.add(lblOResgistratePara, gbc_lblOResgistratePara);
										
										
																
																		JLabel lblDoc = new JLabel("Documento:");
																		lblDoc.setFont(new Font("Corbel", Font.BOLD, 16));
																		GridBagConstraints gbc_lblDoc = new GridBagConstraints();
																		gbc_lblDoc.insets = new Insets(0, 0, 5, 5);
																		gbc_lblDoc.gridx = 0;
																		gbc_lblDoc.gridy = 4;
																		contentPane.add(lblDoc, gbc_lblDoc);
								
										txtDocumento = new JTextField();
										txtDocumento.setBackground(new Color(128, 255, 255));
										txtDocumento.setFont(new Font("Corbel", Font.BOLD, 16));
										GridBagConstraints gbc_txtDocumento = new GridBagConstraints();
										gbc_txtDocumento.anchor = GridBagConstraints.NORTH;
										gbc_txtDocumento.fill = GridBagConstraints.HORIZONTAL;
										gbc_txtDocumento.insets = new Insets(0, 0, 5, 0);
										gbc_txtDocumento.gridx = 1;
										gbc_txtDocumento.gridy = 4;
										contentPane.add(txtDocumento, gbc_txtDocumento);
										txtDocumento.setColumns(10);
								
										JLabel lblPass = new JLabel("Contraseña:");
										lblPass.setForeground(new Color(0, 0, 0));
										lblPass.setFont(new Font("Corbel", Font.BOLD, 16));
										GridBagConstraints gbc_lblPass = new GridBagConstraints();
										gbc_lblPass.insets = new Insets(0, 0, 5, 5);
										gbc_lblPass.gridx = 0;
										gbc_lblPass.gridy = 5;
										contentPane.add(lblPass, gbc_lblPass);
						
								txtContrasena = new JPasswordField();
								txtContrasena.setBackground(new Color(128, 255, 255));
								txtContrasena.setFont(new Font("Corbel", Font.BOLD, 16));
								GridBagConstraints gbc_txtContrasena = new GridBagConstraints();
								gbc_txtContrasena.anchor = GridBagConstraints.NORTH;
								gbc_txtContrasena.fill = GridBagConstraints.HORIZONTAL;
								gbc_txtContrasena.insets = new Insets(0, 0, 5, 0);
								gbc_txtContrasena.gridx = 1;
								gbc_txtContrasena.gridy = 5;
								contentPane.add(txtContrasena, gbc_txtContrasena);
								
										JButton btnLogin = new JButton("Iniciar Sesión");
										btnLogin.setBackground(new Color(128, 255, 255));
										btnLogin.setFont(new Font("Corbel", Font.BOLD, 16));
										btnLogin.addActionListener(e -> autenticarUsuario());
										ImageIcon dec10 = new ImageIcon(PlataformaFinancieraUI.class.getResource("/recursos/decoracion1.png"));
										Image dec = dec10.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
										ImageIcon dec1 = new ImageIcon(dec);
										GridBagConstraints gbc_btnLogin = new GridBagConstraints();
										gbc_btnLogin.gridwidth = 3;
										gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
										gbc_btnLogin.fill = GridBagConstraints.VERTICAL;
										gbc_btnLogin.gridx = 0;
										gbc_btnLogin.gridy = 6;
										contentPane.add(btnLogin, gbc_btnLogin);
								
										JButton btnRegistrar = new JButton("Registrarse");
										btnRegistrar.setBackground(new Color(128, 255, 255));
										btnRegistrar.setFont(new Font("Corbel", Font.BOLD, 16));
										btnRegistrar.addActionListener(e -> new VentanaRegistro(sistema).setVisible(true));
										
										
										
										GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
										gbc_btnRegistrar.insets = new Insets(0, 0, 0, 5);
										gbc_btnRegistrar.fill = GridBagConstraints.BOTH;
										gbc_btnRegistrar.gridx = 1;
										gbc_btnRegistrar.gridy = 7;
										contentPane.add(btnRegistrar, gbc_btnRegistrar);
	}

	

	private void autenticarUsuario() {
		String doc = txtDocumento.getText();
		String pass = new String(txtContrasena.getPassword());
		try {
			Cuenta cuenta = sistema.autenticarUsuario(doc, pass);
			new VentanaMenuPrincipal(sistema, cuenta).setVisible(true);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
