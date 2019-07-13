package com.entities;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the POTRERO database table.
 * 
 */
@Entity
@NamedQuery(name="Potrero.findAll", query="SELECT p FROM Potrero p")
public class Potrero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="POTRERO_IDPOTRERO_GENERATOR", sequenceName="SEQ_ID_POTRERO", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POTRERO_IDPOTRERO_GENERATOR")
	@Column(name="ID_POTRERO")
	private long idPotrero;

	private boolean disponibilidad;

	@Column(name="NOM_POTRERO")
	private String nomPotrero;

	//bi-directional many-to-one association to Predio
	@ManyToOne
	@JoinColumn(name="ID_PREDIO")
	private Predio predio;
	
	@Column(name="AREA")
	private long area;

	public long getArea() {
		return area;
	}

	public void setArea(long area) {
		this.area = area;
	}

	//bi-directional many-to-one association to Zona
	@OneToMany(mappedBy="potrero", fetch=FetchType.EAGER)
	private List<Zona> zonas;

	public Potrero() {
	}

	public long getIdPotrero() {
		return this.idPotrero;
	}

	public void setIdPotrero(long idPotrero) {
		this.idPotrero = idPotrero;
	}

	public boolean getDisponibilidad() {
		return this.disponibilidad;
	}

	

	public String getNomPotrero() {
		return this.nomPotrero;
	}

	public void setNomPotrero(String nomPotrero) {
		this.nomPotrero = nomPotrero;
	}

	public Predio getPredio() {
		return this.predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	public List<Zona> getZonas() {
		return this.zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	public Zona addZona(Zona zona) {
		getZonas().add(zona);
		zona.setPotrero(this);

		return zona;
	}

	public Zona removeZona(Zona zona) {
		getZonas().remove(zona);
		zona.setPotrero(null);

		return zona;
	}

	public void setDisponibilidad(boolean b) {
		// TODO Auto-generated method stub
		this.disponibilidad = b;
	}

	@Override
	public String toString() {
		return nomPotrero;
	}
	
	

}