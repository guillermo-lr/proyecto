package com.interfaz.predio;

import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.entities.Predio;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PrediosBeanRemote;
import com.servicios.ReporteBeanRemote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Button;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class HistoricoPredio {
	private JTable table;

	Date fecha = new Date();
	public HistoricoPredio() throws NamingException {
		/**
		 * @wbp.nonvisual location=339,224
		 * @wbp.parser.entryPoint
		 */

		JFrame frmReporteDePotreros = new JFrame();
		frmReporteDePotreros.setTitle("Historial Predio");
		frmReporteDePotreros.setSize(1052, 740);

		frmReporteDePotreros.setVisible(true);
		
		
		
		
		ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
				.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");

		
		//JFree chart es una libreria gratuita descargada de internet que permite mostrar graficos en swing
		JFreeChart graficaPredio = null;
		TimeSeriesCollection lineas = new TimeSeriesCollection(); //Se uso TimeSeriesCollection por ser una grafica fecha/valor
		graficaPredio = ChartFactory.createTimeSeriesChart("Grafico del predio", "Fecha", "Hectareas", lineas);
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		gridBagLayout.columnWidths = new int[]{0, 33, 122, 11, 27, 173, 402, 97, 0, 0};
		gridBagLayout.rowHeights = new int[]{32, 0, 16, 116, 22, 22, 99, 338, 103, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.1, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0., 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmReporteDePotreros.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblListaDeTodos = new JLabel("Historial de Predio");
		lblListaDeTodos.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblListaDeTodos = new GridBagConstraints();
		gbc_lblListaDeTodos.fill = GridBagConstraints.VERTICAL;
		gbc_lblListaDeTodos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDeTodos.gridwidth = 2;
		gbc_lblListaDeTodos.gridx = 1;
		gbc_lblListaDeTodos.gridy = 2;
		frmReporteDePotreros.getContentPane().add(lblListaDeTodos, gbc_lblListaDeTodos);
		JScrollPane contendorTabla = new JScrollPane();
		contendorTabla.setViewportView(table);
		GridBagConstraints gbc_contendorTabla = new GridBagConstraints();
		gbc_contendorTabla.fill = GridBagConstraints.BOTH;
		gbc_contendorTabla.insets = new Insets(0, 0, 5, 5);
		gbc_contendorTabla.gridheight = 5;
		gbc_contendorTabla.gridwidth = 4;
		gbc_contendorTabla.gridx = 4;
		gbc_contendorTabla.gridy = 2;
		gbc_contendorTabla.weightx = 1;
		gbc_contendorTabla.ipadx = 1;
		frmReporteDePotreros.getContentPane().add(contendorTabla, gbc_contendorTabla);
		
		//Se crea la tabla vacia por el momento y se agrega a JScrolPane, el cual es un contenedor para la grafica
		JTable tablaPredio = new JTable();
		contendorTabla.setViewportView(tablaPredio);
		tablaPredio.setCellSelectionEnabled(true);
		
				tablaPredio.setFont(new Font("Tahoma", Font.BOLD, 13));
				tablaPredio.setColumnSelectionAllowed(true);
				tablaPredio.setFillsViewportHeight(true);
		
		JLabel lblSeleccioneUnPredio = new JLabel("Seleccione un predio");
		GridBagConstraints gbc_lblSeleccioneUnPredio = new GridBagConstraints();
		gbc_lblSeleccioneUnPredio.anchor = GridBagConstraints.WEST;
		gbc_lblSeleccioneUnPredio.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneUnPredio.gridx = 1;
		gbc_lblSeleccioneUnPredio.gridy = 4;
		
		frmReporteDePotreros.getContentPane().add(lblSeleccioneUnPredio, gbc_lblSeleccioneUnPredio);
		//El siguiente combobox permite al usuario elejir el predio sobre el que quiere obtener el historial 
		JComboBox<Predio> comboBoxPredio = new JComboBox<Predio>();
		comboBoxPredio.setEditable(true);
		comboBoxPredio.setSelectedItem(null);
		GridBagConstraints gbc_comboBoxPredio = new GridBagConstraints();
		gbc_comboBoxPredio.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxPredio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPredio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPredio.gridx = 2;
		gbc_comboBoxPredio.gridy = 4;
		frmReporteDePotreros.getContentPane().add(comboBoxPredio, gbc_comboBoxPredio);
		
		
		//El siguiente combobox permite al usuario elejir el intervalo entre valor y valor 
		JComboBox<String> comboBoxPeriodo = new JComboBox<String>();
		comboBoxPeriodo.addItem("Dia");
		comboBoxPeriodo.addItem("Semana");
		comboBoxPeriodo.addItem("Mes");
		comboBoxPeriodo.addItem("Trimestre");
		comboBoxPeriodo.addItem("Semestre");
		comboBoxPeriodo.addItem("Anual");
		
		JLabel lblSeleccionePeriodos = new JLabel("Seleccione Periodo");
		GridBagConstraints gbc_lblSeleccionePeriodos = new GridBagConstraints();
		gbc_lblSeleccionePeriodos.anchor = GridBagConstraints.WEST;
		gbc_lblSeleccionePeriodos.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionePeriodos.gridx = 1;
		gbc_lblSeleccionePeriodos.gridy = 5;
		frmReporteDePotreros.getContentPane().add(lblSeleccionePeriodos, gbc_lblSeleccionePeriodos);
		GridBagConstraints gbc_comboBoxPeriodo = new GridBagConstraints();
		gbc_comboBoxPeriodo.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxPeriodo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPeriodo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPeriodo.gridx = 2;
		gbc_comboBoxPeriodo.gridy = 5;
		frmReporteDePotreros.getContentPane().add(comboBoxPeriodo, gbc_comboBoxPeriodo);
		Button btnObtenerHistorial = new Button("Obtener Historial");
		GridBagConstraints gbc_btnObtenerHistorial = new GridBagConstraints();
		gbc_btnObtenerHistorial.anchor = GridBagConstraints.NORTH;
		gbc_btnObtenerHistorial.insets = new Insets(0, 0, 5, 5);
		gbc_btnObtenerHistorial.gridwidth = 2;
		gbc_btnObtenerHistorial.gridx = 1;
		gbc_btnObtenerHistorial.gridy = 6;
		
		
		

		//se obtiene combobox con predios
		PrediosBeanRemote PrediosBean = (PrediosBeanRemote) InitialContext
				.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
		List<Predio> listaPredio = PrediosBean.obtenerTodos();
		for (Predio predio : listaPredio) {
			comboBoxPredio.addItem((Predio) predio);
		}
		frmReporteDePotreros.getContentPane().add(btnObtenerHistorial, gbc_btnObtenerHistorial);
		//El siguiente boton reyenara la tabla de valores segun el potrero e intervalo que el usuario haya elegido
		btnObtenerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//verifica que el predio exista
					if(listaPredio.contains(comboBoxPredio.getSelectedItem())) {
					
					ReporteBeanRemote ReporteBean = (ReporteBeanRemote) InitialContext
							.doLookup("PROYECTO/ReporteBean!com.servicios.ReporteBeanRemote");
					//completa la tabla 
					tablaPredio.setModel(
							ReporteBean.reporteHistoricoPredio((Predio) comboBoxPredio.getSelectedItem(), comboBoxPeriodo.getSelectedIndex()));
					}
					else{
						
						//si no existe manda mensaje de error
						JOptionPane.showMessageDialog(null, "No se ha encontrado predio solicitado", "Notificacion",
								JOptionPane.INFORMATION_MESSAGE);}
					}
				
			
				 catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		//Aqui se crea la tabla,
		//en principio vacia pero el usuario puede completarla con los valores que tiene en la grafica
		//Los primeros tres Strings corresponden a titulo/leyenga y/ leyenda x y el ultimo a los valores de la grafica que aun no ha sido completada
		ChartPanel contenedorGradica = new ChartPanel(graficaPredio);
		contenedorGradica.setBackground(Color.BLACK);
		GroupLayout gl_contenedorGradica = new GroupLayout(contenedorGradica);
		gl_contenedorGradica.setHorizontalGroup(
			gl_contenedorGradica.createParallelGroup(Alignment.LEADING)
				.addGap(0, 995, Short.MAX_VALUE)
		);
		gl_contenedorGradica.setVerticalGroup(
			gl_contenedorGradica.createParallelGroup(Alignment.LEADING)
				.addGap(0, 266, Short.MAX_VALUE)
		);
		contenedorGradica.setLayout(gl_contenedorGradica);
		GridBagConstraints gbc_contenedorGradica = new GridBagConstraints();
		gbc_contenedorGradica.fill = GridBagConstraints.BOTH;
		gbc_contenedorGradica.insets = new Insets(2, 2, 5, 5);
		gbc_contenedorGradica.gridwidth = 7;
		gbc_contenedorGradica.gridx = 1;
		gbc_contenedorGradica.gridy = 7;
		gbc_contenedorGradica.ipadx = 1;
		gbc_contenedorGradica.ipady = 1;
		frmReporteDePotreros.getContentPane().add(contenedorGradica, gbc_contenedorGradica);
		

		JButton generarGrafico = new JButton("Generar Grafico");
		
		generarGrafico.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_generarGrafico = new GridBagConstraints();
		gbc_generarGrafico.anchor = GridBagConstraints.NORTH;
		gbc_generarGrafico.fill = GridBagConstraints.NONE;
		gbc_generarGrafico.insets = new Insets(0, 0, 0, 5);
		gbc_generarGrafico.gridx = 5;
		gbc_generarGrafico.gridy = 8;
		frmReporteDePotreros.getContentPane().add(generarGrafico, gbc_generarGrafico);
		
		
		
		
		
		
		
		
		//Con este algoritmo se obtiene el grafico de la tabla 

		generarGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 lineas.removeAllSeries(); //Se remueve cualquier grafico anterior
				TableModel modeloGrafica = tablaPredio.getModel(); //Se obtienen los datos de la tabla 
				int columnas = modeloGrafica.getColumnCount(); //Numero de columnas
				int filas = modeloGrafica.getRowCount(); //Numero de filas
				for (int i = 1; i < columnas; i++) { //Se graficara cada columnas, cada columna corresponde con un tipo de uso y el area
					TimeSeries serie = new TimeSeries(modeloGrafica.getColumnName(i)); //TimeSeries es el tipo de serie, se eligio este al ser el eye y fechas 
					
					for (int j = 0; j < filas ; j++) {//Por cada una de las filas de obtendra un valor que correspondera al y(fecha) y la x(numero de hectareas) 
						String fecha= (String) tablaPredio.getValueAt(j, 0);//Se obtiene la fecha desde la tabla, la tabla la devuelve en formato object asi que le hacemos cast a string
				

						try {
							Date date1 = null;// se inicializa la variable fecha
							date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);//Se utiliza la string que obtuvimos de la fecha y la transformamos en una variable date con este formato 
							
							Long valor = (Long) tablaPredio.getValueAt(j, i);//se obtiene la variable(cantidad de hectareas del uso) que se corresponde a la fecha anteriormenta obtenida
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
		
		JButton button = new JButton("Salir");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridx = 7;
		gbc_button.gridy = 8;
		frmReporteDePotreros.getContentPane().add(button, gbc_button);
		
		
		

		//La funcion del boton salir es volver a la pantalla de inicio, si quiere salir del sistema debe sar click en el cruz 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmReporteDePotreros.dispose();
				new PantallaInicio();
			}
		});
	}
}
