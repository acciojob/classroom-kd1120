package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String, Student> studentMap = new HashMap<String, Student>();

    Map<String , Teacher> teacherMap = new HashMap<>();

    Map<Student, Teacher> studentTeacherMap = new HashMap<>();
    public boolean addStudent(Student student) {

        if(studentMap.containsKey(student.getName())){
            return false;
        }

        studentMap.put(student.getName(), student);
        return true;
    }

    public boolean addTeacher(Teacher teacher) {

        if(teacherMap.containsKey(teacher.getName())){
            return false;
        }

        teacherMap.put(teacher.getName(), teacher);
        return true;

    }
}
