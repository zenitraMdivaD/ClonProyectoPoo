package gui;

import javax.swing.*;
import kernel.*;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class VentanaMenuPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * @wbp.nonvisual location=141,-21
	 */
	private final JLabel label = new JLabel("New label");
	/**
	 * @wbp.nonvisual location=-28,-21
	 */
	private final JTextField textField = new JTextField();

	public VentanaMenuPrincipal(SistemaFinanciero sistema, Cuenta cuenta) {
		textField.setColumns(10);
		getContentPane().setBackground(new Color(128, 255, 255));
		setIconImage(new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage());

		setTitle("Menú Principal");
		setSize(503, 482);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 186, 79, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 25, 25, 0, 25, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
						
								JButton btnDepositar = new JButton("");
								btnDepositar.setBackground(new Color(128, 255, 128));
								ImageIcon depositarO = new ImageIcon(VentanaMenuPrincipal.class.getResource("/recursos/depositar.png"));
								Image depositarR = depositarO.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon depositar1 = new ImageIcon(depositarR);
								btnDepositar.setIcon(depositar1);
								btnDepositar.addActionListener(e -> {
									new VentanaDeposito(sistema, cuenta).setVisible(true);
									dispose();
								});
									
								
										
												JLabel lblBienvenida = new JLabel("Bienvenido, " + cuenta.getTitular().getNombreCompleto());
												lblBienvenida.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 20));
												GridBagConstraints gbc_lblBienvenida = new GridBagConstraints();
												gbc_lblBienvenida.fill = GridBagConstraints.VERTICAL;
												gbc_lblBienvenida.insets = new Insets(0, 0, 5, 5);
												gbc_lblBienvenida.gridx = 1;
												gbc_lblBienvenida.gridy = 1;
												getContentPane().add(lblBienvenida, gbc_lblBienvenida);
										
										JLabel lblMuchoGustoEl = new JLabel("Mucho gusto el verte de nuevo :)");
										lblMuchoGustoEl.setFont(new Font("Corbel", Font.ITALIC, 14));
										GridBagConstraints gbc_lblMuchoGustoEl = new GridBagConstraints();
										gbc_lblMuchoGustoEl.insets = new Insets(0, 0, 5, 5);
										gbc_lblMuchoGustoEl.gridx = 1;
										gbc_lblMuchoGustoEl.gridy = 2;
										getContentPane().add(lblMuchoGustoEl, gbc_lblMuchoGustoEl);
								
										JLabel lblSaldo = new JLabel("Saldo actual: $" + cuenta.getSaldo());
										lblSaldo.setFont(new Font("Corbel", Font.ITALIC, 16));
										GridBagConstraints gbc_lblSaldo = new GridBagConstraints();
										gbc_lblSaldo.fill = GridBagConstraints.VERTICAL;
										gbc_lblSaldo.insets = new Insets(0, 0, 5, 5);
										gbc_lblSaldo.gridx = 1;
										gbc_lblSaldo.gridy = 3;
										getContentPane().add(lblSaldo, gbc_lblSaldo);
								
								JLabel lblDepositar = new JLabel("Depositar");
								lblDepositar.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 20));
								GridBagConstraints gbc_lblDepositar = new GridBagConstraints();
								gbc_lblDepositar.insets = new Insets(0, 0, 5, 5);
								gbc_lblDepositar.gridx = 0;
								gbc_lblDepositar.gridy = 4;
								getContentPane().add(lblDepositar, gbc_lblDepositar);
								
								JLabel lblEnviar = new JLabel("Enviar");
								lblEnviar.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 20));
								GridBagConstraints gbc_lblEnviar = new GridBagConstraints();
								gbc_lblEnviar.insets = new Insets(0, 0, 5, 5);
								gbc_lblEnviar.gridx = 1;
								gbc_lblEnviar.gridy = 4;
								getContentPane().add(lblEnviar, gbc_lblEnviar);
								
								JLabel lblRetirar = new JLabel("Retirar");
								lblRetirar.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 20));
								GridBagConstraints gbc_lblRetirar = new GridBagConstraints();
								gbc_lblRetirar.insets = new Insets(0, 0, 5, 0);
								gbc_lblRetirar.gridx = 2;
								gbc_lblRetirar.gridy = 4;
								getContentPane().add(lblRetirar, gbc_lblRetirar);
								GridBagConstraints gbc_btnDepositar = new GridBagConstraints();
								gbc_btnDepositar.fill = GridBagConstraints.VERTICAL;
								gbc_btnDepositar.insets = new Insets(0, 0, 5, 5);
								gbc_btnDepositar.gridx = 0;
								gbc_btnDepositar.gridy = 5;
								getContentPane().add(btnDepositar, gbc_btnDepositar);
								ImageIcon historial = new ImageIcon(VentanaMenuPrincipal.class.getResource("/recursos/registro.png"));
								Image historial1 = historial.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon registro = new ImageIcon(historial1);
								
										JButton btnTransferir = new JButton("");
										btnTransferir.setBackground(new Color(128, 255, 128));
										ImageIcon enviar = (new ImageIcon(VentanaMenuPrincipal.class.getResource("/recursos/enviar.png")));
										Image enviar1 = enviar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
										ImageIcon transferir = new ImageIcon(enviar1);
										btnTransferir.setIcon(transferir);
										btnTransferir.addActionListener(e -> {
											new VentanaTransferencia(sistema, cuenta).setVisible(true);
											dispose();
										});
										GridBagConstraints gbc_btnTransferir = new GridBagConstraints();
										gbc_btnTransferir.fill = GridBagConstraints.VERTICAL;
										gbc_btnTransferir.insets = new Insets(0, 0, 5, 5);
										gbc_btnTransferir.gridx = 1;
										gbc_btnTransferir.gridy = 5;
										getContentPane().add(btnTransferir, gbc_btnTransferir);
								ImageIcon sacar = new ImageIcon(VentanaMenuPrincipal.class.getResource("/recursos/retirar.png"));
								Image sacar1 = sacar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon retirar = new ImageIcon(sacar1);
																
																JButton btnRetirar = new JButton("");
																btnRetirar.setBackground(new Color(128, 255, 128));
																btnRetirar.setIcon(retirar);
																btnRetirar.setBounds(200, 90, 150, 25);
																btnRetirar.addActionListener(e -> {
																	new VentanaRetiro(sistema, cuenta).setVisible(true);
																	dispose();
																});
																GridBagConstraints gbc_btnRetirar = new GridBagConstraints();
																gbc_btnRetirar.insets = new Insets(0, 0, 5, 0);
																gbc_btnRetirar.gridx = 2;
																gbc_btnRetirar.gridy = 5;
																getContentPane().add(btnRetirar, gbc_btnRetirar);
								
														
																JButton btnHistorial = new JButton("");
																btnHistorial.setBackground(new Color(128, 255, 128));
																btnHistorial.setIcon(registro);
																btnHistorial.addActionListener(e -> {
																	new VentanaRetiro(sistema, cuenta).setVisible(true);
																	dispose();
																});
																
																JLabel lblHistorial = new JLabel("Historial");
																lblHistorial.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 20));
																GridBagConstraints gbc_lblHistorial = new GridBagConstraints();
																gbc_lblHistorial.insets = new Insets(0, 0, 5, 5);
																gbc_lblHistorial.gridx = 1;
																gbc_lblHistorial.gridy = 6;
																getContentPane().add(lblHistorial, gbc_lblHistorial);
																GridBagConstraints gbc_btnHistorial = new GridBagConstraints();
																gbc_btnHistorial.insets = new Insets(0, 0, 5, 5);
																gbc_btnHistorial.fill = GridBagConstraints.VERTICAL;
																gbc_btnHistorial.gridx = 1;
																gbc_btnHistorial.gridy = 7;
																getContentPane().add(btnHistorial, gbc_btnHistorial);
								
								JButton btnNewButton = new JButton("Cerrar Sesión");
								btnNewButton.setForeground(new Color(255, 255, 255));
								btnNewButton.setFont(new Font("Corbel", Font.BOLD | Font.ITALIC, 15));
								btnNewButton.setBackground(new Color(0, 0, 255));
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										dispose();
										PlataformaFinancieraUI frame = new PlataformaFinancieraUI(sistema); // ✅
										frame.setVisible(true);
										dispose();
									}
								});
								GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
								gbc_btnNewButton.gridx = 2;
								gbc_btnNewButton.gridy = 8;
								getContentPane().add(btnNewButton, gbc_btnNewButton);
	}
}
