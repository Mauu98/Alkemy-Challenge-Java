package com.myproject.alkemy.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern =  "HH:mm:ss")
    private Date schedule;

    @NotEmpty
    @Column(unique = true)
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Integer maximum_quota;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Professor professor;

    public Subject() {
    }

    public Subject(Long id, Date schedule, String name, String description, Integer maximum_quota, List<Student> students) {
        this.id = id;
        this.schedule = schedule;
        this.name = name;
        this.description = description;
        this.maximum_quota = maximum_quota;
        this.students = students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
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

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaximum_quota() {
        return maximum_quota;
    }

    public void setMaximum_quota(Integer maximum_quota) {
        this.maximum_quota = maximum_quota;
    }

    public List<Student> getStudent() {
        return students;
    }

    public void setStudent(List<Student> student) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString(){
        return name;
    }
}
