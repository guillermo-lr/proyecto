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
import com.interfaz.usuario.PantallaInicio;
import com.servicios.ZonasBeanRemote;
import com.entities.Zona;
import javax.swing.JButton;
import java.awt.Font;

public class EliminarZona {

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
	public EliminarZona() throws NamingException {
		ZonasBeanRemote ZonasBean = (ZonasBeanRemote)
				InitialContext.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");
		
		JFrame frmNuevoZona = new JFrame();
		frmNuevoZona.setFont(new Font("Dialog", Font.BOLD, 16));
		frmNuevoZona.setTitle("Eliminar Zona");

		frmNuevoZona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoZona.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoZona.setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Zona> comboBox = new JComboBox<Zona>();
		comboBox.setBounds(191, 43, 184, 22);
		contentPane.add(comboBox);
		frmNuevoZona.setSize(428, 321);
		frmNuevoZona.setVisible(true);

		List<Zona> listaZona = ZonasBean.obtenerTodos();

		for (Zona zona : listaZona) {
			 
				comboBox.addItem((Zona) zona);
			
		}
		comboBox.setSelectedItem(null);
		JLabel LblZona = new JLabel("Seleccionar Zona");
		LblZona.setBounds(51, 47, 128, 14);
		contentPane.add(LblZona);

		JButton btnAceptar = new JButton("Eliminar");
		btnAceptar.setBounds(51, 200, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(286, 200, 89, 23);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoZona.dispose();
				new PantallaInicio();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {	
					ZonasBeanRemote ZonasBean = (ZonasBeanRemote)
						InitialContext.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");
				
					Zona zona = (Zona) comboBox.getSelectedItem();
					ZonasBean.eliminarZona(zona);
					JOptionPane.showMessageDialog(null, "Zona Eliminado Correctamente", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block

					JOptionPane.showMessageDialog(null, "No se pudo eliminar zona", "Notificacion",
							JOptionPane.WARNING_MESSAGE);

					e1.printStackTrace();
				}
			}
		});

	}
}
