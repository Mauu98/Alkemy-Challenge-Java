package com.myproject.alkemy.services;

import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.models.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISubject_StudentService extends JpaRepository<Student, Long> {

    @Query("SELECT DISTINCT sj.subjects from Student sj WHERE sj.id = ?1")
    List<Subject> findSubjectsByStudent(Long id);
}
