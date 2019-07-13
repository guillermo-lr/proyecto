package com.interfaz.predio;

import javax.naming.InitialContext;


import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.date.SerialDate;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;



public class HistoricoPredio2 {
	private JTable table;

	Date fecha = new Date();
	public HistoricoPredio2() throws NamingException {
		/**
		 * @wbp.nonvisual location=339,224
		 * @wbp.parser.entryPoint
		 */

		JFrame frmReporteDePotreros = new JFrame();
		frmReporteDePotreros.setTitle("Historial Predio");
		frmReporteDePotreros.setSize(1051, 740);
		JScrollPane sp = new JScrollPane();
		sp.setBounds(303, 34, 708, 328);
		sp.setViewportView(table);
		
		JLabel lblListaDeTodos = new JLabel("Historial de Predio");
		lblListaDeTodos.setBounds(26, 32, 304, 16);
		lblListaDeTodos.setFont(new Font("Tahoma", Font.BOLD, 18));

		frmReporteDePotreros.setVisible(true);
		//El siguiente combobox permite al usuario elejir el predio sobre el que quiere obtener el historial 
		JComboBox<Predio> comboBoxPredio = new JComboBox<Predio>();
		comboBoxPredio.setBounds(169, 164, 122, 22);
		comboBoxPredio.setEditable(true);
		
		
		
		//se obtiene combobox con predios
		PrediosBeanRemote PrediosBean = (PrediosBeanRemote) InitialContext
				.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
		List<Predio> listaPredio = PrediosBean.obtenerTodos();
		for (Predio predio : listaPredio) {
			comboBoxPredio.addItem((Predio) predio);
		}
		comboBoxPredio.setSelectedItem(null);
		Button btnObtenerHistorial = new Button("Obtener Historial");
		btnObtenerHistorial.setBounds(97, 263, 122, 24);
		
		
		//El siguiente combobox permite al usuario elejir el intervalo entre valor y valor 
		JComboBox<String> comboBoxPeriodo = new JComboBox<String>();
		comboBoxPeriodo.setBounds(169, 212, 122, 22);
		comboBoxPeriodo.addItem("Dia");
		comboBoxPeriodo.addItem("Semana");
		comboBoxPeriodo.addItem("Mes");
		comboBoxPeriodo.addItem("Trimestre");
		comboBoxPeriodo.addItem("Semestre");
		comboBoxPeriodo.addItem("Anual");
		
		JLabel lblSeleccioneUnPredio = new JLabel("Seleccione un predio");
		lblSeleccioneUnPredio.setBounds(26, 167, 131, 16);
		
		JLabel lblSeleccionePeriodos = new JLabel("Seleccione Periodo");
		lblSeleccionePeriodos.setBounds(26, 215, 122, 16);
		
		ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
				.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");

		
		//JFree chart es una libreria gratuita descargada de internet que permite mostrar graficos en swing
		JFreeChart graficaPredio = null;
		TimeSeriesCollection lineas = new TimeSeriesCollection(); //Se uso TimeSeriesCollection por ser una grafica fecha/valor
		graficaPredio = ChartFactory.createTimeSeriesChart("Grafico del predio", "Fecha", "Hectareas", lineas);
		//Aqui se crea la tabla,
		//en principio vacia pero el usuario puede completarla con los valores que tiene en la grafica
		//Los primeros tres Strings corresponden a titulo/leyenga y/ leyenda x y el ultimo a los valores de la grafica que aun no ha sido completada
		ChartPanel chartPanel = new ChartPanel(graficaPredio);
		chartPanel.setBounds(26, 375, 995, 266);
		chartPanel.setBackground(Color.BLACK);
		

		JButton generarGrafico = new JButton("Generar Grafico");
		generarGrafico.setBounds(349, 654, 173, 25);
		
		generarGrafico.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton button = new JButton("Salir");
		button.setBounds(924, 654, 97, 25);
		GroupLayout gl_chartPanel = new GroupLayout(chartPanel);
		gl_chartPanel.setHorizontalGroup(
			gl_chartPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 995, Short.MAX_VALUE)
		);
		gl_chartPanel.setVerticalGroup(
			gl_chartPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 266, Short.MAX_VALUE)
		);
		chartPanel.setLayout(gl_chartPanel);
		frmReporteDePotreros.getContentPane().setLayout(null);
		frmReporteDePotreros.getContentPane().add(comboBoxPeriodo);
		frmReporteDePotreros.getContentPane().add(sp);
		
		//Se crea la tabla vacia por el momento y se agrega a JScrolPane, el cual es un contenedor para la grafica
		JTable tabla = new JTable();
		sp.setViewportView(tabla);
		tabla.setCellSelectionEnabled(true);
		
				tabla.setFont(new Font("Tahoma", Font.BOLD, 13));
				tabla.setColumnSelectionAllowed(true);
				tabla.setFillsViewportHeight(true);
		frmReporteDePotreros.getContentPane().add(btnObtenerHistorial);
		frmReporteDePotreros.getContentPane().add(lblSeleccioneUnPredio);
		frmReporteDePotreros.getContentPane().add(comboBoxPredio);
		frmReporteDePotreros.getContentPane().add(lblSeleccionePeriodos);
		frmReporteDePotreros.getContentPane().add(lblListaDeTodos);
		frmReporteDePotreros.getContentPane().add(chartPanel);
		frmReporteDePotreros.getContentPane().add(generarGrafico);
		frmReporteDePotreros.getContentPane().add(button);
		
		
		
		
		
		
		
		
		//Con este algoritmo se obtiene el grafico de la tabla 

		generarGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				 lineas.removeAllSeries(); //Se remueve cualquier grafico anterior
				TableModel modeloGrafica = tabla.getModel(); //Se obtienen los datos de la tabla 
				int columnas = modeloGrafica.getColumnCount(); //Numero de columnas
				int filas = modeloGrafica.getRowCount(); //Numero de filas
				for (int i = 1; i < columnas; i++) { //Se graficara cada columnas, cada columna corresponde con un tipo de uso y el area
					TimeSeries serie = new TimeSeries(modeloGrafica.getColumnName(i)); //TimeSeries es el tipo de serie, se eligio este al ser el eye y fechas 
					
					for (int j = 0; j < filas ; j++) {//Por cada una de las filas de obtendra un valor que correspondera al y(fecha) y la x(numero de hectareas) 
						String fecha= (String) tabla.getValueAt(j, 0);//Se obtiene la fecha desde la tabla, la tabla la devuelve en formato object asi que le hacemos cast a string
				

						try {
							Date date1 = null;// se inicializa la variable fecha
							date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);//Se utiliza la string que obtuvimos de la fecha y la transformamos en una variable date con este formato 
							
							Long valor = (Long) tabla.getValueAt(j, i);//se obtiene la variable(cantidad de hectareas del uso) que se corresponde a la fecha anteriormenta obtenida
							serie.add(new Day(date1), valor.doubleValue());//se agrega el valor a la linea grafica 
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					lineas.addSeries(serie);//se agrega la linea grafica al grafico 
				}
				}
		});
		//El siguiente boton reyenara la tabla de valores segun el potrero e intervalo que el usuario haya elegido
		btnObtenerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
							.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");
					tabla.setModel(
							ReporteBean.reporteHistoricoPredio((Predio) comboBoxPredio.getSelectedItem(), comboBoxPeriodo.getSelectedIndex()));

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
		

		//La funcion del boton salir es volver a la pantalla de inicio, si quiere salir del sistema debe sar click en el cruz 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmReporteDePotreros.dispose();
				new PantallaInicio();
			}
		});
	}
}
