package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Predio;
import com.entities.Propietario;
import com.exception.ServiciosException;

@Remote
public interface PrediosBeanRemote {

	void actualizarPredio(Predio predio) throws ServiciosException;

	
	List<Predio> obtenerTodos();

	

	void deshabilitarPredio(Predio predio) throws ServiciosException;


	List<Predio> obtenerTodosDisponibles();


	void altaPredio(String nombre, String descripcion, Propietario propietario, long area) throws ServiciosException;




	void modificarPredio(Predio predio, long area, String nombre, Propietario propietario) throws ServiciosException;
;

}
