package com.myproject.alkemy.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String last_name;

    @NotEmpty
    @Size(min = 6, max = 15, message = "Size must be between 6 and 15")
    @Column(unique = true)
    private String dni;

    @NotBlank(message = "The checkbox STATUS cannot be unchecked")
    private Integer active;

    @OneToMany
    private List<Subject> subjects;

    public Professor() {
    }

    public Professor(Long id, String name, String last_name, String dni, Integer active) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.dni = dni;
        this.active = 1;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString(){
        return name + last_name;
    }
}
