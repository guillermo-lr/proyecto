package com.servicios;

import java.util.List;

import java.util.Map;

import javax.ejb.Remote;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.TipoUso;
import com.entities.Zona;
import com.exception.ServiciosException;
@Remote
public interface PotrerosBeanRemote {

	void actualizarPotrero(Potrero potrero) throws ServiciosException;


	List<Potrero> obtenerTodos();

	List<Potrero> obtenerTodosDisponibles();

	Potrero obtenerPotrero(Long id) throws ServiciosException;

	void altaPotrero(String nombre, Predio predio, String area) throws ServiciosException;









}
