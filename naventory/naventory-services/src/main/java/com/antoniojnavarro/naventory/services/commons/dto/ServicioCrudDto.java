package com.antoniojnavarro.naventory.services.commons.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.antoniojnavarro.naventory.dao.commons.dto.paginationresult.PaginationResult;
import com.antoniojnavarro.naventory.dao.commons.enums.SortOrderEnum;
import com.antoniojnavarro.naventory.model.commons.GenericEntity;
import com.antoniojnavarro.naventory.model.commons.filters.SearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

public interface ServicioCrudDto<DTO extends GenericDto, TEntity extends GenericEntity, F extends SearchFilter, ID extends Serializable>
		extends Serializable {

	DTO findById(ID id) throws ServicioException;

	List<DTO> findBySearchFilter(F searchFilter) throws ServicioException;

	List<DTO> findBySearchFilter(F searchFilter, String sortField, SortOrderEnum sortOrder) throws ServicioException;

	PaginationResult<DTO> findBySearchFilterPagination(F searchFilter, int pageNumber, int pageSize)
			throws ServicioException;

	// Metodo con paginacion y ordenacion personalizada, fuera de las indicadas
	// en la anotacion @OrderByColumn
	PaginationResult<DTO> findBySearchFilterPagination(F searchFilter, int pageNumber, int pageSize, String sortField,
			SortOrderEnum sortOrder) throws ServicioException;

	List<DTO> findAll() throws ServicioException;

	boolean exists(DTO dto) throws ServicioException;

	boolean existsById(ID id) throws ServicioException;

	default DTO saveOrUpdate(DTO entity, boolean validate) throws ServicioException {
		if (validate) {
			validate(entity);
		}
		return save(entity);
	}

	default DTO saveOrUpdate(DTO entity) throws ServicioException {
		return this.saveOrUpdate(entity, true);
	}

	DTO save(DTO entity) throws ServicioException;

	void validate(DTO dto) throws ServicioException;

	void delete(DTO dto) throws ServicioException;

	void deleteRange(List<DTO> dtos) throws ServicioException;

	void deleteById(ID id) throws ServicioException;

	default DTO entityToDto(TEntity entity) throws ServicioException {
		if (entity == null) {
			return null;
		}
		return this.parseEntityToDto(entity);
	}

	default List<DTO> entitiesToDtos(List<TEntity> entities) throws ServicioException {
		return entities == null ? new ArrayList<DTO>()
				: entities.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	default TEntity dtoToEntity(DTO dto) throws ServicioException {
		if (dto == null) {
			return null;
		}
		return this.parseDtoToEntity(dto);
	}

	default List<TEntity> dtosToEntities(List<DTO> dtos) throws ServicioException {
		return dtos == null ? new ArrayList<TEntity>()
				: dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

	default PaginationResult<DTO> paginationResultEntitiesToDtos(PaginationResult<TEntity> paginationResultEntities)
			throws ServicioException {
		PaginationResult<DTO> paginationResultDto = new PaginationResult<DTO>();
		return paginationResultDto.pageNumber(paginationResultEntities.getPageNumber())
				.pageSize(paginationResultEntities.getPageSize()).totalResult(paginationResultEntities.getTotalResult())
				.offset(paginationResultEntities.getOffset())
				.result(this.entitiesToDtos(paginationResultEntities.getResult()));

	}

	// Parsers de entitades a DTO y viceversa (Método plantilla)
	DTO parseEntityToDto(TEntity entity) throws ServicioException;

	TEntity parseDtoToEntity(DTO dto) throws ServicioException;
}