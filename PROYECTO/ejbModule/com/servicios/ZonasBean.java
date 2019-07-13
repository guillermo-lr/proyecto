package com.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class ZonasBean implements ZonasBeanRemote {

	@PersistenceContext
	private EntityManager em;
 
    public ZonasBean() {
    }
    
    @Override
	public void altaZona(Zona zona) throws ServiciosException {

		try{
			
			em.persist(zona);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la zona");
		}
	}

	@Override
	public void altaZona(String nombre, Long area, String descripcion, Potrero potrero, TipoUso uso) throws ServiciosException {
		
		try{
			long areaZona = 0;
			for (Zona zonas : potrero.getZonas()) {
				areaZona+= zonas.getAreaZona();
			}
			
			
			if(areaZona + area >= potrero.getArea()){
			Zona zona= new Zona();
			zona.setNombreZona(nombre);
			zona.setAreaZona(area);
			zona.setDescZona(descripcion);
			zona.setPotrero(potrero);
			ZonaUso zonaUso = new ZonaUso();
			zonaUso.setZona(zona);
			zonaUso.setTipoUso(uso);
			zonaUso.setFechaDesde(new Date());
			em.persist(zona);
			em.persist(zonaUso);
			em.flush();}
			else{
				
					throw new ServiciosException("Area de las zonas mas grande que la del potrero");

				
			}
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la zona");
		}
	}
	@Override
	public void asignarZona(Potrero potrero, Zona zona) throws ServiciosException {
		
		try{
			long areaZona = 0;
			for (Zona zonas : potrero.getZonas()) {
				areaZona+= zonas.getAreaZona();
			}
			
			
			if(areaZona + zona.getAreaZona() <= potrero.getArea()){
			if(potrero.getZonas()!=null){
				potrero.getZonas().add(zona);
				em.merge(potrero);
			em.flush();}
			else{
				List<Zona> zonasDePotrero = new ArrayList<>();
				zonasDePotrero.add(zona);
				
				potrero.setZonas(zonasDePotrero);
				
				}
			}
			
			
			else{
				
					throw new ServiciosException("Area de las zonas mas grande que la del potrero");

				
			}
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la zona");
		}
	}
	
	
	@Override
	public void actualizarZona(Zona zona) throws ServiciosException {
		
		try{
			em.merge(zona);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar la zona");
		}
	}
	
	@Override
	public void eliminarZona(Zona zona) throws ServiciosException {

		try{
			em.remove(zona);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la zona");
		}
	}
	@Override
	public List<Zona> obtenerTodos() {
		Query query = em.createQuery("SELECT p FROM Zona p");
		return query.getResultList();
	}

	@Override
	public void altaZona(String nombre, long areaZona, String descripcion) {
		// TODO Auto-generated method stub
	
		Zona nuevaZona = new Zona();
		
		nuevaZona.setAreaZona(areaZona);
		nuevaZona.setDescZona(descripcion);
		nuevaZona.setNombreZona(nombre);
		em.persist(nuevaZona);
		em.flush();
	}
	
	
	
	
	
}
