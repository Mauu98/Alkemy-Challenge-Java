package com.myproject.alkemy.services;

import com.myproject.alkemy.models.entity.Professor;

import java.util.List;

public interface IProfessorService {

    public List<Professor> findAll();

    public Professor findOne(Long id);

    public void save(Professor professor);

    public void deleteById(Long id);
}
