package com.interfaz.usuario;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.time.LocalDate;
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
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ResetearUsuario {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public ResetearUsuario() throws NamingException {

		JFrame frmResetearUsuario = new JFrame();
		frmResetearUsuario.setResizable(false);
		frmResetearUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		frmResetearUsuario.setTitle("Resetear Usuario");

		frmResetearUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResetearUsuario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmResetearUsuario.setContentPane(contentPane);
		

		contentPane.setLayout(null);
		JComboBox<Usuario> comboBoxUsuario = new JComboBox<Usuario>();
		

		comboBoxUsuario.setBounds(191, 43, 184, 22);
		contentPane.add(comboBoxUsuario);
		frmResetearUsuario.setSize(428, 321);
		frmResetearUsuario.setVisible(true);
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

		JButton btnResetear = new JButton("Resetear");
		btnResetear.setHorizontalAlignment(SwingConstants.LEFT);
		btnResetear.setBounds(51, 200, 89, 23);
		contentPane.add(btnResetear);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(286, 200, 89, 23);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmResetearUsuario.dispose();
				new PantallaInicio();
			}
		});
		comboBoxUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnResetear.doClick();
				}
			}
		});
		btnResetear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//se envia un mensaje de advertencia
					int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea resetear la contraseña este usuario?", "Notificacion",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					//en caso de aceptar se resetea la contraseña
					if(opcion == 0) {
						
						

					Usuario usuario = (Usuario) comboBoxUsuario.getSelectedItem();
					UsuariosBeanRemote UsuariosBean = (UsuariosBeanRemote) InitialContext
							.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
					UsuariosBean.resetearUsuario(usuario);
					JOptionPane.showMessageDialog(null, "Se ha reseteado la conntraseña a inicial", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//en caso de no poder resetear el usuario se envia un mensaje de error 
					JOptionPane.showMessageDialog(null, "No se pudo resetear usuario", "Notificacion",
							JOptionPane.WARNING_MESSAGE);

					e1.printStackTrace();
				}
			}
		});
		
		

	}
	
	
}
