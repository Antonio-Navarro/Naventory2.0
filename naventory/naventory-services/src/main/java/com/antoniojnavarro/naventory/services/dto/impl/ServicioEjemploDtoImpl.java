package com.antoniojnavarro.naventory.services.dto.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.model.entities.Ejemplo;
import com.antoniojnavarro.naventory.model.filters.EjemploSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioEjemplo;
import com.antoniojnavarro.naventory.services.commons.ServicioException;
import com.antoniojnavarro.naventory.services.commons.ServicioValidacion;
import com.antoniojnavarro.naventory.services.dto.EjemploDto;
import com.antoniojnavarro.naventory.services.dto.api.ServicioEjemploDto;

@Service
public class ServicioEjemploDtoImpl implements ServicioEjemploDto {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ServicioEjemplo srvEjemplo;

	@Autowired
	private ServicioValidacion srvValidacion;

	@Override
	public EjemploDto findById(Long id) throws ServicioException {
		return this.entityToDto(srvEjemplo.findById(id));
	}

	@Override
	public List<EjemploDto> findBySearchFilter(EjemploSearchFilter searchFilter) throws ServicioException {
		return this.entitiesToDtos(srvEjemplo.findBySearchFilter(searchFilter));
	}

	@Override
	public List<EjemploDto> findBySearchFilter(EjemploSearchFilter searchFilter, String sortField,
			SortOrderEnum sortOrder) throws ServicioException {
		return this.entitiesToDtos(srvEjemplo.findBySearchFilter(searchFilter, sortField, sortOrder));
	}

	@Override
	public PaginationResult<EjemploDto> findBySearchFilterPagination(EjemploSearchFilter searchFilter, int pageNumber,
			int pageSize, String sortField, SortOrderEnum sortOrder) throws ServicioException {
		return this.paginationResultEntitiesToDtos(
				srvEjemplo.findBySearchFilterPagination(searchFilter, pageNumber, pageSize, sortField, sortOrder));
	}

	@Override
	public PaginationResult<EjemploDto> findBySearchFilterPagination(EjemploSearchFilter searchFilter, int pageNumber,
			int pageSize) throws ServicioException {
		return this.paginationResultEntitiesToDtos(
				srvEjemplo.findBySearchFilterPagination(searchFilter, pageNumber, pageSize));
	}

	@Override
	public List<EjemploDto> findAll() throws ServicioException {
		return this.entitiesToDtos(srvEjemplo.findAll());
	}

	@Override
	public boolean exists(EjemploDto dto) throws ServicioException {
		if (dto == null || dto.getId() == null || dto.getId() <= 0) {
			return false;
		}
		return existsById(dto.getId());
	}

	@Override
	public boolean existsById(Long id) throws ServicioException {
		return srvEjemplo.existsById(id);
	}

	@Override
	public EjemploDto save(EjemploDto dto) throws ServicioException {
		return entityToDto(srvEjemplo.saveOrUpdate(dtoToEntity(dto), false));
	}

	@Override
	public void validate(EjemploDto dto) throws ServicioException {
		this.srvValidacion.isNull("Nombre", dto.getNombre());
		this.srvValidacion.isNull("F. Alta", dto.getFechaAlta());

	}

	@Override
	public void delete(EjemploDto dto) throws ServicioException {
		this.srvEjemplo.delete(dtoToEntity(dto));
	}

	@Override
	public void deleteRange(List<EjemploDto> dtos) throws ServicioException {
		this.srvEjemplo.deleteRange(dtosToEntities(dtos));

	}

	@Override
	public void deleteById(Long id) throws ServicioException {
		this.srvEjemplo.deleteById(id);
	}

	@Override
	public EjemploDto parseEntityToDto(Ejemplo entity) throws ServicioException {
		// Hacemos uso de la libreria ModelMapper para relizar el mapeo
		// inteligente
		return this.modelMapper.map(entity, EjemploDto.class);
	}

	@Override
	public Ejemplo parseDtoToEntity(EjemploDto dto) throws ServicioException {
		return this.modelMapper.map(dto, Ejemplo.class);
	}
}
