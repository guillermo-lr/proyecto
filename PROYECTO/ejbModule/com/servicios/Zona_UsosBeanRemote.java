package com.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.entities.Potrero;
import com.entities.TipoUso;
import com.entities.TipoUso;
import com.entities.Zona;
import com.entities.ZonaUso;
import com.entities.ZonaUso;
import com.exception.ServiciosException;

@Remote
public interface Zona_UsosBeanRemote {

	

	void actualizarZona_Uso(ZonaUso zona_uso) throws ServiciosException;

	List<ZonaUso> obtenerUsosPorZona(Zona zona, LocalDate date);

	List<ZonaUso> obtenerUsosPorFecha(LocalDate date);

	List<ZonaUso> obtenerTodas();

	List<ZonaUso> obtenerUsosPorFechaPotrero(LocalDate date, Potrero potrero);

	List<ZonaUso> obtenerUsosPorFechaZona(LocalDate date, Zona zona);

	List<ZonaUso> obtenerUsosPorUsoPotrero(TipoUso uso, Potrero potrero);

	void altaZona_Uso(Zona zona, TipoUso tipo_uso, Date fecha_desde) throws ServiciosException;


}
