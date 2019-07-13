package com.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.swing.table.DefaultTableModel;

import com.entities.Potrero;
import com.entities.Predio;
import com.entities.Propietario;
import com.entities.TipoUso;

@Remote
public interface ReporteBeanRemote {







	DefaultTableModel reporteTodosPotreros(Date fecha, Boolean disponibilidad);


	DefaultTableModel reporteHistoricoPredio(Predio predio, int variable);

	DefaultTableModel reportePotrerosSeleccionados(Date fecha, List<Potrero> list);

}
