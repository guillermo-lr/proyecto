package com.interfaz.predio;

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
import com.servicios.PrediosBeanRemote;
import com.entities.Predio;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EliminarPredio {

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
	public EliminarPredio() throws NamingException {
		PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
				InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
		
		JFrame frmNuevoPredio = new JFrame();
		frmNuevoPredio.setResizable(false);
		frmNuevoPredio.setFont(new Font("Dialog", Font.BOLD, 16));
		frmNuevoPredio.setTitle("Eliminar Predio");

		frmNuevoPredio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoPredio.setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmNuevoPredio.setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Predio> comboBox = new JComboBox<Predio>();
		comboBox.setEditable(true);
		
		
		comboBox.setBounds(191, 43, 184, 22);
		contentPane.add(comboBox);
		frmNuevoPredio.setSize(428, 321);
		frmNuevoPredio.setVisible(true);

		List<Predio> listaPredio = PrediosBean.obtenerTodos();

		for (Predio predio : listaPredio) {
			 {
				comboBox.addItem((Predio) predio);
			}
		}
		comboBox.setSelectedItem(null);
		JLabel LblPredio = new JLabel("Seleccionar Predio");
		LblPredio.setBounds(51, 47, 128, 14);
		contentPane.add(LblPredio);

		JButton btnAceptar = new JButton("Eliminar");
		btnAceptar.setBounds(51, 200, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(286, 200, 89, 23);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frmNuevoPredio.dispose();
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
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {	
					PrediosBeanRemote PrediosBean = (PrediosBeanRemote)
						InitialContext.doLookup("PROYECTO/PrediosBean!com.servicios.PrediosBeanRemote");
				
					Predio predio = (Predio) comboBox.getSelectedItem();
					
					if(listaPredio.contains(predio))//comprueba que el predio ecista
					{
					PrediosBean.deshabilitarPredio(predio);
					JOptionPane.showMessageDialog(null, "Predio Eliminado Correctamente", "Notificacion",
							JOptionPane.INFORMATION_MESSAGE);}
					else{

						JOptionPane.showMessageDialog(null, "Predio no existe", "Notificacion",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block

					JOptionPane.showMessageDialog(null, e1.getMessage(), "Notificacion",
							JOptionPane.WARNING_MESSAGE);

					e1.printStackTrace();
				}
			}
		});

	}
}
