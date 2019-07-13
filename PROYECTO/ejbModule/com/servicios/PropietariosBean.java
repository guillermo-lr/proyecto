package com.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.Propietario;
import com.exception.ServiciosException;


@Stateless
public class PropietariosBean implements PropietariosBeanRemote {

	@PersistenceContext
	private EntityManager em;
  
    public PropietariosBean() {
    }
    
	@Override
	public void altaPropietario(String razon_social, String nombre, String rut, String direccion, String telefono, String contacto) throws ServiciosException {

		try{
			Propietario propietario = new Propietario();
			propietario.setRazonSocial(razon_social);
			propietario.setNomPropietario(nombre);
			propietario.setRutPropietario(rut);
			propietario.setDireccion(direccion);
			propietario.setTelefono(telefono);
			propietario.setContacto(contacto);
			em.persist(propietario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el propietario");
		}
	}

	@Override
	public void actualizarPropietario(Propietario propietario) throws ServiciosException {
		
		try{
			em.merge(propietario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el propietario");
		}
	}
	@Override
	public List<Propietario> obtenerTodos() {
		Query query = em.createQuery("SELECT p FROM Propietario p");
		return query.getResultList();
	}
	@Override
	public void deshabilitarPropietario(Propietario propietario) throws ServiciosException {

		try{
			em.remove(propietario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo deshabilitar el predio");
		}
	}
}
