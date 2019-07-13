package com.interfaz.potrero;

import java.awt.BorderLayout;



import java.awt.EventQueue;
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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import com.enumerados.Tipo_Documento;
import com.exception.ServiciosException;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PotrerosBeanRemote;
import com.servicios.PrediosBeanRemote;
import com.servicios.UsuariosBeanRemote;
import com.entities.Predio;
import com.entities.TipoUso;
import com.entities.Usuario;
import com.enumerados.Rol;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AltaPotrero  {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField area;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AltaPotrero() throws NamingException {
		
		JFrame frmCrearUnNuevo = new JFrame();
		frmCrearUnNuevo.setTitle("Crear un nuevo potrero");
		frmCrearUnNuevo.setResizable(false);
		
		frmCrearUnNuevo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrearUnNuevo.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmCrearUnNuevo.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Predio> comboBoxPotrero = new JComboBox<Predio>();
		comboBoxPotrero.setBounds(368, 13, 153, 22);
		comboBoxPotrero.setSelectedItem(null);
		contentPane.add(comboBoxPotrero);
		frmCrearUnNuevo.setSize(553, 399);
		frmCrearUnNuevo.setVisible(true);
		
		PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
				InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
		
		List<Predio> listaPredio = PrediosBean.obtenerTodosDisponibles();

		
		for (Predio predio : listaPredio) {
			comboBoxPotrero.addItem((Predio)predio);
		}
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel LblPredio = new JLabel("Predio");
		LblPredio.setBounds(279, 14, 107, 14);
		contentPane.add(LblPredio);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(316, 313, 89, 23);
		contentPane.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					PotrerosBeanRemote PotrerosBean = (PotrerosBeanRemote)
							InitialContext.doLookup("PROYECTO/PotrerosBean!com.servicios.PotrerosBeanRemote");
				
					PotrerosBean.altaPotrero(nombre.getText(), (Predio) comboBoxPotrero.getSelectedItem(), area.getText());
					JOptionPane.showMessageDialog(null,
							"Potrero Dado de alta correctamente", 
							"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"No se pudo crear el Potrero", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmCrearUnNuevo.dispose();
				new PantallaInicio();
			}
		});
			
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		area = new JTextField();
		area.setBounds(224, 70, 116, 22);
		contentPane.add(area);
		area.setColumns(10);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(156, 73, 56, 16);
		contentPane.add(lblArea);
		
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
					
			}
		});
		
	}	
}
	
	
