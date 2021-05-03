package com.myproject.alkemy.services.implement;

import com.myproject.alkemy.models.dao.IProfessorDao;
import com.myproject.alkemy.models.entity.Professor;
import com.myproject.alkemy.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImplement implements IProfessorService {

    @Autowired
    private IProfessorDao professorDao;

    @Override
    public List<Professor> findAll() {
        return (List<Professor>) professorDao.findAll();
    }

    @Override
    public Professor findOne(Long id) {
        return professorDao.findById(id).orElse(null);
    }

    @Override
    public void save(Professor professor) {
        professorDao.save(professor);
    }

    @Override
    public void deleteById(Long id) {
        professorDao.deleteById(id);
    }


}
