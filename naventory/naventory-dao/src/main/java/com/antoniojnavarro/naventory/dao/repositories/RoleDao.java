package com.antoniojnavarro.naventory.dao.repositories;

import com.antoniojnavarro.naventory.dao.commons.api.Dao;
import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.filters.RoleSearchFilter;

public interface RoleDao extends Dao<Role, RoleSearchFilter, Integer> {
}
