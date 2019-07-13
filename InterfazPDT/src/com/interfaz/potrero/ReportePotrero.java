package com.interfaz.potrero;

import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import com.entities.Potrero;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PotrerosBeanRemote;
import com.servicios.ReporteBeanRemote;
import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ReportePotrero {
	private JTable table;
	private JDateChooser fechaElegida;

	Date fecha = new Date();//se obtiene la fecha actual

	public ReportePotrero() throws NamingException {

		JFrame frmReporteDePotreros = new JFrame();
		frmReporteDePotreros.setTitle("Reporte De Potreros");
		frmReporteDePotreros.setSize(1064, 424);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 122, 152, 169, 291, 97, 92, 97, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 16, 52, 24, 185, 41, 19, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmReporteDePotreros.getContentPane().setLayout(gridBagLayout);
		JLabel lblListaDeTodos = new JLabel("Historial de Potreros ");
		lblListaDeTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblListaDeTodos = new GridBagConstraints();
		gbc_lblListaDeTodos.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblListaDeTodos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDeTodos.gridwidth = 3;
		gbc_lblListaDeTodos.gridx = 1;
		gbc_lblListaDeTodos.gridy = 1;
		frmReporteDePotreros.getContentPane().add(lblListaDeTodos, gbc_lblListaDeTodos);
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(table);
		
				GridBagConstraints gbc_sp = new GridBagConstraints();
				gbc_sp.fill = GridBagConstraints.BOTH;
				gbc_sp.insets = new Insets(0, 0, 5, 5);
				gbc_sp.gridheight = 4;
				gbc_sp.gridwidth = 5;
				gbc_sp.gridx = 3;
				gbc_sp.gridy = 1;
				gbc_sp.weightx = 1;
				gbc_sp.weighty = 1;

				frmReporteDePotreros.getContentPane().add(sp, gbc_sp);
				JTable tabla = new JTable();
				sp.setViewportView(tabla);
				ReporteBeanRemote ReporteBean = (ReporteBeanRemote)

						InitialContext.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");
			
						tabla.setFont(new Font("Tahoma", Font.BOLD, 13));
						tabla.setColumnSelectionAllowed(true);
						tabla.setFillsViewportHeight(true);
						tabla.setCellSelectionEnabled(true);
						
								tabla.setModel(ReporteBean.reporteTodosPotreros(fecha, true));
		
		
		PotrerosBeanRemote PotrerosBean = (PotrerosBeanRemote) InitialContext
				.doLookup("PROYECTO/PotrerosBean!com.servicios.PotrerosBeanRemote");
		List<Potrero> listaPotrero = PotrerosBean.obtenerTodosDisponibles();

		
		//se genera una lista con todos los potreros
		DefaultListModel<Potrero> lista = new DefaultListModel<>();
		
		for (Potrero potrero : listaPotrero) {
			lista.addElement(potrero);
		}
		Button seleccionarFecha = new Button("Seleccionar Fecha");
		
		GridBagConstraints gbc_seleccionarFecha = new GridBagConstraints();
		gbc_seleccionarFecha.anchor = GridBagConstraints.NORTH;
		gbc_seleccionarFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_seleccionarFecha.insets = new Insets(0, 0, 5, 5);
		gbc_seleccionarFecha.gridx = 1;
		gbc_seleccionarFecha.gridy = 3;
		frmReporteDePotreros.getContentPane().add(seleccionarFecha, gbc_seleccionarFecha);
		
		
		
		
		seleccionarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				fecha = fechaElegida.getDate();

				try {
					ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
							.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");
					tabla.setModel(ReporteBean.reporteTodosPotreros(fecha, true));

				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		

		//el sigiente objeto permite elegir una fecha
		fechaElegida = new JDateChooser();
		
				GridBagConstraints gbc_fechaElegida = new GridBagConstraints();
				gbc_fechaElegida.anchor = GridBagConstraints.NORTH;
				gbc_fechaElegida.fill = GridBagConstraints.HORIZONTAL;
				gbc_fechaElegida.insets = new Insets(0, 0, 5, 5);
				gbc_fechaElegida.gridx = 2;
				gbc_fechaElegida.gridy = 3;
				frmReporteDePotreros.getContentPane().add(fechaElegida, gbc_fechaElegida);
		JList list = new JList(lista);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridheight = 2;
		gbc_list.gridwidth = 2;
		gbc_list.gridx = 1;
		gbc_list.gridy = 4;
		frmReporteDePotreros.getContentPane().add(list, gbc_list);
		
		JButton btnSoloSeleccionador = new JButton("Obtener Seleccionados");
		//el siguiente metodo obtiene el historial para los potreros seleccionados 
		btnSoloSeleccionador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ReporteBeanRemote ReporteBean = (ReporteBeanRemote)

							InitialContext.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");
					
					if(!list.getSelectedValuesList().isEmpty())//comprueba que se haya seleccionado algun potrero
					{
						
						//se pasa la fecha elegida junto con la lista de potreros seleccionada 
						tabla.setModel(ReporteBean.reportePotrerosSeleccionados(fecha, list.getSelectedValuesList()));}
				
					else{
						JOptionPane.showMessageDialog(null, "No ha seleccionado ningun potrero", "Intentelo Nuevamente",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				}
				
			}
		);
		GridBagConstraints gbc_btnSoloSeleccionador = new GridBagConstraints();
		gbc_btnSoloSeleccionador.anchor = GridBagConstraints.NORTH;
		gbc_btnSoloSeleccionador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSoloSeleccionador.insets = new Insets(0, 0, 5, 5);
		gbc_btnSoloSeleccionador.gridx = 3;
		gbc_btnSoloSeleccionador.gridy = 5;
		frmReporteDePotreros.getContentPane().add(btnSoloSeleccionador, gbc_btnSoloSeleccionador);
		JButton btnLimpiar = new JButton("Limpiar");
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpiar.gridx = 5;
		gbc_btnLimpiar.gridy = 5;
		frmReporteDePotreros.getContentPane().add(btnLimpiar, gbc_btnLimpiar);
		
		

		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ReporteBeanRemote ReporteBean;

					ReporteBean = (ReporteBeanRemote)

					InitialContext.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");

					tabla.setModel(ReporteBean.reporteTodosPotreros(fecha, true));//rellena la tabla con los datos de la actualidad y todos los potreros
					list.clearSelection();//limpia la seleccion de potreros
					fecha = new Date();//vuelve a la fecha actual

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
				
						JButton btnSalir = new JButton("Salir");
						
						GridBagConstraints gbc_btnSalir = new GridBagConstraints();
						gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
						gbc_btnSalir.anchor = GridBagConstraints.NORTHEAST;
						gbc_btnSalir.gridx = 6;
						gbc_btnSalir.gridy = 5;
						frmReporteDePotreros.getContentPane().add(btnSalir, gbc_btnSalir);
				//vuelve al inicio
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						new PantallaInicio();
						frmReporteDePotreros.dispose();
					}
				});
		ReporteBeanRemote ReporteBean1 = (ReporteBeanRemote) InitialContext
				.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");

		frmReporteDePotreros.setVisible(true);
		
	}
	
	
}
