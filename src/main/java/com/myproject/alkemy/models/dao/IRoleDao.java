package com.myproject.alkemy.models.dao;

import com.myproject.alkemy.models.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRoleDao extends CrudRepository<Role, Long> {

    //@Query("SELECT r FROM Role r WHERE r.id = 1")
    //Role findRolUser();
}
