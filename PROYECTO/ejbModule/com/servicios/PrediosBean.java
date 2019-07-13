package com.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.Predio;
import com.entities.Propietario;
import com.exception.ServiciosException;


@Stateless
public class PrediosBean implements PrediosBeanRemote {

	@PersistenceContext
	private EntityManager em;
  
    public PrediosBean() {
    }

	@Override
	public void altaPredio(String nombre, String descripcion, Propietario propietario, long area) throws ServiciosException {

		try{
			//verifica que el nombre no tenga mas de 45 caracteres
			if(nombre.length()>45){
				throw new ServiciosException("El nombre del predio no puede tener mas de 45 caracteres");
			}
			//verifica que el nombre no este vacio
			if(nombre.length()==0){
				throw new ServiciosException("El nombre no puede estar vacio");
			}
			//verifica que el area no sea menor o igual a cero
			if(area <=0){
				throw new ServiciosException("El area no puede ser menor o igual a cero");
			}
			Predio predio= new Predio();
			predio.setArea(area);
			predio.setNombre(nombre);
			predio.setDescPredio(descripcion);
			predio.setPropietario(propietario);
			predio.setStatus(true);
			em.persist(predio);
			em.flush();
			
		}catch(PersistenceException e){
			
			
			throw new ServiciosException("No se pudo crear el predios \n" + e.getMessage());
		}
	}
	
	
	
	
	@Override
	public void actualizarPredio(Predio predio) throws ServiciosException {
		
		try{
			em.merge(predio);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el predio");
		}
	}
	
	@Override
	public void modificarPredio(Predio predio, long area, String nombre, Propietario propietario) throws ServiciosException {
		
		try{
			//verifica que el nombre no tenga mas de 45 caracteres
			if(nombre.length()>45){
				throw new ServiciosException("El nombre del predio no puede tener mas de 45 caracteres");
			}
			//verifica que el nombre no este vacio
			if(nombre.length()==0){
				throw new ServiciosException("El nombre no puede estar vacio");
			}
			//verifica que el area no sea menor o igual a cero
			if(area <=0){
				throw new ServiciosException("El area no puede ser menor o igual a cero");
			}
			
		predio.setNombre(nombre);
		predio.setArea(area);
		predio.setPropietario(propietario);
			em.merge(predio);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el predio");
		}
	}
	
	@Override
	public void deshabilitarPredio(Predio predio) throws ServiciosException {

		try{
			predio.setStatus(false);
			em.merge(predio);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo deshabilitar el predio");
		}
	}
	
	@Override
	public List<Predio> obtenerTodos() {
		Query query = em.createQuery("SELECT p FROM Predio p");
		return query.getResultList();
	}
	
	@Override
	public List<Predio> obtenerTodosDisponibles() {
		Query query = em.createQuery("SELECT p FROM Predio p WHERE p.status = true");
		return query.getResultList();
	}
	
}
