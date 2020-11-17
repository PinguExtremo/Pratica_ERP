package controlador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1299, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(234, 140, 29, 487);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(235, 131, 828, 30);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		panel_2.setBounds(1034, 157, 29, 470);
		contentPane.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLUE);
		panel_3.setBounds(249, 597, 809, 30);
		contentPane.add(panel_3);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(542, 328, 108, 63);
		contentPane.add(lblNewLabel);

		JLabel lblContrase = new JLabel("Contrase\u00F1a:");
		lblContrase.setFont(new Font("Arial", Font.PLAIN, 18));
		lblContrase.setBounds(515, 418, 108, 63);
		contentPane.add(lblContrase);

		usuario = new JTextField();
		usuario.setFont(new Font("Arial", Font.BOLD, 18));
		usuario.setBounds(643, 347, 135, 30);
		contentPane.add(usuario);
		usuario.setColumns(10);

		password = new JTextField();
		password.setFont(new Font("Arial", Font.BOLD, 18));
		password.setColumns(10);
		password.setBounds(643, 431, 135, 30);
		contentPane.add(password);

		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String loginusuario = usuario.getText();
				String loginpass = password.getText();

				if (loginusuario.equals("1") && loginpass.equals("1")) {

					dispose();
					Home home = new Home();
					home.setVisible(true);

				} else {

					Login frame = new Login();
					JOptionPane.showMessageDialog(frame, "Usuario Incorrecto");
				}
			}
		});

		btnNewButton.setBounds(607, 525, 135, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("*Nota aclaratoria*");
		lblNewLabel_1.setBounds(301, 187, 129, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" la contrase\u00F1a para tabajadores ser\u00E1 1, la contrase\u00F1a para administradores ser\u00E1 2");
		lblNewLabel_2.setBounds(298, 218, 379, 30);
		contentPane.add(lblNewLabel_2);
	}
}
