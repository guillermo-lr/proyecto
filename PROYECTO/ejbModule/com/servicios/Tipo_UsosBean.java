package com.servicios;

import java.time.LocalDate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entities.Potrero;
import com.entities.TipoUso;
import com.entities.TipoUso;
import com.entities.Usuario;
import com.entities.Zona;
import com.entities.ZonaUso;
import com.exception.ServiciosException;


@Stateless
public class Tipo_UsosBean implements Tipo_UsosBeanRemote {

	@PersistenceContext
	private EntityManager em;
   
    public Tipo_UsosBean() {
    }
    
	@Override
	public void altaTipo_Uso(String nombre, TipoUso tipo_uso) throws ServiciosException {

		try{
			TipoUso tipo_uso1 = new TipoUso();
			tipo_uso1.setNombre(nombre);
			tipo_uso1.setTipoUso(tipo_uso1);
			
			em.persist(tipo_uso1);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el tipo de uso");
		}
	}
	
	@Override
	public void actualizarTipo_Uso(TipoUso tipo_uso) throws ServiciosException {
		
		try{
			em.merge(tipo_uso);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el tipo de uso");
		}
	}

	@Override
	public void eliminarTipo_Uso(TipoUso tipo_uso) throws ServiciosException {

		try{
			em.remove(tipo_uso);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el tipo de uso");
		}
	}

	@Override
	public List<TipoUso> obtenerTipo_Uso() {
		TypedQuery<TipoUso> query = em.createQuery("SELECT u FROM TipoUso u",TipoUso.class);
		return query.getResultList();
	}
	@Override
	public TipoUso obtenerTipoUso(Long id) throws ServiciosException {
		
		try{
			return em.find(TipoUso.class, id);
			
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el TipoUso");
		}
	}
	
	
	
		
	}

