package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PROPIETARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p")
public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROPIETARIO_IDPROPIETARIO_GENERATOR", sequenceName="SEQ_ID_PROPIETARIO", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROPIETARIO_IDPROPIETARIO_GENERATOR")
	@Column(name="ID_PROPIETARIO")
	private long idPropietario;

	private String contacto;

	private String direccion;

	@Column(name="NOM_PROPIETARIO")
	private String nomPropietario;

	@Column(name="RAZON_SOCIAL")
	private String razonSocial;

	@Column(name="RUT_PROPIETARIO")
	private String rutPropietario;

	private String telefono;

	//bi-directional many-to-one association to Predio
	@OneToMany(mappedBy="propietario")
	private List<Predio> predios1;

	//bi-directional many-to-many association to Predio
	@ManyToMany
	@JoinTable(
		name="PROPIETARIO_PREDIO"
		, joinColumns={
			@JoinColumn(name="ID_PROPIETARIO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PREDIO")
			}
		)
	private List<Predio> predios2;

	public Propietario() {
	}

	public long getIdPropietario() {
		return this.idPropietario;
	}

	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNomPropietario() {
		return this.nomPropietario;
	}

	public void setNomPropietario(String nomPropietario) {
		this.nomPropietario = nomPropietario;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRutPropietario() {
		return this.rutPropietario;
	}

	public void setRutPropietario(String rut) {
		this.rutPropietario = rut;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Predio> getPredios1() {
		return this.predios1;
	}

	public void setPredios1(List<Predio> predios1) {
		this.predios1 = predios1;
	}

	public Predio addPredios1(Predio predios1) {
		getPredios1().add(predios1);
		predios1.setPropietario(this);

		return predios1;
	}

	public Predio removePredios1(Predio predios1) {
		getPredios1().remove(predios1);
		predios1.setPropietario(null);

		return predios1;
	}

	public List<Predio> getPredios2() {
		return this.predios2;
	}

	public void setPredios2(List<Predio> predios2) {
		this.predios2 = predios2;
	}
	@Override
	public String toString() {
		return nomPropietario;
	}

}