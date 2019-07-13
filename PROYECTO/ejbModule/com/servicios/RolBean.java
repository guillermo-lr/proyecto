package com.servicios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.Rol;
import com.entities.Propietario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class RolBean
 */
@Stateless
@LocalBean
public class RolBean implements RolBeanRemote {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public RolBean() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void altaRol(String nombre) throws ServiciosException {

		try{
			Rol rol= new Rol();
			rol.setNombreRol(nombre);
			em.persist(rol);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el rol");
		}
	}
	
	
	
	
	@Override
	public void actualizarRol(Rol rol) throws ServiciosException {
		
		try{
			em.merge(rol);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el rol");
		}
	}
	
	@Override
	public void deshabilitarRol(Rol rol) throws ServiciosException {

		try{
			em.remove(rol);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo deshabilitar el rol");
		}
	}
	
	@Override
	public List<Rol> obtenerTodos() {
		Query query = em.createQuery("SELECT p FROM Rol p");
		return query.getResultList();
	}
	

}
