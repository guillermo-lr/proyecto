package com.interfaz.potrero;

import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.TipoUso;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PotrerosBeanRemote;
import com.servicios.PrediosBean;
import com.servicios.PrediosBeanRemote;
import com.servicios.ReporteBean;
import com.servicios.ReporteBeanRemote;
import com.servicios.Tipo_UsosBeanRemote;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.Label;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.beans.PropertyChangeEvent;
import java.awt.Button;
import javax.swing.SwingConstants;

public class ReportePotrero2 {
	private JTable table;
	private JDateChooser fechaElegida;

	Date fecha = new Date();//se obtiene la fecha actual

	public ReportePotrero2() throws NamingException {

		JFrame frmReporteDePotreros = new JFrame();
		frmReporteDePotreros.setResizable(false);
		frmReporteDePotreros.setTitle("Reporte De Potreros");
		frmReporteDePotreros.setSize(1395, 424);
		frmReporteDePotreros.getContentPane().setLayout(null);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(319, 34, 1046, 291);
		sp.setViewportView(table);

		frmReporteDePotreros.getContentPane().add(sp);
		JTable tabla = new JTable();
		sp.setViewportView(tabla);

		tabla.setFont(new Font("Tahoma", Font.BOLD, 13));
		tabla.setColumnSelectionAllowed(true);
		tabla.setFillsViewportHeight(true);
		tabla.setCellSelectionEnabled(true);
		ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
				.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");

		tabla.setModel(ReporteBean.reporteTodosPotreros(fecha, true));
		JLabel lblListaDeTodos = new JLabel("Historial de Potreros ");
		lblListaDeTodos.setBounds(27, 26, 304, 16);
		lblListaDeTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmReporteDePotreros.getContentPane().add(lblListaDeTodos);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(1268, 338, 97, 25);
		
		frmReporteDePotreros.getContentPane().add(btnSalir);

		frmReporteDePotreros.setVisible(true);
		

		//el sigiente objeto permite elegir una fecha
		fechaElegida = new JDateChooser();
		fechaElegida.setBounds(27, 94, 122, 22);

		frmReporteDePotreros.getContentPane().add(fechaElegida);
		Button seleccionarFecha = new Button("Seleccionar Fecha");
		seleccionarFecha.setBounds(155, 94, 152, 24);
		
		frmReporteDePotreros.getContentPane().add(seleccionarFecha);
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(1160, 338, 97, 25);
		frmReporteDePotreros.getContentPane().add(btnLimpiar);
		PotrerosBeanRemote PotrerosBean = (PotrerosBeanRemote) InitialContext
				.doLookup("PROYECTO/PotrerosBean!com.servicios.PotrerosBeanRemote");
		List<Potrero> listaPotrero = PotrerosBean.obtenerTodosDisponibles();

		
		//se genera una lista con todos los potreros
		DefaultListModel<Potrero> lista = new DefaultListModel<>();
		
		for (Potrero potrero : listaPotrero) {
			lista.addElement(potrero);
		}
		JList list = new JList(lista);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );//permite elegir mas de un potrero
		
		list.setBounds(27, 140, 278, 239);
		frmReporteDePotreros.getContentPane().add(list);
		
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
		btnSoloSeleccionador.setBounds(319, 338, 169, 25);
		frmReporteDePotreros.getContentPane().add(btnSoloSeleccionador);
		
		

		
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
		//vuelve al inicio
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				new PantallaInicio();
				frmReporteDePotreros.dispose();
			}
		});
	}
	
	
}
