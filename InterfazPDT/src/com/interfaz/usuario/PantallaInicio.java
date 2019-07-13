package com.interfaz.usuario;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.NetworkChannel;

import javax.naming.NamingException;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.interfaz.potrero.AltaPotrero;
import com.interfaz.potrero.ReportePotrero;
import com.interfaz.predio.AltaPredio;
import com.interfaz.predio.EliminarPredio;
import com.interfaz.predio.HistoricoPredio;
import com.interfaz.predio.ModificarPredio;
import com.interfaz.propietario.AltaPropietario;
import com.interfaz.usos.AltaTipoUso;
import com.interfaz.zona.AsignarNuevaZona;
import com.interfaz.zona.AsignarNuevoUso;
import com.interfaz.zona.AsignarZona;
import com.interfaz.zona.EliminarZona;

public class PantallaInicio {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 * 
	 * /** Create the frame.
	 */
	public PantallaInicio() {
		JFrame frmSistemaDeGestion = new JFrame();
		frmSistemaDeGestion.setResizable(false);
		frmSistemaDeGestion.setTitle("Sistema De Gestion de Predios");
		frmSistemaDeGestion.setSize(500, 500);

		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestion.setBounds(100, 100, 600, 400);

		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeGestion.setJMenuBar(menuBar);

		JMenu mnPredio = new JMenu("Predios");
		buttonGroup.add(mnPredio);
		menuBar.add(mnPredio);

		JMenuItem mntmNuevoPredio = new JMenuItem("Nuevo Predio");
		mntmNuevoPredio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AltaPredio();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnPredio.add(mntmNuevoPredio);

		JMenuItem mntmModificarPredio = new JMenuItem("Modificar Predio");
		mntmModificarPredio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ModificarPredio();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnPredio.add(mntmModificarPredio);

		JMenuItem mntmEliminarPredio = new JMenuItem("Eliminar Predio");
		mntmEliminarPredio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new EliminarPredio();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnPredio.add(mntmEliminarPredio);

		JMenuItem mntmReporte = new JMenuItem("Reporte");
		
		
		mnPredio.add(mntmReporte);
		
		JMenuItem mntmHistoricoPredio = new JMenuItem("Historico Predio");
		mntmHistoricoPredio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new HistoricoPredio();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnPredio.add(mntmHistoricoPredio);

		JMenu mnPotrero = new JMenu("Potreros");
		menuBar.add(mnPotrero);

		JMenuItem mntmNuevoPotrero = new JMenuItem("Nuevo Potrero");
		mntmNuevoPotrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AltaPotrero();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		mnPotrero.add(mntmNuevoPotrero);

		JMenuItem mntmModificarPotrero = new JMenuItem("Modificar Potrero");
		
		mnPotrero.add(mntmModificarPotrero);

		JMenuItem mntmAzignarZona = new JMenuItem("Azignar Zona");
		mntmAzignarZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AsignarZona();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnPotrero.add(mntmAzignarZona);

		JMenuItem mntmEliminarPotrero = new JMenuItem("Eliminar Potrero");
		
		mnPotrero.add(mntmEliminarPotrero);

		JMenuItem mntmReporte_1 = new JMenuItem("Historico Potrero");
		mntmReporte_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new ReportePotrero();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		mnPotrero.add(mntmReporte_1);

		JMenu mnPropietarios = new JMenu("Propietarios");
		menuBar.add(mnPropietarios);

		JMenuItem mntmNuevoPropietario = new JMenuItem("Nuevo Propietario");
		mntmNuevoPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AltaPropietario();
					frmSistemaDeGestion.dispose();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mnPropietarios.add(mntmNuevoPropietario);

		JMenuItem mntmModificarPropietario = new JMenuItem("Modificar Propietario");
		
		mnPropietarios.add(mntmModificarPropietario);

		JMenuItem mntmEliminarPropietario = new JMenuItem("Eliminar Propietario");
		
		mnPropietarios.add(mntmEliminarPropietario);

		JMenu mnTipoDeUso = new JMenu("Usos");
		menuBar.add(mnTipoDeUso);

		JMenuItem mntmNuevoUso = new JMenuItem("Nuevo Uso");
		mntmNuevoUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AltaTipoUso();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		mnTipoDeUso.add(mntmNuevoUso);

		JMenuItem mntmModificarUso = new JMenuItem("Modificar Uso");
		
		mnTipoDeUso.add(mntmModificarUso);

		JMenuItem mntmEliminarUso = new JMenuItem("Eliminar Uso");
		
		mnTipoDeUso.add(mntmEliminarUso);

		JMenu mnZonas = new JMenu("Zonas");
		menuBar.add(mnZonas);

		JMenuItem mntmNuevaZona = new JMenuItem("Nueva Zona");
		mntmNuevaZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new AsignarNuevaZona();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnZonas.add(mntmNuevaZona);

		JMenuItem mntmModificarZona = new JMenuItem("Modificar Zona");
		
		mnZonas.add(mntmModificarZona);

		JMenuItem mntmEliminarZona = new JMenuItem("Eliminar Zona");
		mntmEliminarZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new EliminarZona();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		JMenuItem mntmAsignarUso = new JMenuItem("Asignar Uso");
		mntmAsignarUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AsignarNuevoUso();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnZonas.add(mntmAsignarUso);
		
		JMenuItem mntmAsignarZona = new JMenuItem("Asignar Zona");
		mntmAsignarZona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new AsignarZona();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnZonas.add(mntmAsignarZona);
		mnZonas.add(mntmEliminarZona);

		JMenu mnUsuario = new JMenu("Usuarios");
		menuBar.add(mnUsuario);

		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mnUsuario.add(mntmNuevoUsuario);
		mntmNuevoUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new AltaUsuario();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		

		JMenuItem mntmEditarUsuario = new JMenuItem("Editar Usuario");
		mntmEditarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ModificarUsuario();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnUsuario.add(mntmEditarUsuario);

		JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ModificarContraseña();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		
		JMenuItem mntmResetearUsuarios = new JMenuItem("Resetear usuarios");
		mntmResetearUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ResetearUsuario();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnUsuario.add(mntmResetearUsuarios);
		mnUsuario.add(mntmCambiarContrasea);
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar Usuario");
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new EliminarUsuario();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frmSistemaDeGestion.dispose();
			}
		});
		mnUsuario.add(mntmEliminarUsuario);
		frmSistemaDeGestion.getContentPane().setLayout(null);
		frmSistemaDeGestion.setVisible(true);
		//los siguientes if configura que pantallas puede o no el usuario acceder segun su rol 
		if(Loguin.usuario.getRolBean().getNombreRol().equalsIgnoreCase("USUARIO")){
			mnPredio.remove(mntmModificarPredio);
			mnPredio.remove(mntmEliminarPredio);
			mnPredio.remove(mntmModificarPredio);
			mnPredio.remove(mntmModificarPredio);
			mnPotrero.remove(mntmModificarPotrero);
			mnPotrero.remove(mntmEliminarPotrero);
			mnPotrero.remove(mntmNuevoPotrero);
			mnUsuario.remove(mntmEliminarUsuario);
			mnUsuario.remove(mntmNuevoUsuario);
			mnUsuario.remove(mntmEditarUsuario);
			mnPredio.remove(mntmNuevoPredio);
			mnZonas.remove(mntmNuevaZona);
			mnZonas.remove(mntmEliminarZona);
			menuBar.remove(mnPropietarios);
			menuBar.remove(mnTipoDeUso);
			menuBar.remove(mntmEliminarPotrero);
			
			
			
		}
		if(Loguin.usuario.getRolBean().getNombreRol().equalsIgnoreCase("MASTER FILES")){
			mnPredio.remove(mntmModificarPredio);
			mnPredio.remove(mntmEliminarPredio);
			mnPredio.remove(mntmModificarPredio);
			mnPredio.remove(mntmModificarPredio);
			mnUsuario.remove(mntmEliminarUsuario);
			mnUsuario.remove(mntmNuevoUsuario);
			mnUsuario.remove(mntmEditarUsuario);
			mnPredio.remove(mntmNuevoPredio);
			mnZonas.remove(mntmNuevaZona);
			menuBar.remove(mnTipoDeUso);
		}
		
		
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
