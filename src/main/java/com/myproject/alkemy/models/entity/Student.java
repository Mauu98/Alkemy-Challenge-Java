package com.myproject.alkemy.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 45)
    private String name;

    @NotEmpty
    @Column(length = 45)
    private String last_name;

    @NotEmpty
    @Size(min = 6, max = 15, message = "Size must be between 6 and 15")
    @Column(unique = true)
    private String dni;

    @NotEmpty
    @Size(min = 3, max = 70, message = "Size must be between 3 and 70")
    @Column(unique = true)
    private String file; //Legajo

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @JsonManagedReference
    private List<Subject> subjects = new ArrayList<>();


    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    /*-------------------------------------------------------------*/
    public Student(Long id, String name, String last_name, String dni, String file, List<Subject> subjects, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.dni = dni;
        this.file = file;
        this.subjects = subjects;
        this.roles = roles;
    }

    public Student() {
        subjects = new ArrayList<>();
    }

    //Método para que el estudiante pueda inscribirse a las materias
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString(){
        return "DNI: "+dni;
    }
}
