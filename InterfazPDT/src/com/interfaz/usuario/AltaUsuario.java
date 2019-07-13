package com.interfaz.usuario;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import com.enumerados.Tipo_Documento;
import com.servicios.RolBeanRemote;
import com.servicios.UsuariosBeanRemote;
import com.entities.Rol;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaUsuario  {

	Date fechaLocal = new Date();

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField documento;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AltaUsuario() throws NamingException {
		JFrame frmNuevoUsuario = new JFrame();
		frmNuevoUsuario.setResizable(false);
		frmNuevoUsuario.setTitle("Nuevo Usuario");
		
		RolBeanRemote rolBean = (RolBeanRemote)
				InitialContext.doLookup("PROYECTO/RolBean!com.servicios.RolBeanRemote");
		
		frmNuevoUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoUsuario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoUsuario.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(279, 14, 107, 14);
		contentPane.add(lblApellido);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(354, 11, 167, 20);
		contentPane.add(apellido);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(279, 64, 107, 14);
		contentPane.add(lblDocumento);
		
		documento = new JTextField();
		documento.setColumns(10);
		documento.setBounds(354, 61, 167, 20);
		contentPane.add(documento);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 120, 107, 14);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		
		
		
		email.setColumns(10);
		email.setBounds(83, 117, 167, 20);
		contentPane.add(email);
		
		JLabel lblTipoDocumento = new JLabel("Tipo Documento:");
		lblTipoDocumento.setBounds(10, 67, 107, 14);
		contentPane.add(lblTipoDocumento);
		
		JComboBox<Object> tipoDocumento = new JComboBox<Object>();
		tipoDocumento.setModel(new DefaultComboBoxModel<Object>(Tipo_Documento.values()));
		tipoDocumento.setBounds(115, 61, 135, 20);
		contentPane.add(tipoDocumento);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(281, 117, 107, 14);
		contentPane.add(lblRol);
		
		JComboBox<Rol> rol = new JComboBox<Rol>();
		for (Rol roles : rolBean.obtenerTodos()) {
			
			rol.addItem(roles);
		}
		rol.setBounds(354, 117, 167, 20);
		contentPane.add(rol);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(316, 313, 89, 23);
		contentPane.add(btnAceptar);
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
		
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		frmNuevoUsuario.setSize(700, 500);
		frmNuevoUsuario.setVisible(true);
		
		
		
		btnAceptar.addActionListener(new ActionListener() {
			//el siguiente metodo crea el usuario 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//se comprueba que todos los campos esten completos
				if(nombre.getText().isEmpty() || apellido.getText().isEmpty() || 
							tipoDocumento.getSelectedItem().toString() == null ||
							documento.getText().isEmpty() || email.getText().isEmpty() || (Rol) rol.getSelectedItem() == null){
					//si no estan completos envia este mensaje
					JOptionPane.showMessageDialog(null,
							"Debe completar todos los campos", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
					
				}
				//se comprueba que el mail sea correcto
				
				else if(!email.getText().contains("@")){
					
					//si no es correcto envia este mensaje de error
					JOptionPane.showMessageDialog(null,
							"Debe colocar un email correcto", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
				}
				else{
				
				try {
					//A los nuevos usuarios se les asignara la contraseña 'inicial' y deberan cambiarla cuando entren por primera vezz
					usuarioBean.altaUsuario(nombre.getText(), apellido.getText(), 
							tipoDocumento.getSelectedItem().toString(),
							documento.getText(), email.getText(), fechaLocal, true, (Rol) rol.getSelectedItem());
					JOptionPane.showMessageDialog(null,
							"Usuario Dado de alta correctamente. Debe iniciar con contraseña 'Inicial'", 
							"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"No se pudo crear el Usuario \n" + e1.getMessage(), 
							"Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
				}
			}}
		});

		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmNuevoUsuario.dispose();
				new PantallaInicio();
			}
		});
			
		//se activa para que se pueda dar aceptar con enter 
		
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		
		//el boton limpiar borra los campos 
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
					apellido.setText("");
					documento.setText("");
					email.setText("");
					tipoDocumento.setSelectedItem("CI");
					rol.setSelectedItem("USUARIO");
			}
		});
		
	}
	
	
}
