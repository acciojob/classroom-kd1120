package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository ;


    public boolean addStudent(Student student){
       if( !studentRepository.addStudent(student)){
           return true;
       }

        return false;
    }

    public boolean addTeacher(Teacher teacher) {

        if(!studentRepository.addTeacher(teacher)){
         return true;
        }
        return false;
    }
}
