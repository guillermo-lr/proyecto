package com.interfaz.usuario;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificarContraseña implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame frmSistemaDeGestion = new JFrame();
	private JPasswordField nuevoPassword;

	public ModificarContraseña() throws NamingException {

		frmSistemaDeGestion.setTitle("Sistema de Gestion De Predios");

		frmSistemaDeGestion.setSize(500, 500);
		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestion.setBounds(100, 50, 450, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frmSistemaDeGestion.getContentPane().add(contentPane);

		JLabel lblUsuario = new JLabel("E-mail:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(12, 70, 88, 14);
		contentPane.add(lblUsuario);

		textField = new JTextField();
		textField.setBounds(130, 67, 169, 20);
		contentPane.add(textField);
		textField.setText(Loguin.usuario.getMail());
		textField.setEditable(false);
		textField.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasea.setBounds(12, 103, 88, 14);
		contentPane.add(lblContrasea);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(130, 166, 89, 23);
		frmSistemaDeGestion.setVisible(true);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(!passwordField.getText().isEmpty() && !nuevoPassword.getText()
						.isEmpty())
				{
				try {
					validarUsuario();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Intentelo Nuevamente",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		);

		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmSistemaDeGestion.dispose();
				new PantallaInicio();

			}
		});
		btnCancelar.setBounds(233, 166, 89, 23);
		contentPane.add(btnCancelar);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 100, 169, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel = new JLabel("Modificar Contrase\u00F1a");
		lblNewLabel.setBounds(130, 26, 265, 16);
		contentPane.add(lblNewLabel);

		nuevoPassword = new JPasswordField();
		nuevoPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		nuevoPassword.setBounds(130, 133, 169, 20);
		contentPane.add(nuevoPassword);

		JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a:");
		lblNuevaContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNuevaContrasea.setBounds(12, 139, 113, 14);
		contentPane.add(lblNuevaContrasea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	void validarUsuario() throws NamingException {

		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext
				.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");

		try {
			// el usuario solo podra cambiar su propio password

			// para realizar el cambio debe ingresar su password actual y una
			// nueva
			if (Loguin.usuario.getPasswordUsuario().equals(passwordField.getText())) {

				usuarioBean.modificarContraseña(Loguin.usuario, nuevoPassword.getText());
				JOptionPane.showMessageDialog(null, "Contraseña Modificada Correctamente", "Notificacion",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña Incorrecto", "Intentelo Nuevamente",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error \n " + e1.getMessage(), "Ha ocurrido un error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
