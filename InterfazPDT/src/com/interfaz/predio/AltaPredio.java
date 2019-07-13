package com.interfaz.predio;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Propietario;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PrediosBeanRemote;
import com.servicios.PropietariosBeanRemote;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AltaPredio  {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField Descripcion;
	private JTextField area;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AltaPredio() throws NamingException {
		JFrame frmNuevoPredio = new JFrame();
		frmNuevoPredio.setResizable(false);
		frmNuevoPredio.setTitle("Crear Nuevo Predio");
		
		frmNuevoPredio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoPredio.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoPredio.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel desc = new JLabel("Descripcion");
		desc.setBounds(10, 120, 107, 14);
		contentPane.add(desc);
		
		Descripcion = new JTextField();
		
		Descripcion.setColumns(10);
		Descripcion.setBounds(83, 117, 438, 136);
		contentPane.add(Descripcion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(316, 313, 89, 23);
		contentPane.add(btnAceptar);
		
		JComboBox<Propietario> comboBoxPropietario = new JComboBox<Propietario>();
		comboBoxPropietario.setBounds(354, 14, 167, 22);
		contentPane.add(comboBoxPropietario);
		frmNuevoPredio.setSize(428, 321);
		frmNuevoPredio.setVisible(true);
		PropietariosBeanRemote PropietariosBean = (PropietariosBeanRemote) InitialContext
				.doLookup("PROYECTO/PropietariosBean!com.servicios.PropietariosBeanRemote");

		List<Propietario> listaPropietario = PropietariosBean.obtenerTodos();

		for (Propietario propietario : listaPropietario) {
				comboBoxPropietario.addItem((Propietario) propietario);
			}
		
		comboBoxPropietario.setSelectedItem(null);
		frmNuevoPredio.getContentPane().add(comboBoxPropietario);
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				try {
					PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
							InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
					
					PrediosBean.altaPredio(nombre.getText(), Descripcion.getText(), (Propietario) comboBoxPropietario.getSelectedItem(), Long.parseLong(area.getText()));
					JOptionPane.showMessageDialog(null,
							"Predio Dado de alta correctamente", 
							"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							e1.getMessage(), 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
			}
		);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmNuevoPredio.dispose();
				new PantallaInicio();
			}
		});
			
		Descripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(278, 13, 74, 16);
		contentPane.add(lblPropietario);
		
		area = new JTextField();
		area.setBounds(83, 70, 167, 22);
		contentPane.add(area);
		area.setColumns(10);
		
		JLabel lblArea = new JLabel("Area(ha):");
		lblArea.setBounds(12, 73, 56, 16);
		contentPane.add(lblArea);
		frmNuevoPredio.setSize(553, 399);
		frmNuevoPredio.setVisible(true);
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
					Descripcion.setText("");
			}
		});
		
	}
}
