package com.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.entities.Rol;
import com.enumerados.Tipo_Documento;
import com.exception.ServiciosException;

@Remote
public interface UsuariosBeanRemote {

	void actualizarUsuario(Usuario usuario) throws ServiciosException;

	void deshabilitarUsuario(Usuario usuario) throws ServiciosException;

	List<Usuario> obtenerTodos();

	void altaUsuario(Usuario usuario);

	


	boolean ValidarUsuario(String mail, String password) throws ServiciosException;

	Usuario ObtenerUsuario(String mail, String password);

	void resetearUsuario(Usuario usuario) throws ServiciosException;

	void altaUsuario(String nombre, String apellido, String tipo_documento, String documento, String mail,
			Date fecha_alta, Boolean status, Rol rol) throws ServiciosException;

	

	void modificarUsuario(String text, String text2, String string, String text3, String text4, Date fechaLocal,
			boolean b, Rol selectedItem, Usuario selectedItem2) throws ServiciosException;



	void modificarContraseña(Usuario usuario, String password) throws ServiciosException;


	

}
