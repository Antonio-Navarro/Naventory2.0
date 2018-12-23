package com.antoniojnavarro.naventory.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Usuario;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;

public interface VentaDao extends Dao<Venta, VentaSearchFilter, Integer> {

	List<Venta> findVentasByUsuarioOrderByFechaDesc(Usuario user);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(f.nombre, count(*)) from Venta v, FormaPago f where v.usuario.email = ?1 and v.formaPago=f.idPago group by v.formaPago")
	List<GraficaGenericDto> findFormasPagoGrafica(String email);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(v.fecha,'%m-%Y') , count(*)) from Venta v where v.usuario.email=?1 group by date_format(v.fecha,'%m-%Y')")
	List<GraficaGenericDto> getVentasMensualesGrafica(String email);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(v.fecha,'%m-%Y') , round(sum(v.total),2)) from Venta v where v.usuario.email= ?1 group by date_format(v.fecha,'%m-%Y') ")
	List<GraficaGenericDto> getIngresosMensualesGrafica(String email);

	Long countByUsuario(Usuario usuario);

}
