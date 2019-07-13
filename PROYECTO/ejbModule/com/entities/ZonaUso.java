package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ZONA_USO database table.
 * 
 */
@Entity
@Table(name="ZONA_USO")
@NamedQuery(name="ZonaUso.findAll", query="SELECT z FROM ZonaUso z")
public class ZonaUso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ZONA_USO_IDZONAUSO_GENERATOR", sequenceName="SEQ_ID_ZONA_USO", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZONA_USO_IDZONAUSO_GENERATOR")
	@Column(name="ID_ZONA_USO")
	private long idZonaUso;

	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

	//bi-directional many-to-one association to TipoUso
	@ManyToOne
	@JoinColumn(name="ID_TIPO_USO")
	private TipoUso tipoUso;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	@JoinColumn(name="ID_ZONA")
	private Zona zona;

	public ZonaUso() {
	}

	public long getIdZonaUso() {
		return this.idZonaUso;
	}

	public void setIdZonaUso(long idZonaUso) {
		this.idZonaUso = idZonaUso;
	}

	public Date getFechaDesde() {
		return this.fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public TipoUso getTipoUso() {
		return this.tipoUso;
	}

	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}