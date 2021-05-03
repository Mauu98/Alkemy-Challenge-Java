package com.myproject.alkemy.services.implement;

import com.myproject.alkemy.models.dao.IStudentDao;
import com.myproject.alkemy.models.dao.ISubjectDao;
import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.models.entity.Subject;
import com.myproject.alkemy.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplement implements IStudentService {

    @Autowired
    private ISubjectDao subjectDao;

    @Autowired
    private IStudentDao studentDao;


    @Override
    public void addSubject(Long studentId, Long subject_id) {
            studentDao.addSubject(studentId, subject_id);
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.save(student);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectDao.deleteById(id);
    }

    @Override
    public Student findByDni(String dni) {
        return studentDao.findByDni(dni);
    }


}
