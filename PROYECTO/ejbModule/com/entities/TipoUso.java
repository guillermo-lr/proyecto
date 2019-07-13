package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_USO database table.
 * 
 */
@Entity
@Table(name="TIPO_USO")
@NamedQuery(name="TipoUso.findAll", query="SELECT t FROM TipoUso t")
public class TipoUso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_USO_IDTIPOUSO_GENERATOR", sequenceName="SEQ_ID_TIPO_USO", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_USO_IDTIPOUSO_GENERATOR")
	@Column(name="ID_TIPO_USO")
	private long idTipoUso;

	private String nombre;

	//bi-directional many-to-one association to TipoUso
	@ManyToOne
	@JoinColumn(name="PADRE")
	private TipoUso tipoUso;

	//bi-directional many-to-one association to TipoUso
	@OneToMany(mappedBy="tipoUso")
	private List<TipoUso> tipoUsos;

	//bi-directional many-to-one association to ZonaUso
	@OneToMany(mappedBy="tipoUso")
	private List<ZonaUso> zonaUsos;

	public TipoUso() {
	}

	public long getIdTipoUso() {
		return this.idTipoUso;
	}

	public void setIdTipoUso(long idTipoUso) {
		this.idTipoUso = idTipoUso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoUso getTipoUso() {
		return this.tipoUso;
	}

	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}

	public List<TipoUso> getTipoUsos() {
		return this.tipoUsos;
	}

	public void setTipoUsos(List<TipoUso> tipoUsos) {
		this.tipoUsos = tipoUsos;
	}

	public TipoUso addTipoUso(TipoUso tipoUso) {
		getTipoUsos().add(tipoUso);
		tipoUso.setTipoUso(this);

		return tipoUso;
	}

	public TipoUso removeTipoUso(TipoUso tipoUso) {
		getTipoUsos().remove(tipoUso);
		tipoUso.setTipoUso(null);

		return tipoUso;
	}

	public List<ZonaUso> getZonaUsos() {
		return this.zonaUsos;
	}

	public void setZonaUsos(List<ZonaUso> zonaUsos) {
		this.zonaUsos = zonaUsos;
	}

	public ZonaUso addZonaUso(ZonaUso zonaUso) {
		getZonaUsos().add(zonaUso);
		zonaUso.setTipoUso(this);

		return zonaUso;
	}

	public ZonaUso removeZonaUso(ZonaUso zonaUso) {
		getZonaUsos().remove(zonaUso);
		zonaUso.setTipoUso(null);

		return zonaUso;
	}
	@Override
	public String toString() {
		return nombre;
	}

}