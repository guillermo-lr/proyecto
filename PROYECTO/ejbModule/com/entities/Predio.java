package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PREDIO database table.
 * 
 */
@Entity
@NamedQuery(name="Predio.findAll", query="SELECT p FROM Predio p")
public class Predio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PREDIO_IDPREDIO_GENERATOR", sequenceName="SEQ_ID_PREDIO",allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREDIO_IDPREDIO_GENERATOR")
	@Column(name="ID_PREDIO")
	private long idPredio;

	@Column(name="DESC_PREDIO")
	private String descPredio;

	public boolean isStatus() {
		return status;
	}
	
	

	public void setStatus(boolean status) {
		this.status = status;
	}
	private String nombre;
	
	@Column(name="STATUS")
	private boolean status;
	
	@Column(name="AREA")
	private long area;

	public long getArea() {
		return area;
	}



	public void setArea(long area) {
		this.area = area;
	}
	//bi-directional many-to-one association to Potrero
	@OneToMany(mappedBy="predio", fetch = FetchType.EAGER)
	private List<Potrero> potreros;

	//bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name="ID_PROPIETARIO")
	private Propietario propietario;

	//bi-directional many-to-many association to Propietario
	@ManyToMany(mappedBy="predios2")
	private List<Propietario> propietarios;

	public Predio() {
	}

	public long getIdPredio() {
		return this.idPredio;
	}

	public void setIdPredio(long idPredio) {
		this.idPredio = idPredio;
	}

	public String getDescPredio() {
		return this.descPredio;
	}

	public void setDescPredio(String descPredio) {
		this.descPredio = descPredio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Potrero> getPotreros() {
		return this.potreros;
	}

	public void setPotreros(List<Potrero> potreros) {
		this.potreros = potreros;
	}

	public Potrero addPotrero(Potrero potrero) {
		getPotreros().add(potrero);
		potrero.setPredio(this);

		return potrero;
	}

	public Potrero removePotrero(Potrero potrero) {
		getPotreros().remove(potrero);
		potrero.setPredio(null);

		return potrero;
	}

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<Propietario> getPropietarios() {
		return this.propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}
	@Override
	public String toString() {
		return nombre;
	}
}