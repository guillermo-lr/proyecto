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
import javax.swing.JTextField;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Potrero;
import com.entities.TipoUso;
import com.interfaz.usuario.PantallaInicio;
import com.servicios.PotrerosBeanRemote;
import com.servicios.Tipo_UsosBeanRemote;
import com.servicios.ZonasBeanRemote;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AsignarNuevaZona  {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField Area;
	private JTextField Descripcion;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AsignarNuevaZona() throws NamingException {
		JFrame frmNuevaZona = new JFrame();
		frmNuevaZona.setTitle("Nueva Zona");
		
		frmNuevaZona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevaZona.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevaZona.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(83, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setBounds(279, 14, 107, 14);
		contentPane.add(lblArea);
		
		Area = new JTextField();
		Area.setColumns(10);
		Area.setBounds(354, 11, 167, 20);
		contentPane.add(Area);
		
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
		frmNuevaZona.setSize(428, 321);
		frmNuevaZona.setVisible(true);

		JComboBox<Potrero> comboBoxPotrero = new JComboBox<Potrero>();
		comboBoxPotrero.setBounds(191, 43, 184, 22);
		contentPane.add(comboBoxPotrero);
		
		PotrerosBeanRemote PotrerosBean = (PotrerosBeanRemote) InitialContext
				.doLookup("PROYECTO/PotrerosBean!com.servicios.PotrerosBeanRemote");
		
		List<Potrero> listaPotrero = PotrerosBean.obtenerTodosDisponibles();

		for (Potrero potrero : listaPotrero) {
			
				comboBoxPotrero.addItem((Potrero) potrero);
			
		}
		comboBoxPotrero.setSelectedItem(null);
		JLabel LblPotrero = new JLabel("Seleccionar Potrero");
		LblPotrero.setBounds(51, 47, 128, 14);
		contentPane.add(LblPotrero);

		
		
		
			
			
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(432, 313, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmNuevaZona.dispose();
				new PantallaInicio();
			}
		});
			
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(199, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		JComboBox<TipoUso> comboBoxUso = new JComboBox<TipoUso>();
		comboBoxUso.setBounds(191, 88, 184, 22);
		contentPane.add(comboBoxUso);
		Tipo_UsosBeanRemote tipo_UsosBean = (Tipo_UsosBeanRemote)
				InitialContext.doLookup("PROYECTO/Tipo_UsosBean!com.servicios.Tipo_UsosBeanRemote");
		List<TipoUso> usos = tipo_UsosBean.obtenerTipo_Uso();
		for (TipoUso tipoUso : usos) {
			comboBoxUso.addItem(tipoUso);
		}
		
		JLabel lblSeleccionarUso = new JLabel("Seleccionar Uso");
		lblSeleccionarUso.setBounds(51, 91, 112, 16);
		contentPane.add(lblSeleccionarUso);
		frmNuevaZona.setSize(553, 399);
		frmNuevaZona.setVisible(true);
		btnAceptar.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {ZonasBeanRemote zonaBean = (ZonasBeanRemote)
					InitialContext.doLookup("PROYECTO/ZonasBean!com.servicios.ZonasBeanRemote");
			
				
					
				
				zonaBean.altaZona(nombre.getText(), Long.parseLong(Area.getText()), Descripcion.getText(),
						(Potrero) comboBoxPotrero.getSelectedItem(), (TipoUso) comboBoxUso.getSelectedItem());

			
				JOptionPane.showMessageDialog(null,
						"Zona Dada de alta correctamente", 
						"Notificacion", JOptionPane.INFORMATION_MESSAGE);
				
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"No se pudo crear la Zona", 
						"Notificacion", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
		}
	});
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
					Area.setText("");
					Descripcion.setText("");
			}
		});
		
	}
}
