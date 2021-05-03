package com.myproject.alkemy.services;

import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.models.entity.Subject;
import org.springframework.data.jpa.repository.Query;

public interface IStudentService {

    public void addSubject(Long studentId, Long subject_id);

    public void saveStudent(Student student);

    public void deleteSubject(Long id);

    Student findByDni(String dni);
}
