package com.myproject.alkemy.services.implement;

import com.myproject.alkemy.models.dao.ISubjectDao;
import com.myproject.alkemy.models.entity.Subject;
import com.myproject.alkemy.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImplement implements ISubjectService {

    @Autowired
    private ISubjectDao subjectDao;

    @Override
    public List<Subject> findAll() {
       return (List<Subject>) subjectDao.getSubjects();
    }

    @Override
    public Subject findOne(Long id) {
        return subjectDao.findById(id).orElse(null);
    }

    @Override
    public void save(Subject subject) {
        subjectDao.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        subjectDao.deleteById(id);
    }

}
