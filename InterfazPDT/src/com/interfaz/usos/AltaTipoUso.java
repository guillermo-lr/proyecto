package com.interfaz.usos;

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
import com.servicios.PrediosBeanRemote;
import com.servicios.Tipo_UsosBeanRemote;
import com.servicios.UsuariosBeanRemote;
import com.entities.TipoUso;
import com.entities.Usuario;
import com.enumerados.Rol;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AltaTipoUso  {

	LocalDate fechaLocal = LocalDate.now();

	private JPanel contentPane;
	private JTextField nombre;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public AltaTipoUso() throws NamingException {
		JFrame frmNuevoTipoDe = new JFrame();
		frmNuevoTipoDe.setResizable(false);
		frmNuevoTipoDe.setTitle("Nuevo Tipo de Uso");
		
		Tipo_UsosBeanRemote tipo_UsosBean = (Tipo_UsosBeanRemote)
				InitialContext.doLookup("PROYECTO/Tipo_UsosBean!com.servicios.Tipo_UsosBeanRemote");
		
		
		frmNuevoTipoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoTipoDe.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoTipoDe.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 14, 107, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(136, 11, 167, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel Tipo = new JLabel("Tipo de uso padre");
		Tipo.setBounds(10, 65, 107, 14);
		contentPane.add(Tipo);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(129, 221, 89, 23);
		contentPane.add(btnAceptar);

		JComboBox<TipoUso> tipoUsoPadre = new JComboBox<TipoUso>();
		List<TipoUso> listaUsos = tipo_UsosBean.obtenerTipo_Uso();
		tipoUsoPadre.setSelectedItem(null);
		
		for (TipoUso tipo_Uso2 : listaUsos) {
			tipoUsoPadre.addItem((TipoUso) tipo_Uso2);
		}
		tipoUsoPadre.setBounds(136, 61, 167, 22);
		contentPane.add(tipoUsoPadre);
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
							tipo_UsosBean.altaTipo_Uso(nombre.getText(),
									(TipoUso) tipoUsoPadre.getSelectedItem());
							JOptionPane.showMessageDialog(null, "Tipo de uso Dado de alta correctamente", "Notificacion",
									JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"No se pudo crear el Tipo de Uso", 
							"Notificacion", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(234, 221, 89, 23);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {

				
				frmNuevoTipoDe.dispose();
				new PantallaInicio();
			}
		});
			
		
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(28, 221, 89, 23);
		contentPane.add(btnLimpiar);
		
		frmNuevoTipoDe.setSize(381, 329);
		frmNuevoTipoDe.setVisible(true);
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					nombre.setText("");
			}
		});
		
	}
	

	
}
