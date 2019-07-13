package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Potrero;
import com.entities.TipoUso;
import com.entities.Zona;
import com.exception.ServiciosException;

@Remote
public interface ZonasBeanRemote {


	void actualizarZona(Zona zona) throws ServiciosException;

	void eliminarZona(Zona zona) throws ServiciosException;

	List<Zona> obtenerTodos();

	void altaZona(Zona zona) throws ServiciosException;

	void altaZona(String text, long parseLong, String text2);

	void altaZona(String nombre, Long area, String descripcion, Potrero potrero, TipoUso uso) throws ServiciosException;

	void asignarZona(Potrero potrero, Zona zona) throws ServiciosException;

}
