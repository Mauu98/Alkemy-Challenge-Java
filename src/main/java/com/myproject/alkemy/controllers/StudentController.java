package com.myproject.alkemy.controllers;

import com.myproject.alkemy.config.CustomStudentDetails;
import com.myproject.alkemy.models.dao.IRoleDao;
import com.myproject.alkemy.models.dao.IStudentDao;
import com.myproject.alkemy.models.dao.ISubjectDao;
import com.myproject.alkemy.models.entity.Role;
import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.models.entity.Subject;
import com.myproject.alkemy.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ISubjectDao subjectDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private ISubject_StudentService student_subjectService;

    /*--------------------------------------*/

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Authentication auth, HttpServletRequest request, HttpSession session){

        String studentDni = auth.getName();

        if(auth != null){
            log.info("User: "+studentDni+". Role: "+auth.getAuthorities()+" - Test: "+auth.isAuthenticated()+" -  Cred: "+auth.getCredentials());
        }

        if(session.getAttribute("student") == null) {
            Student student = studentService.findByDni(studentDni);
            log.info("User Info: "+student);
            session.setAttribute("student", student);
            System.out.println(student_subjectService.findSubjectsByStudent(student.getId()));
        }

        model.addAttribute("title","Subject's List");
        model.addAttribute("subjects",subjectDao.getSubjectAlphabetically());


        return "subject/subjects";
    }

    @RequestMapping("/register")
    public String newStudent(Map<String, Object> model, Model mod){

        mod.addAttribute("roles", roleDao.findAll());
        model.put("title","Form Register");
        Student student = new Student();
        model.put("student",student);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST) //Procesa el formulario
    public String save(@Valid Student student, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){

        model.addAttribute("roles", roleDao.findAll());

        if (result.hasErrors()) {
            model.addAttribute("title", "Form Register");
            return "register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedFile = passwordEncoder.encode(student.getFile());
        student.setFile(encodedFile);
        String msjFlash = "Student "+student.getName().concat(" ").concat(student.getLast_name())+" registered with DNI"+student.getDni();

        studentService.saveStudent(student);
        status.setComplete();
        flash.addFlashAttribute("success",msjFlash);

        return "redirect:/login";
    }

    @RequestMapping(value = {"/subject/registry/{id}"}, method = RequestMethod.GET)
    public String addSubject(@PathVariable(value = "id") Long id,Authentication authentication, RedirectAttributes flashMsg){
        String studentDni = authentication.getName();
        Student student = studentService.findByDni(studentDni);

        Long id_student = student.getId();
        Subject subject = subjectService.findOne(id);
        Long id_subject = subject.getId();


        student.getSubjects().add(subject);
        studentService.saveStudent(student);
        log.info("Inscription to subject || Student ID: "+student.getId()+" - Subject ID: "+subject.getId());
        flashMsg.addFlashAttribute("inscription","You successfully signed up to "+subject.getName());

        return "redirect:/list";

    }

    //--------------------------

    @GetMapping("/subjects-registered")
    public String testSubject(Authentication authentication, Model model){

        String studentDni = authentication.getName();
        Student student = studentService.findByDni(studentDni);

        model.addAttribute("title","Subjects Registered");
        model.addAttribute("subjects_registered", student_subjectService.findSubjectsByStudent(student.getId()));

        System.out.println(student_subjectService.findSubjectsByStudent(student.getId()));

        return "registered-subjects";
    }

}
