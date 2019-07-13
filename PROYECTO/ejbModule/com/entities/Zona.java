package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ZONA database table.
 * 
 */
@Entity
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZONA_IDZONA_GENERATOR", sequenceName="SEQ_ID_ZONA", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZONA_IDZONA_GENERATOR")
	@Column(name="ID_ZONA")
	private long idZona;

	@Column(name="AREA_ZONA")
	private Long areaZona;

	@Column(name="DESC_ZONA")
	private String descZona;

	@Column(name="NOMBRE_ZONA")
	private String nombreZona;

	//bi-directional many-to-one association to Potrero
	@ManyToOne
	@JoinColumn(name="ID_POTRERO")
	private Potrero potrero;

	//bi-directional many-to-one association to ZonaUso
	@OneToMany(mappedBy="zona")
	private List<ZonaUso> zonaUsos;

	public Zona() {
	}

	public long getIdZona() {
		return this.idZona;
	}

	public void setIdZona(long idZona) {
		this.idZona = idZona;
	}

	public Long getAreaZona() {
		return this.areaZona;
	}

	public void setAreaZona(Long areaZona) {
		this.areaZona = areaZona;
	}

	public String getDescZona() {
		return this.descZona;
	}

	public void setDescZona(String descZona) {
		this.descZona = descZona;
	}

	public String getNombreZona() {
		return this.nombreZona;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}

	public Potrero getPotrero() {
		return this.potrero;
	}

	public void setPotrero(Potrero potrero) {
		this.potrero = potrero;
	}

	public List<ZonaUso> getZonaUsos() {
		return this.zonaUsos;
	}

	public void setZonaUsos(List<ZonaUso> zonaUsos) {
		this.zonaUsos = zonaUsos;
	}

	public ZonaUso addZonaUso(ZonaUso zonaUso) {
		getZonaUsos().add(zonaUso);
		zonaUso.setZona(this);

		return zonaUso;
	}

	public ZonaUso removeZonaUso(ZonaUso zonaUso) {
		getZonaUsos().remove(zonaUso);
		zonaUso.setZona(null);

		return zonaUso;
	}
	@Override
	public String toString() {
		return nombreZona;
	}

}