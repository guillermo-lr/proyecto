package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Propietario;
import com.exception.ServiciosException;

@Remote
public interface PropietariosBeanRemote {


	void actualizarPropietario(Propietario propietario) throws ServiciosException;

	List<Propietario> obtenerTodos();

	void deshabilitarPropietario(Propietario propietario) throws ServiciosException;

	void altaPropietario(String razon_social, String nombre, String rut, String direccion, String telefono,
			String contacto) throws ServiciosException;

}
