package com.interfaz.zona;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
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
import com.servicios.Tipo_UsosBeanRemote;
import com.servicios.Zona_UsosBeanRemote;
import com.servicios.ZonasBeanRemote;
import com.entities.TipoUso;
import com.entities.Zona;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AsignarNuevoUso {

	Date fechaLocal = new Date();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 * 
	 * @throws NamingException
	 */
	public AsignarNuevoUso() throws NamingException {
		
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
		comboBox.setBounds(310, 61, 167, 22);
		contentPane.add(comboBox);
		frmNuevoPropietario.setSize(549, 473);
		frmNuevoPropietario.setVisible(true);
		ZonasBeanRemote ZonasBean = (ZonasBeanRemote) InitialContext
				.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");
		
		List<Zona> listaZonas = ZonasBean.obtenerTodos();

		for (Zona zona : listaZonas) {
			comboBox.addItem((Zona) zona);
		}
		

		JLabel LblPropietario = new JLabel("Seleccionar Zona");
		LblPropietario.setBounds(136, 65, 137, 14);
		contentPane.add(LblPropietario);

		JButton btnAceptar = new JButton("Asignar");
		btnAceptar.setBounds(136, 390, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(378, 390, 89, 23);
		contentPane.add(btnCancelar);
		comboBox.setSelectedItem(null);
		
		JComboBox<TipoUso> comboBoxTipo_Uso = new JComboBox<TipoUso>();
		comboBoxTipo_Uso.setBounds(310, 169, 167, 22);
		contentPane.add(comboBoxTipo_Uso);
		Tipo_UsosBeanRemote tipo_UsosBean = (Tipo_UsosBeanRemote)
				InitialContext.doLookup("PROYECTO/Tipo_UsosBean!com.servicios.Tipo_UsosBeanRemote");
		
		
		List<TipoUso> listaTipo_Uso = tipo_UsosBean.obtenerTipo_Uso();

		for (TipoUso tipo_Uso : listaTipo_Uso) {
			comboBoxTipo_Uso.addItem((TipoUso) tipo_Uso);
		}
		
		JLabel lblSeleccionarPotrero = new JLabel("Seleccionar Uso");
		lblSeleccionarPotrero.setBounds(136, 172, 139, 16);
		contentPane.add(lblSeleccionarPotrero);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(218, 92, 56, 16);
		contentPane.add(lblArea);
		
		JLabel lblPotrero = new JLabel("Potrero:");
		lblPotrero.setBounds(217, 114, 56, 16);
		contentPane.add(lblPotrero);
		
		JLabel lblNewLabel = new JLabel("Propietario:");
		lblNewLabel.setBounds(218, 140, 89, 16);
		contentPane.add(lblNewLabel);
		
		JLabel area = new JLabel("");
		area.setBounds(310, 92, 56, 16);
		contentPane.add(area);
		
		JLabel potrero = new JLabel("");
		potrero.setBounds(310, 114, 56, 16);
		contentPane.add(potrero);
		
		JLabel label = new JLabel("");
		label.setBounds(310, 140, 56, 16);
		contentPane.add(label);
		
		JLabel propietario = new JLabel("");
		propietario.setBounds(310, 143, 56, 16);
		contentPane.add(propietario);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Zona zona = (Zona) arg0.getItem();
				area.setText(zona.getAreaZona().toString());
				potrero.setText(zona.getPotrero().getNomPotrero());
				propietario.setText(zona.getPotrero().getPredio().getPropietario().getNomPropietario());
			}
		});

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoPropietario.dispose();
				new PantallaInicio();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {Zona_UsosBeanRemote Zona_UsosBean = (Zona_UsosBeanRemote) InitialContext
						.doLookup("PROYECTO/Zona_UsosBean!com.servicios.Zona_UsosBeanRemote");

					Zona zona = (Zona) comboBox.getSelectedItem();
					TipoUso tipo_Uso = (TipoUso) comboBoxTipo_Uso.getSelectedItem();
					Zona_UsosBean.altaZona_Uso(zona, tipo_Uso, fechaLocal);
					JOptionPane.showMessageDialog(null, "Se ha asignado la zona Correctamente", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "No se pudo asignar Zona Correctamente", "Notificacion",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		

	}
}
