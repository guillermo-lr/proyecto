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
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.exception.ServiciosException;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PrediosBeanRemote;
import com.servicios.PropietariosBeanRemote;
import com.servicios.ZonasBeanRemote;
import com.entities.Predio;
import com.entities.Propietario;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModificarPredio {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nuevonombre;

	/**
	 * Launch the application.
	 */
	ZonasBeanRemote zonaBean = (ZonasBeanRemote) InitialContext
			.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");
	private JTextField nuevaArea;
	

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public ModificarPredio() throws NamingException {
		PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
				InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
		
		JFrame frmNuevoPredio = new JFrame();
		frmNuevoPredio.setResizable(false);
		frmNuevoPredio.setFont(new Font("Dialog", Font.BOLD, 16));
		frmNuevoPredio.setTitle("Modificar Predio");

		frmNuevoPredio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoPredio.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoPredio.setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Predio> comboBoxPredio = new JComboBox<Predio>();
		comboBoxPredio.setSelectedItem(null);
		comboBoxPredio.setBounds(301, 43, 167, 22);
		
		List<Predio> listaPredio = PrediosBean.obtenerTodosDisponibles();

		for (Predio predio : listaPredio) {
			
				comboBoxPredio.addItem((Predio) predio);
			
		}
		frmNuevoPredio.setSize(549, 321);
		frmNuevoPredio.setVisible(true);
		contentPane.add(comboBoxPredio);

		JLabel lblNombre = new JLabel("Nuevo Nombre Predio:");
		lblNombre.setBounds(101, 74, 161, 14);
		contentPane.add(lblNombre);

		nuevonombre = new JTextField();
		nuevonombre.setBounds(301, 71, 167, 20);
		contentPane.add(nuevonombre);
		nuevonombre.setColumns(10);

		JLabel LblPredio = new JLabel("SeleccionarPredio");
		LblPredio.setBounds(101, 47, 128, 14);
		contentPane.add(LblPredio);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(241, 200, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(379, 200, 89, 23);
		contentPane.add(btnCancelar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(102, 200, 89, 23);
		contentPane.add(btnLimpiar);
		
		JComboBox comboBoxPropietario = new JComboBox();
		comboBoxPropietario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		comboBoxPropietario.setBounds(301, 153, 167, 22);
		contentPane.add(comboBoxPropietario);
		
		PropietariosBeanRemote propietarioBean = (PropietariosBeanRemote)
				InitialContext.doLookup("PROYECTO/PropietariosBean!com.servicios.PropietariosBeanRemote");
		
		List<Propietario> listaPropietario = propietarioBean.obtenerTodos();
		
		for (Propietario propietario : listaPropietario) {
			comboBoxPropietario.addItem(propietario);
		}
		JLabel lblNuevoPropietario = new JLabel("Nuevo Propietario");
		lblNuevoPropietario.setBounds(101, 156, 128, 16);
		contentPane.add(lblNuevoPropietario);
		
		nuevaArea = new JTextField();
		nuevaArea.setBounds(301, 118, 167, 22);
		contentPane.add(nuevaArea);
		nuevaArea.setColumns(10);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(101, 121, 56, 16);
		contentPane.add(lblArea);

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoPredio.dispose();
				new PantallaInicio();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if((Predio) comboBoxPredio.getSelectedItem() != null && !nuevonombre.getText().isEmpty()  
						&& (Propietario) comboBoxPropietario.getSelectedItem() != null){
				try {
					Predio predio = (Predio) comboBoxPredio.getSelectedItem();
					String nombre = nuevonombre.getText();
					Propietario propietario = (Propietario) comboBoxPropietario.getSelectedItem();
					long area = Long.parseLong(nuevaArea.getText());
					PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
							InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
				
					PrediosBean.modificarPredio(predio, area, nombre, propietario);
					JOptionPane.showMessageDialog(null, "Predio Modificado Correctamente", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ServiciosException | NamingException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(null, "No se pudo modificar el predio", "Notificacion",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}}else{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);}
				}
				
			}
		);

		btnLimpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nuevonombre.setText("");

			}
		});

	}
}
