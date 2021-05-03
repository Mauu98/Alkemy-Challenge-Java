package com.myproject.alkemy.config;

import com.myproject.alkemy.models.dao.IStudentDao;
import com.myproject.alkemy.models.entity.Role;
import com.myproject.alkemy.models.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomStudentDetailsService implements UserDetailsService {


    @Autowired
    private IStudentDao repository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Student student = repository.findByDni(dni);

        if(student == null){
            throw new UsernameNotFoundException("Student don't found");
        }

        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        for(Role roles : student.getRole()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getAuthority());
            grantList.add(grantedAuthority);
        }

        UserDetails user = (UserDetails) new User(dni, student.getFile(), grantList); //username, password, role/s

        return user;
    }
}
