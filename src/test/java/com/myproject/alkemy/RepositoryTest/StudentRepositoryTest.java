package com.myproject.alkemy.RepositoryTest;

import com.myproject.alkemy.models.dao.IStudentDao;
import com.myproject.alkemy.models.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTest {

    @Autowired
    private IStudentDao repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateStudent(){
        Student student = new Student();
        student.setName("Mauricio");
        student.setLast_name("Pino");
        student.setDni("98234234");
        student.setFile("1104236");

        Student saveStudent = repository.save(student);

        Student existStudent = entityManager.find(Student.class, saveStudent.getId()); //Se pasa por argumento la clase Student y el id de dicho estudiante.

        assertThat(existStudent.getDni().equals(student.getDni()));

    }

    @Test
    public void findStudentByDni(){
        String dni = "98234234";
        Student student = repository.findByDni(dni);

        assertThat(student).isNotNull();

    }

}
