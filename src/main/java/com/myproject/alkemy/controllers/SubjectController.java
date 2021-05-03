package com.myproject.alkemy.controllers;

import com.myproject.alkemy.models.dao.IStudentDao;
import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.models.entity.Subject;
import com.myproject.alkemy.services.IStudentService;
import com.myproject.alkemy.services.ISubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLDataException;
import java.util.Map;

@Controller
public class SubjectController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStudentDao studentDao;

    @GetMapping(value = "/subject/{id}")
    public String subjectInfo(@PathVariable(value = "id") Long id, RedirectAttributes flashMsg, Map<String, Object> model, Authentication authentication){

        Subject subject = subjectService.findOne(id);

        if(subject == null){
            flashMsg.addFlashAttribute("error","Subject doesn't exist in DataBase");
            return "redirect:/list";
        }

        model.put("subject",subject);
        model.put("title",subject.getName());

        return "subject/info";
    }


}
