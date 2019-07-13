package com.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.Potrero;
import com.entities.Predio;
import com.exception.ServiciosException;
//gg
@Stateless
public class PotrerosBean implements PotrerosBeanRemote {

	@PersistenceContext
	private EntityManager em;

	public PotrerosBean() {
	}

	@Override
	public void altaPotrero(String nombre, Predio predio, String area) throws ServiciosException {
		List<Potrero> lista = predio.getPotreros();
		long totalPotrerosArea = 0;
		for (Potrero p : lista) {
			totalPotrerosArea += p.getArea();
		}
		if (predio.getArea() > totalPotrerosArea + Long.parseLong(area)) {
			try {
				Potrero potrero = new Potrero();
				potrero.setArea(Long.parseLong(area));
				potrero.setNomPotrero(nombre);
				potrero.setDisponibilidad(true);
				potrero.setPredio(predio);
				potrero.setZonas(new ArrayList<>());
				em.persist(potrero);
				em.flush();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo crear el potrero");
			}
		} else {

			// TODO: handle exception
			throw new ServiciosException("Area de potreros mayor a la del predio");

		}
	}

	@Override
	public void actualizarPotrero(Potrero potrero) throws ServiciosException {

		try {
			em.merge(potrero);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el potrero");
		}
	}

	@Override
	public Potrero obtenerPotrero(Long id) throws ServiciosException {

		try {
			return em.find(Potrero.class, id);

		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar el potrero");
		}
	}

	@Override
	public List<Potrero> obtenerTodos() {
		Query query = em.createQuery("SELECT p FROM Potrero p");
		return query.getResultList();
	}

	@Override
	public List<Potrero> obtenerTodosDisponibles() {
		Query query = em.createQuery("SELECT p FROM Potrero p WHERE p.disponibilidad = TRUE");
		return query.getResultList();
	}

}
