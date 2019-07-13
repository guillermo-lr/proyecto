package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Rol;
import com.exception.ServiciosException;

@Remote
public interface RolBeanRemote {

	void deshabilitarRol(Rol rol) throws ServiciosException;

	List<Rol> obtenerTodos();

	void actualizarRol(Rol rol) throws ServiciosException;

	void altaRol(String nombre) throws ServiciosException;

}
