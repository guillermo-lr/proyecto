package com.interfaz.zona;

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
import com.exception.ServiciosException;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PotrerosBeanRemote;
import com.servicios.ZonasBeanRemote;
import com.entities.Potrero;
import com.entities.Zona;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AsignarZona {

	  LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	ZonasBeanRemote ZonasBean = (ZonasBeanRemote) InitialContext
			.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public AsignarZona() throws NamingException {
		JFrame frmNuevoPropietario = new JFrame();
		frmNuevoPropietario.setFont(new Font("Dialog", Font.BOLD, 16));
		frmNuevoPropietario.setTitle("Asignar Zona");

		frmNuevoPropietario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoPropietario.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoPropietario.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		

		JComboBox<Zona> comboBox = new JComboBox<Zona>();
		
		comboBox.setSelectedItem(null);
		comboBox.setBounds(231, 187, 167, 22);
		contentPane.add(comboBox);
		frmNuevoPropietario.setSize(493, 473);
		frmNuevoPropietario.setVisible(true);

		List<Zona> listaZonas = ZonasBean.obtenerTodos();

		for (Zona zona : listaZonas) {
			comboBox.addItem((Zona) zona);
		}
		

		JLabel LblPropietario = new JLabel("Seleccionar Zona");
		LblPropietario.setBounds(102, 191, 137, 14);
		contentPane.add(LblPropietario);

		JButton btnAceptar = new JButton("Asignar");
		btnAceptar.setBounds(102, 390, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(309, 390, 89, 23);
		contentPane.add(btnCancelar);
		comboBox.setSelectedItem(null);
		
		JComboBox<Potrero> comboBoxPotrero = new JComboBox<Potrero>();
		comboBoxPotrero.setBounds(231, 124, 167, 22);
		contentPane.add(comboBoxPotrero);
		PotrerosBeanRemote PotrerosBean = (PotrerosBeanRemote)
				InitialContext.doLookup("PROYECTO/PotrerosBean!com.servicios.PotrerosBeanRemote");
		
		List<Potrero> listaPotrero = PotrerosBean.obtenerTodosDisponibles();

		for (Potrero potrero : listaPotrero) {
			comboBoxPotrero.addItem((Potrero) potrero);
		}
		
		JLabel lblSeleccionarPotrero = new JLabel("Seleccionar Potrero");
		lblSeleccionarPotrero.setBounds(100, 127, 139, 16);
		contentPane.add(lblSeleccionarPotrero);

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoPropietario.dispose();
				new PantallaInicio();
			}
		});
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ZonasBeanRemote ZonasBean = (ZonasBeanRemote) InitialContext
							.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");

					
					Zona zona = (Zona) comboBox.getSelectedItem();
					Potrero potrero = (Potrero) comboBoxPotrero.getSelectedItem();
					zona.setPotrero(potrero);
					ZonasBean.actualizarZona(zona);
					JOptionPane.showMessageDialog(null, "Se ha asignado la zona Correctamente", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ServiciosException | NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "No se pudo asignar Zona Correctamente", "Notificacion",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		

	}
}
