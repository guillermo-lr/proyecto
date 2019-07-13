package com.interfaz.usuario;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

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
import com.servicios.UsuariosBean;
import com.servicios.UsuariosBeanRemote;
import com.entities.Rol;
import com.entities.Usuario;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificarUsuario  {

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
	public ModificarUsuario() throws NamingException {
		JFrame frmModificarUsuario = new JFrame();
		frmModificarUsuario.setResizable(false);
		frmModificarUsuario.setTitle("Modificar Usuario");
		
		RolBeanRemote rolBean = (RolBeanRemote)
				InitialContext.doLookup("PROYECTO/RolBean!com.servicios.RolBeanRemote");
		
		frmModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarUsuario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmModificarUsuario.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 84, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 81, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(279, 84, 107, 14);
		contentPane.add(lblApellido);
		
		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setBounds(354, 81, 167, 20);
		contentPane.add(apellido);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(279, 117, 107, 14);
		contentPane.add(lblDocumento);
		
		documento = new JTextField();
		documento.setColumns(10);
		documento.setBounds(354, 114, 167, 20);
		contentPane.add(documento);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 158, 107, 14);
		contentPane.add(lblEmail);
		
		email = new JTextField();
		
		email.setColumns(10);
		email.setBounds(83, 155, 167, 20);
		contentPane.add(email);
		
		JLabel lblTipoDocumento = new JLabel("Tipo Documento:");
		lblTipoDocumento.setBounds(10, 117, 107, 14);
		contentPane.add(lblTipoDocumento);
		
		JComboBox<Object> tipoDocumento = new JComboBox<Object>();
		tipoDocumento.setModel(new DefaultComboBoxModel<Object>(Tipo_Documento.values()));
		tipoDocumento.setBounds(114, 114, 135, 20);
		contentPane.add(tipoDocumento);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(279, 158, 107, 14);
		contentPane.add(lblRol);
		
		JComboBox<Object> rol = new JComboBox<Object>();
		for (Rol roles : rolBean.obtenerTodos()) {
			
			rol.addItem(roles);
		}
		rol.setBounds(354, 155, 167, 20);
		contentPane.add(rol);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(316, 313, 89, 23);
		contentPane.add(btnAceptar);
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
		
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmModificarUsuario.dispose();
				new PantallaInicio();
			}
		});
			
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		UsuariosBeanRemote UsuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
		
		
		JComboBox comboBoxUsuario = new JComboBox();
		comboBoxUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Usuario us = (Usuario) comboBoxUsuario.getSelectedItem();
				
				nombre.setText(us.getNombre());
				apellido.setText(us.getApellido());
				documento.setText(us.getDocumento());
				email.setText(us.getMail());
				
				
			}
		});
		List<Usuario> listaUsuario = UsuariosBean.obtenerTodos();

		for (Usuario usuario : listaUsuario) {
			if (usuario.getStatus()) {
				comboBoxUsuario.addItem((Usuario) usuario);
			}
		}
		comboBoxUsuario.setBounds(199, 25, 167, 22);
		contentPane.add(comboBoxUsuario);
		
		JLabel lblSeleccionarUsuario = new JLabel("Seleccionar usuario");
		lblSeleccionarUsuario.setBounds(10, 28, 135, 16);
		contentPane.add(lblSeleccionarUsuario);
		frmModificarUsuario.setSize(700, 500);
		frmModificarUsuario.setVisible(true);
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			//el siguiente metodo modifica el usuario 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//se comprueba que no hayan campos vacios
					if(!nombre.getText().isEmpty() && !apellido.getText().isEmpty() && 
							
							!tipoDocumento.getSelectedItem().toString().isEmpty() && !documento.getText().isEmpty() &&
							!email.getText().isEmpty() && (Rol) rol.getSelectedItem() != null
							&& (Usuario) comboBoxUsuario.getSelectedItem() != null){
						
						
						
							usuarioBean.modificarUsuario(nombre.getText(), apellido.getText(), 
							tipoDocumento.getSelectedItem().toString(),
							documento.getText(), email.getText(), 
							fechaLocal, true, (Rol) rol.getSelectedItem(),
							(Usuario) comboBoxUsuario.getSelectedItem());
					JOptionPane.showMessageDialog(null,
							"Usuario Modificado Correctamente", 
							"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Debe completar todos los campos", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
				}}
					catch (Exception e1) {
				
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"No se pudo modificar el Usuario", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
				}
					
				}
			}
		);
		//el boton limpiar borra los campos A
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
