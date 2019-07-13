package com.interfaz.propietario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.exception.ServiciosException;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PropietariosBeanRemote;
import com.entities.Propietario;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaPropietario  {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField razon_social;
	private JTextField rut;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField contacto;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AltaPropietario() throws NamingException {
		PropietariosBeanRemote propietarioBean = (PropietariosBeanRemote)
				InitialContext.doLookup("PROYECTO/PropietariosBean!com.servicios.PropietariosBeanRemote");
		JFrame frmNuevoPropietario = new JFrame();
		frmNuevoPropietario.setResizable(false);
		frmNuevoPropietario.setTitle("Crear Nuevo Propietario");
		
		frmNuevoPropietario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoPropietario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoPropietario.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setBounds(279, 14, 107, 14);
		contentPane.add(lblRazonSocial);
		
		razon_social = new JTextField();
		razon_social.setColumns(10);
		razon_social.setBounds(379, 11, 167, 20);
		contentPane.add(razon_social);
		
		JLabel lblRut = new JLabel("Rut:");
		lblRut.setBounds(279, 67, 107, 14);
		contentPane.add(lblRut);
		
		rut = new JTextField();
		rut.setColumns(10);
		rut.setBounds(379, 64, 167, 20);
		contentPane.add(rut);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(10, 67, 107, 14);
		contentPane.add(lblDireccion);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(83, 64, 167, 20);
		contentPane.add(direccion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(316, 313, 89, 23);
		contentPane.add(btnAceptar);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmNuevoPropietario.dispose();
				new PantallaInicio();
			}
		});
			
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(279, 117, 107, 14);
		contentPane.add(lblTelefono);
		
		telefono = new JTextField();
		
		telefono.setColumns(10);
		telefono.setBounds(379, 114, 167, 20);
		contentPane.add(telefono);
		
		contacto = new JTextField();
		contacto.setBounds(83, 113, 167, 22);
		contentPane.add(contacto);
		contacto.setColumns(10);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(15, 116, 56, 16);
		contentPane.add(lblContacto);
		frmNuevoPropietario.setSize(700, 500);
		frmNuevoPropietario.setVisible(true);
btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					Propietario propietario = new Propietario();
					propietarioBean.altaPropietario(razon_social.getText(), nombre.getText(), 
							rut.getText(), direccion.getText(), telefono.getText(), contacto.getText());
					JOptionPane.showMessageDialog(null,
							"Propietario Dado de alta correctamente", 
							"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"No se pudo crear el Propietario", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
					razon_social.setText("");
					rut.setText("");
					direccion.setText("");
					telefono.setText("");
					contacto.setText("");
			}
		});
		
	}
}
