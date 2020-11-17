package paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Clientes;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionCliente extends JPanel {
	private JTextField txtNombre;
	private JTextField txtDniCliente;
	private JTable table;
	
	DefaultTableModel modeloTabla= new DefaultTableModel();
	private JTextField txtTelefono;
	private JTextField txtIdEliminar;

	/**
	 * Create the panel.
	 */
	public GestionCliente() {
		
		setBackground(SystemColor.window);
		setLayout(null);
		
		JLabel labelGestionCliente1 = new JLabel("A\u00F1adir Cliente");
		labelGestionCliente1.setForeground(SystemColor.textHighlight);
		labelGestionCliente1.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelGestionCliente1.setBounds(75, 57, 182, 40);
		add(labelGestionCliente1);
		
		JLabel labelGestionCliente2 = new JLabel("Nombre Cliente:");
		labelGestionCliente2.setForeground(SystemColor.textHighlight);
		labelGestionCliente2.setFont(new Font("Arial", Font.BOLD, 16));
		labelGestionCliente2.setBounds(10, 124, 149, 35);
		add(labelGestionCliente2);
		
		JLabel labelGestionCliente3 = new JLabel("DNI Cliente: ");
		labelGestionCliente3.setForeground(SystemColor.textHighlight);
		labelGestionCliente3.setFont(new Font("Arial", Font.BOLD, 16));
		labelGestionCliente3.setBounds(39, 190, 108, 15);
		add(labelGestionCliente3);
		
		
		JButton btnAniadir = new JButton("A\u00F1adir Cliente");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		btnAniadir.setFont(new Font("Arial", Font.BOLD, 15));
		btnAniadir.setForeground(Color.WHITE);
		btnAniadir.setBackground(Color.BLUE);
		btnAniadir.setBounds(102, 333, 150, 23);
		add(btnAniadir);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(SystemColor.inactiveCaption);
		txtNombre.setBounds(169, 144, 121, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDniCliente = new JTextField();
		txtDniCliente.setBackground(SystemColor.inactiveCaption);
		txtDniCliente.setColumns(10);
		txtDniCliente.setBounds(169, 190, 123, 20);
		add(txtDniCliente);
		
		JLabel labelGestionCliente4 = new JLabel("Eliminar Cliente");
		labelGestionCliente4.setForeground(SystemColor.textHighlight);
		labelGestionCliente4.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelGestionCliente4.setBounds(1028, 57, 191, 40);
		add(labelGestionCliente4);
		
		JLabel labelGestionCliente5 = new JLabel("ID Cliente:");
		labelGestionCliente5.setForeground(SystemColor.textHighlight);
		labelGestionCliente5.setFont(new Font("Arial", Font.BOLD, 16));
		labelGestionCliente5.setBounds(1009, 145, 115, 14);
		add(labelGestionCliente5);
		
		JButton btnEliminar = new JButton("Eliminar Cliente");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCliente();
			}
		});
		
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 15));
		btnEliminar.setBackground(Color.BLUE);
		btnEliminar.setBounds(1060, 333, 150, 23);
		add(btnEliminar);
		
		JLabel labelGestionCliente6 = new JLabel("Lista de clientes");
		labelGestionCliente6.setForeground(SystemColor.textHighlight);
		labelGestionCliente6.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelGestionCliente6.setBounds(566, 393, 214, 40);
		add(labelGestionCliente6);
		
		JLabel labelGestionCliente7 = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Sotecars.png")).getImage();
		Image modifiedImage = img1.getScaledInstance(170, 100, java.awt.Image.SCALE_SMOOTH);
		labelGestionCliente7.setIcon(new ImageIcon(modifiedImage));
		labelGestionCliente7.setBounds(566, 170, 172, 111);
		add(labelGestionCliente7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 452, 1218, 261);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla.setColumnIdentifiers(new Object[] {"ID","Nombre","Telefono","DNI"});
		table.setModel(modeloTabla);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(SystemColor.textHighlight);
		lblTelefono.setFont(new Font("Arial", Font.BOLD, 16));
		lblTelefono.setBounds(56, 241, 91, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(SystemColor.inactiveCaption);
		txtTelefono.setBounds(167, 240, 123, 20);
		add(txtTelefono);
		
		txtIdEliminar = new JTextField();
		txtIdEliminar.setColumns(10);
		txtIdEliminar.setBackground(SystemColor.inactiveCaption);
		txtIdEliminar.setBounds(1115, 144, 121, 20);
		add(txtIdEliminar);
		
		modeloTabla.setRowCount(0);
		
		cargaClientes();
		
	}
	
	public void cargaClientes() {
		Connection conexion=null;
		Statement sql=null;
		ResultSet rs=null;
		try {
			try {
				conexion= DriverManager.getConnection("jdbc:mysql://localhost/SotecarsBBDD","TRABAJO","TRABAJO");
				sql=conexion.createStatement();
				rs=sql.executeQuery("SELECT ID, Nombre, Telefono, DNI FROM Clientes");

				while(rs.next()) {
					modeloTabla.addRow(new Object[] {
							rs.getObject("ID"),
							rs.getObject("Nombre"),
							rs.getObject("Telefono"),
							rs.getObject("DNI")
					});
				}

				conexion.close();
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		  	}
		}finally{
			System.out.println("Ningun error");
		}
	}
	
	public void eliminarCliente() {
		int idCliente=Integer.parseInt(txtIdEliminar.getText()); 
		
		Connection conexion=null;
		Statement sql=null;
		ResultSet rs=null;
		try {
			try {
				conexion= DriverManager.getConnection("jdbc:mysql://localhost/SotecarsBBDD","TRABAJO","TRABAJO");
				sql=conexion.createStatement();
				rs=sql.executeQuery("DELETE FROM Clientes WHERE ID="
						+ idCliente);

				conexion.close();
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		  	}
		}finally{
			System.out.println("Ningun error");
		}
	}
	
	public void aniadirCliente() {
		String nombre= txtNombre.getText();
		String dni= txtDniCliente.getText();
		int telefono= Integer.parseInt(txtTelefono.getText());
		
		Connection conexion=null;
		Statement sql=null;
		ResultSet rs=null;
		try {
			try {
				conexion= DriverManager.getConnection("jdbc:mysql://localhost/SotecarsBBDD","TRABAJO","TRABAJO");
				sql=conexion.createStatement();
				rs=sql.executeQuery("INSERT INTO Clientes(Nombre, Telefono, DNI) values('"
						+ nombre+"'"
						+ dni
						+ telefono+");" );

				conexion.close();
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		  	}
		}finally{
			System.out.println("Ningun error");
		}
	}
}
