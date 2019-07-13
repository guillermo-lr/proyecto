package com.servicios;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.Propietario;
import com.entities.TipoUso;

/**
 * Session Bean implementation class ReporteBean
 */
@Stateless
@LocalBean
public class ReporteBean implements ReporteBeanRemote {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ReporteBean() {
		// TODO Auto-generated constructor stub
	}


	
//el siguiente metodo obtiene los datos para todos los potreros 
	@Override
	public DefaultTableModel reporteTodosPotreros(Date fecha, Boolean disponibilidad) {
		Query queryP = em.createQuery("SELECT p FROM Potrero p");
	//	queryP.setParameter("disponibilidad", disponibilidad);
		List<Potrero> potreros = queryP.getResultList();
		DefaultTableModel model = new DefaultTableModel();

		TypedQuery<TipoUso> query1 = em.createQuery("SELECT u FROM TipoUso u", TipoUso.class);
//se obtienen los tipos de uso y se agregan a las columnas
		//Tambien se agregan otros campos a las columnas de la tabla 
		List<TipoUso> usos = query1.getResultList();

		model.addColumn("Nombre");
		
		model.addColumn("Predio");
		model.addColumn("Propietario");

		model.addColumn("Area");

		for (TipoUso tipoUso : usos) {
			model.addColumn(tipoUso.getNombre());
		}
		
		//se calculan los campor para cada potrero
		//cada potrero se correspondera con una fila, para generar una fila se debe pasar un Object[] de tipo 
		//Object[] es un array de objetos, que pueden ser de cualquier tipo 
		
		for (Potrero potrero : potreros) {
			Object[] datosPotrero = new Object[usos.size() + 4];
			datosPotrero[0] = potrero.getNomPotrero();
			datosPotrero[1] = potrero.getPredio();
			datosPotrero[2] = potrero.getPredio().getPropietario();
			Query queryS = em.createQuery(
					"SELECT SUM(P.areaZona) FROM Zona P INNER JOIN P.potrero Z WHERE " +
			" P.potrero = :potrero");
			queryS.setParameter("potrero", potrero);
			if (queryS.getResultList().get(0) == null) {
				datosPotrero[3] = 0;
			} else {
				datosPotrero[3] = (long) queryS.getResultList().get(0);
			}
			
			int v = 4; //la variable v se corresponde con el numero de columna, ya hemos pasado las primeras 3 por eso empezamos con la 4 
			for (TipoUso tipoUso : usos) {
				
				//se calcula la suma de todos las zonas con dicho uso y dicho potrero 
				Query queryU = em.createQuery("SELECT SUM(P.areaZona) FROM Zona P INNER JOIN P.zonaUsos Z WHERE "
						+ " (Z.tipoUso = :USO OR Z.tipoUso = :usoPadre) AND P.potrero = :POTRERO AND Z.fechaDesde <= :fecha AND (Z.fechaHasta >= :fecha OR Z.fechaHasta is null)");
				queryU.setParameter("POTRERO", potrero);
				queryU.setParameter("USO", tipoUso);
				queryU.setParameter("usoPadre", tipoUso);

				queryU.setParameter("fecha", fecha);
				Long areaUso = (Long) queryU.getResultList().get(0);
				if (areaUso != null) {
					datosPotrero[v] = areaUso;
					v++;

				} else {
					datosPotrero[v] = 0;// en caso de que el potrero no tenga ninguna zona con dicho uso se pasara el valor 0
					v++;
				}

			}
			model.addRow(datosPotrero);//se agrega la fila a la tabla 
		}
		return model;
	}
	

	
	
	
	//El siguiente metodo obtiene el historico de un predio en un objeto DefaultTableModel el cual la tabal de swing usa para obtener datos de la tabala
	//Obtendra una tabal con las fechas y el area por tipo de uso que tenia el predio en ese momento
	//El metodo va a tener un predio y una variable que indicara que tipo de periodo dia/mes/año quiere los registros
	@Override
	public DefaultTableModel reporteHistoricoPredio(Predio predio, int variable){
		
		//El siguiente objeto da formato a la fecha
		SimpleDateFormat formatedor = new SimpleDateFormat("dd-MM-yyyy");
		//se crea el modelo 
		DefaultTableModel model = new DefaultTableModel();
		//Se obtienen los tipos de uso mediante un query
		TypedQuery<TipoUso> query1 = em.createQuery("SELECT u FROM TipoUso u", TipoUso.class);
		List<TipoUso> usos = query1.getResultList();
		
		//se añaden fecha y area como columnas de la tabla
		model.addColumn("Fecha");
		model.addColumn("Area");
		//el siguiente objeto se usa para poder sumar meses/dias/etc a una fecha en especifico
		Calendar calender = Calendar.getInstance();// esta variable calender guardara la fecha que le dimos
		//se añaden los tipos de uso como columnas
		for (TipoUso tipoUso : usos) {
			model.addColumn(tipoUso);
		}
		//Se obtiene la fecha en que fue asignada un tipo de uso al potrero por primera vez con esta query
		Query queryFechaInicial = em.createQuery("SELECT MIN(Z.fechaDesde) from ZonaUso Z "
				+ "INNER JOIN Z.zona M INNER JOIN M.potrero P WHERE P.predio = :predio");
		queryFechaInicial.setParameter("predio", predio);
		Date fechaInicio = new Date();
		Date fechaFinal = new Date();
		if(queryFechaInicial.getResultList().get(0) != null){
			fechaInicio = (Date) queryFechaInicial.getResultList().get(0);
		}
		//se obtiene el area total del predio y se asigna a una variable
			Query areaPotrero = em.createQuery("SELECT SUM(Z.areaZona) FROM Zona Z INNER JOIN Z.potrero P WHERE "
					+ "P.predio = :predio");
			areaPotrero.setParameter("predio", predio);
			Long areaTotal = (long) 0;
			if(areaPotrero.getResultList().get(0) != null){
				areaTotal = (Long) areaPotrero.getResultList().get(0);
			}
		//se le asigna la fecha inicial al calendario	
			calender.setTime(fechaInicio);
		//sobre la base de la fecha inicial se van a calcular las filas de la tabla
			//Se calcularan todos los periodos hasta el dia de la fecha
			while(fechaInicio.before(fechaFinal)){
				//El DefaultTableModel toma un objet[] que agregara como fila a la tabla
				//Cada mienmbro del array de objects es una columna de la fila
				Object[] datosPredio = new Object[usos.size() + 2];
				datosPredio[0] = formatedor.format(fechaInicio);
				datosPredio[1] = areaTotal;
				int v = 2;
			for (TipoUso uso : usos) {
				//Se calcula el area que ocupa cada uso y sus asociados en el predio con la siguiente query
				Long areaUso;
				Query Query1 = em.createQuery("SELECT SUM(Z.areaZona) FROM Zona Z INNER JOIN Z.zonaUsos N INNER JOIN "
						+ "Z.potrero P WHERE P.predio = :predio AND N.tipoUso = :USO and N.fechaDesde <= :fecha "
						+ "AND (N.fechaHasta >= :fecha OR N.fechaHasta is null)");
				Query1.setParameter("fecha", fechaInicio);
				Query1.setParameter("USO", uso);
				Query1.setParameter("predio", predio);
				
				if(Query1.getResultList().get(0) == null){
					areaUso = (long) 0;
				}
				else{
					areaUso = (Long) Query1.getResultList().get(0);
				}
				//se agrega el dato al array de objects
				datosPredio[v] = areaUso;
				v++;
			}
			 v = 2;
			 //se agrega la fila a la tabla
			model.addRow(datosPredio);
			
			//El siguiente switch permite  a la interfaz elejis que tipo de periodo quiere que tenga cada fila
			switch(variable){
			case 0:
				calender.add(Calendar.DAY_OF_YEAR, 1);
				break;
			case 1:
				calender.add(Calendar.DAY_OF_YEAR, 7);
				break;
			case 2:
				calender.add(Calendar.MONTH, 1);
				break;
			case 3:
				calender.add(Calendar.MONTH, 3);
				break;
			case 4:
				calender.add(Calendar.MONTH, 6);
			case 5:
				calender.add(Calendar.YEAR, 1);
					
			}
		
			fechaInicio = calender.getTime();
			}
			//se retornalos datos de la tabla
		return model;
	}
	
	//el siguiente metodo obtendra los datos para una lista de potreros determinada 
	//se reporta en un objeto de tipo DefaultTableModel porque es lo que se necesita para generar una tabla 
	@Override
	public DefaultTableModel reportePotrerosSeleccionados(Date fecha, List<Potrero> list) {
		DefaultTableModel model = new DefaultTableModel();

		TypedQuery<TipoUso> query1 = em.createQuery("SELECT u FROM TipoUso u", TipoUso.class);
		
		//se añaden las columnas a la tabla

		List<TipoUso> usos = query1.getResultList();

		model.addColumn("Nombre");
		
		model.addColumn("Predio");
		model.addColumn("Propietario");

		model.addColumn("Area en uso");

		for (TipoUso tipoUso : usos) {
			model.addColumn(tipoUso.getNombre());
		}
		
		//se obtienen los datos para cada potrero
		for (Potrero potrero : list) {
			
			//el objeto object guardara cada uno de los datos y se convertira en una fila 
			
			Object[] datosPotrero = new Object[usos.size() + 4]; //usos .size porque cada uso tendra una columna y +4 para las otras columnas 
			datosPotrero[0] = potrero.getNomPotrero();
			datosPotrero[1] = potrero.getPredio();
			datosPotrero[2] = potrero.getPredio().getPropietario();
			Query queryS = em.createQuery(
					"SELECT SUM(P.areaZona) FROM Zona P INNER JOIN P.potrero Z WHERE " +
			" P.potrero = :potrero");
			queryS.setParameter("potrero", potrero);
			if (queryS.getResultList().get(0) == null)
				//no queremos poner valores null por lo que si no tienen ninguna zona con ese uso se enviara cero
			{
				datosPotrero[3] = 0;
			} else {
				datosPotrero[3] = (long) queryS.getResultList().get(0);
			}

			int v = 4;
			
			//se clacula el area de cada tipo de uso de cada potrero, a cada uso se le sumaran los usos hijos tambien 
			for (TipoUso tipoUso : usos) {
				Query queryU = em.createQuery("SELECT SUM(P.areaZona) FROM Zona P INNER JOIN P.zonaUsos Z WHERE "
						+ " (Z.tipoUso = :USO OR Z.tipoUso = :usoPadre) AND P.potrero = :POTRERO AND Z.fechaDesde <= :fecha AND (Z.fechaHasta >= :fecha OR Z.fechaHasta is null)");
				queryU.setParameter("POTRERO", potrero);
				queryU.setParameter("USO", tipoUso);
				queryU.setParameter("usoPadre", tipoUso);

				queryU.setParameter("fecha", fecha);
				Long areaUso = (Long) queryU.getResultList().get(0);
				if (areaUso != null) {
					datosPotrero[v] = areaUso;
					v++;

				} else {
					datosPotrero[v] = 0;
					v++;
				}

			}
			
			//se añaden los datos obtenidos a la tabla 
			model.addRow(datosPotrero);
		}
		return model;
	}
	
	
	}


