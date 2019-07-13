package com.servicios;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.TipoUso;
import com.entities.Zona;
import com.entities.ZonaUso;
import com.exception.ServiciosException;

@Stateless
public class Zona_UsosBean implements Zona_UsosBeanRemote {

	@PersistenceContext
	private EntityManager em;

	public Zona_UsosBean() {
	}
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //este elemento se usa para dar formato a la fecha
	
	@Override
	public void altaZona_Uso(Zona zona, TipoUso tipo_uso, Date fecha_desde)
			throws ServiciosException {

		try {
			ZonaUso zona_uso = new ZonaUso();
			zona_uso.setZona(zona);
			zona_uso.setTipoUso(tipo_uso);
			zona_uso.setFechaDesde(fecha_desde);
			zona_uso.setFechaHasta(null);

			em.persist(zona_uso);

			Query query = em.createQuery("SELECT p FROM ZonaUso p WHERE p.zona LIKE :zona AND p.fechaHasta = null");
			query.setParameter("zona", zona);
			List<ZonaUso> lista = query.getResultList();
			for (ZonaUso zona_Uso2 : lista) {
				if (zona_Uso2 != zona_uso) {
					em.merge(zona_Uso2);
					zona_Uso2.setFechaHasta(fecha_desde);
				}
			}

			em.flush();

		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo crear el tipo de uso con la zona");
		}
	}

	@Override
	public void actualizarZona_Uso(ZonaUso zona_uso) throws ServiciosException {

		try {
			em.merge(zona_uso);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el tipo de uso con la zona");
		}
	}
	@Override
	public List<ZonaUso> obtenerUsosPorZona(Zona zona, LocalDate date) { 
		Query query = em.createQuery("SELECT p FROM ZonaUso p WHERE p.zona LIKE :zona AND p.fecha_desde >= :date AND (p.fecha_hasta <= :date OR p.fecha_hasta IS NULL)");
		query.setParameter("zona", zona);
		query.setParameter("date", date);
		return query.getResultList();
		
	}
	
	@Override
	public List<ZonaUso> obtenerUsosPorFecha(LocalDate date) { 
		Query query = em.createQuery("SELECT p FROM ZonaUso p WHERE p.fecha_desde >= :date AND (p.fecha_hasta <= date OR p.fecha_hasta = null)");
		query.setParameter("date", date);
		return query.getResultList();
	}
	
	@Override
	public List<ZonaUso> obtenerTodas() { 
		Query query = em.createQuery("SELECT p FROM ZonaUso p)");
		return query.getResultList();
		
	}
	
	@Override
	public List<ZonaUso> obtenerUsosPorFechaPotrero(LocalDate date, Potrero potrero) { 
		Query query = em.createQuery("SELECT p FROM ZonaUso p WHERE p.zona LIKE :zona AND p.fecha_desde >= :date AND (p.fecha_hasta <= date OR p.fecha_hasta = null)");
		query.setParameter("date", date);
		query.setParameter("zona", potrero.getZonas());
		return query.getResultList();
	}
	
	
	@Override
	public List<ZonaUso> obtenerUsosPorFechaZona(LocalDate date, Zona zona) { 
		Query query = em.createQuery("SELECT p FROM Zona_Uso p WHERE p.zona LIKE :zona AND p.fecha_desde >= :date AND (p.fecha_hasta <= date OR p.fecha_hasta = null)");
		query.setParameter("date", date);
		query.setParameter("zona", zona);
		return query.getResultList();
	}
	
	@Override
	public List<ZonaUso> obtenerUsosPorUsoPotrero(TipoUso uso, Potrero potrero) { 
		Query query = em.createQuery("SELECT p FROM Zona_Uso p WHERE p.zona LIKE :zona AND p.Tipo_Uso = :uso");
		query.setParameter("uso", uso);
		query.setParameter("zona", potrero.getZonas());
		return query.getResultList();
	}
	
	
	
		
	}
	
	
	

