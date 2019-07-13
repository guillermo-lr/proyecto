package com.servicios;

import java.util.List;


import javax.ejb.Remote;

import com.entities.TipoUso;
import com.exception.ServiciosException;

@Remote
public interface Tipo_UsosBeanRemote {

	void altaTipo_Uso(String nombre, TipoUso tipo_uso) throws ServiciosException;

	void actualizarTipo_Uso(TipoUso tipo_uso) throws ServiciosException;

	void eliminarTipo_Uso(TipoUso tipo_uso) throws ServiciosException;

	List<TipoUso> obtenerTipo_Uso();

	TipoUso obtenerTipoUso(Long id) throws ServiciosException;

}
