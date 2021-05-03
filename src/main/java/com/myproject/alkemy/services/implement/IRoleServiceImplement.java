package com.myproject.alkemy.services.implement;

import com.myproject.alkemy.models.dao.IRoleDao;
import com.myproject.alkemy.models.entity.Role;
import com.myproject.alkemy.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IRoleServiceImplement implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll() {
        return (List<Role>) roleDao.findAll();
    }
}
