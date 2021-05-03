package com.myproject.alkemy.models.dao;

import com.myproject.alkemy.models.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStudentDao extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.dni = ?1")
    Student findByDni(String dni);

    @Query(value = "INSERT INTO student_subjects (student_id, subject_id) VALUES (:studentId, :subjectId)", nativeQuery = true)
    public void addSubject(Long studentId, Long subjectId);
}
