package com.antoniojnavarro.naventory.repository.repositories;

import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.filters.RoleSearchFilter;
import com.antoniojnavarro.naventory.repository.commons.api.BaseRepository;

public interface RoleRepository extends BaseRepository<Role, RoleSearchFilter, Integer> {
}
