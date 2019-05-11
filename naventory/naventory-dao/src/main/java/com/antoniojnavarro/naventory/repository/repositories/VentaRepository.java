package com.antoniojnavarro.naventory.repository.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto;
import com.antoniojnavarro.naventory.model.entities.Empresa;
import com.antoniojnavarro.naventory.model.entities.Venta;
import com.antoniojnavarro.naventory.model.filters.VentaSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface VentaRepository extends BaseRepository<Venta, VentaSearchFilter, Integer> {

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(f.nombre, count(*)) from Venta v, FormaPago f where v.empresa = ?1 and v.formaPago=f.idPago group by v.formaPago")
	List<GraficaGenericDto> findFormasPagoGrafica(Empresa empresa);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(v.fecha,'%m-%Y') , count(*)) from Venta v where v.empresa=?1 group by date_format(v.fecha,'%m-%Y')")
	List<GraficaGenericDto> getVentasMensualesGrafica(Empresa empresa);

	@Query("select new com.antoniojnavarro.naventory.model.dtos.GraficaGenericDto(date_format(v.fecha,'%m-%Y') , round(sum(v.total),2)) from Venta v where v.empresa= ?1 group by date_format(v.fecha,'%m-%Y') order by date_format(v.fecha,'%m-%Y') desc ")
	List<GraficaGenericDto> getIngresosMensualesGrafica(Empresa empresa, Pageable p);

	Long countByEmpresa(Empresa empesa);

}
