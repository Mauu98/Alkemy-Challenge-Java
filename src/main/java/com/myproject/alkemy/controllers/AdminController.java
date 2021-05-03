package com.myproject.alkemy.controllers;

import com.myproject.alkemy.models.dao.ISubjectDao;
import com.myproject.alkemy.models.entity.Professor;
import com.myproject.alkemy.models.entity.Subject;
import com.myproject.alkemy.services.IProfessorService;
import com.myproject.alkemy.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private ISubjectDao subjectDao;

    //--------------------
    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String home(){
        return "admin/home";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/subjects")
    public String subjectList(Model model){

        model.addAttribute("title","Subject's List");
        model.addAttribute("subjects",subjectDao.getSubjectAlphabetically());

        return "admin/list_Subjects";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/form-subject")
    public String newSubject(Map<String, Object> model, Model mod){

        mod.addAttribute("professors",professorService.findAll());

        mod.addAttribute("title","Subject Form");
        Subject subject = new Subject();
        model.put("subject", subject);


        return "admin/form_Subject";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form-subject", method = RequestMethod.POST)
    public String saveSubject(@Valid Subject subject, BindingResult result, Model model, RedirectAttributes flashMsg, SessionStatus status){

        model.addAttribute("professors",professorService.findAll());

        if(result.hasErrors()){
            model.addAttribute("title", "Subject Form");
            return "admin/form_Subject";
        }

        String subjectMsg = (subject.getId() != null)? "Subject edited successfully" : "Subject created successfully";
        subjectService.save(subject);
        flashMsg.addFlashAttribute("success", subjectMsg);
        status.setComplete();

        return "redirect:/subjects";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/editSubject/{id}")
    public String editSubject(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, Model mod){

        Subject subject = null;
        if(id>0){
            subject = subjectService.findOne(id);
            if(subject == null){
                flash.addFlashAttribute("error","Subject doesn't exist in DataBase");
                return "redirect:/subjects";
            }
        } else {
            flash.addFlashAttribute("error","The ID cannot be 0");
            return "redirect:/subjects";
        }

        mod.addAttribute("professors",professorService.findAll());

        model.put("subject",subject);
        model.put("title","Subject Form");

        return "admin/form_Subject";

    }

    //-----------------------------------------------------------------------------------------------------
    @Secured("ROLE_ADMIN")
    @GetMapping("/professors")
    public String professorList(Model model){

        model.addAttribute("professors",professorService.findAll());
        model.addAttribute("title","Professor's List");

        return "admin/list_Professors";

    }

    @GetMapping("/form-professor")
    public String addProfessor(Map<String, Object> model,Model mod){

        mod.addAttribute("title","Professor Form");
        Professor professor = new Professor();
        model.put("professor",professor);


        return "admin/form_Professor";
    }

    @RequestMapping(value = "/form-professor", method = RequestMethod.POST)
    public String saveProfessor(@Valid Professor professor, BindingResult result,  Model model, RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            model.addAttribute("title","Professor Form");
            return "admin/form_Professor";
        }

        String professorMsg = (professor.getId() != null)? "Professor edited successfully" : "Professor created successfully";
        professorService.save(professor);
        flash.addFlashAttribute("success", professorMsg);
        status.setComplete();


        return "redirect:/professors";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/editProfessor/{id}")
    public String editProfessor(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, Model mod){

        Professor professor = null;
        if(id>0){
            professor = professorService.findOne(id);
            if(professor == null){
                flash.addFlashAttribute("error","Professor doesn't exist in DataBase");
                return "redirect:/professors";
            }
        } else {
            flash.addFlashAttribute("error","The ID cannot be 0");
            return "redirect:/professors";
        }


        model.put("professor",professor);
        model.put("title","Professor Form");

        return "admin/form_Professor";

    }




}
