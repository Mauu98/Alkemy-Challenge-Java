package com.myproject.alkemy.models.dao;

import com.myproject.alkemy.models.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ISubjectDao extends CrudRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s")
    public List<Subject> getSubjects();

    @Query("SELECT s FROM Subject s ORDER BY s.name")
    public List<Subject> getSubjectAlphabetically();

}
