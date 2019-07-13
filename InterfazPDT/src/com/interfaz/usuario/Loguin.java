package com.interfaz.usuario;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import com.servicios.UsuariosBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Loguin implements ActionListener {

	public static void main(String[] args) {
		new Loguin();
	}

	private JPanel contentPane;
	private JTextField email;
	private JPasswordField passwordField;
	private JFrame frmSistemaDeGestion = new JFrame();
	public static Usuario usuario = new Usuario(); // esta variable guardara el
													// usuario que fue logeado

	public Loguin() {
		frmSistemaDeGestion.setResizable(false);//no se permite agrandar la pantalla
		frmSistemaDeGestion.setTitle("Sistema de Gestion De Predios");
		frmSistemaDeGestion.setSize(416, 250);
		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestion.setBounds(100, 50, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmSistemaDeGestion.getContentPane().add(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("E-mail:");//el usuario ingresa con su email
		lblUsuario.setBounds(89, 69, 88, 15);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblUsuario);

		email = new JTextField();//campo para escribir el email
		email.setBounds(177, 65, 165, 22);
		contentPane.add(email);
		email.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(77, 104, 88, 15);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblContrasea);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(91, 135, 101, 25);
		frmSistemaDeGestion.setVisible(true);

		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(223, 135, 83, 25);
		contentPane.add(btnCancelar);

		passwordField = new JPasswordField();//campo para escribir la contraseña, lo escrito no es visible
		passwordField.setBounds(177, 100, 165, 22);
		contentPane.add(passwordField);

		JLabel lblLoguin = new JLabel("Bienvenido a SGP ");
		lblLoguin.setBounds(155, 13, 265, 16);
		lblLoguin.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblLoguin);

		JLabel lblNewLabel = new JLabel("Identifiquese para Acceder al sistema");
		lblNewLabel.setBounds(110, 40, 215, 16);
		contentPane.add(lblNewLabel);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//comprueba que los campos se hayan completado
				if (passwordField.getPassword().length == 0 || email.getText().isEmpty()) 
				{
					//si no han sido completados envia este mensaje de error
					JOptionPane.showMessageDialog(null, "Debe ingresar un email y una contraseña",
							"Intentelo Nuevamente", JOptionPane.INFORMATION_MESSAGE);

				} else {
					//llama al metodo valida el usuario 
					validarUsuario();
				}
			}
		}

		);
		// El siguiente metodo hara que al apretar enter sea como si se ubiera
		// dado click en aceptar
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmSistemaDeGestion.dispose();

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// El sigueinte metodo obtiene el valor ingresado en password y email y los
	// envia a los ejb para validarlos o no
	void validarUsuario() {

		try {
			UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
			InitialContext.doLookup("PROYECTO/UsuariosBean!com.servicios.UsuariosBeanRemote");
			String password = String.valueOf(passwordField.getPassword());//se guarda el password como string
			String mail = email.getText();
			boolean valido = usuarioBean.ValidarUsuario(mail, password);//validar usuario devuelve un boleano
			
			if (valido) {
				//si el booleando es true dejara entrar a la pantalla de inicio
				usuario = usuarioBean.ObtenerUsuario(mail, password);
				new PantallaInicio();
				frmSistemaDeGestion.dispose();
				
			} else {
				//si el booleando es falso emitira el siguiente cartel
				JOptionPane.showMessageDialog(null, "Usuario o contraseña Incorrecto", "Intentelo Nuevamente",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//si ha ocurrido un error enviara el siguiente mensaje 
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Ha ocurrido un error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();

		}
	}
}
