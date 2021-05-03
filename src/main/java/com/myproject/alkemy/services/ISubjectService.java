package com.myproject.alkemy.services;

import com.myproject.alkemy.models.entity.Subject;

import java.util.List;

public interface ISubjectService {

    public List<Subject> findAll();

    public Subject findOne(Long id);

    public void save(Subject subject);

    public void deleteById(Long id);
}
