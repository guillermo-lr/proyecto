package com.interfaz.usuario;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.UsuariosBeanRemote;
import com.entities.Usuario;
import javax.swing.JButton;import java.awt.Event;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EliminarUsuario {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	JComboBox<Usuario> comboBoxUsuario;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public EliminarUsuario() throws NamingException {
		
		JFrame frmNuevoUsuario = new JFrame();
		frmNuevoUsuario.setResizable(false);
		frmNuevoUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		frmNuevoUsuario.setTitle("Eliminar Usuario");

		frmNuevoUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoUsuario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoUsuario.setContentPane(contentPane);
		

		contentPane.setLayout(null);
		JComboBox<Usuario> comboBoxUsuario = new JComboBox<Usuario>();
		
		
		comboBoxUsuario.setEditable(true);

		comboBoxUsuario.setBounds(191, 43, 184, 22);
		contentPane.add(comboBoxUsuario);
		frmNuevoUsuario.setSize(428, 321);
		frmNuevoUsuario.setVisible(true);
		//se agregan toods los usuarios a un combobox de seleccion
		UsuariosBeanRemote UsuariosBean = (UsuariosBeanRemote) InitialContext
				.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
		List<Usuario> listaUsuario = UsuariosBean.obtenerTodos();

		for (Usuario usuario : listaUsuario) {
			if (usuario.getStatus()) {
				comboBoxUsuario.addItem((Usuario) usuario);
			}
		}
		comboBoxUsuario.setSelectedItem(null);
		JLabel LblUsuario = new JLabel("Seleccionar Usuario");
		LblUsuario.setBounds(51, 47, 151, 14);
		contentPane.add(LblUsuario);

		JButton btnAceptar = new JButton("Eliminar");
		btnAceptar.setEnabled(true);
		btnAceptar.setBounds(51, 200, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(286, 200, 89, 23);
		contentPane.add(btnCancelar);
	

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoUsuario.dispose();
				new PantallaInicio();
			}
		});
		comboBoxUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				eliminarUsuario();
	}
	
	//metodo que elimina el usuario
	public void eliminarUsuario(){
		try {
			
			//se comprueba que el usuario seleccionado exista
			if(!listaUsuario.contains(comboBoxUsuario.getSelectedItem())
					){
				JOptionPane.showMessageDialog(null, "Usuario no existe", "Notificacion",
						JOptionPane.WARNING_MESSAGE);
				
				}
			
			else{
			//se envia un mensaje de advertencia
			int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar este usuario?", "Notificacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			//en caso de aceptar se cambia el status del usuario a false 
			if(opcion == 0) {
				
				UsuariosBeanRemote UsuariosBean = (UsuariosBeanRemote) InitialContext
					.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");

			Usuario usuario = (Usuario) comboBoxUsuario.getSelectedItem();
			
			UsuariosBean.deshabilitarUsuario(usuario);
			JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente", "Notificacion",
					JOptionPane.INFORMATION_MESSAGE);}
		}} catch (Exception e1) {
			// TODO Auto-generated catch block
			//en caso de no poder eliminar el usuario se envia un mensaje de error 
			JOptionPane.showMessageDialog(null, "No se pudo eliminar usuario" + e1.getMessage(), "Notificacion",
					JOptionPane.ERROR_MESSAGE);

			e1.printStackTrace();
		}			}
});



	}
}
