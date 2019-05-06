package com.antoniojnavarro.naventory.services.api;

import com.antoniojnavarro.naventory.model.entities.Role;
import com.antoniojnavarro.naventory.model.filters.RoleSearchFilter;
import com.antoniojnavarro.naventory.services.commons.ServicioCrud;

public interface ServicioRole extends ServicioCrud<Role, RoleSearchFilter, Integer> {
}
