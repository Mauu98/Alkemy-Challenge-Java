package com.myproject.alkemy.models.dao;

import com.myproject.alkemy.models.entity.Professor;
import org.springframework.data.repository.CrudRepository;

public interface IProfessorDao extends CrudRepository<Professor, Long> {
}
