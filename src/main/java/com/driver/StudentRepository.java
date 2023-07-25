package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String, Student> studentMap = new HashMap<String, Student>();

    Map<String , Teacher> teacherMap = new HashMap<>();

    Map<Teacher, List<String>> studentTeacherMap = new HashMap<>();
    public void addStudent(Student student) {

        if(!studentMap.containsKey(student.getName())){

            studentMap.put(student.getName(), student);
        }

    }

    public void addTeacher(Teacher teacher) {

        if(teacherMap.containsKey(teacher.getName())){
            teacherMap.put(teacher.getName(), teacher);
        }
    }

    public void addStudentTeacherPair(String student, String teacher) {


    }

    public Student getStudentByName(String name) {

        if(studentMap.containsKey(name)){
            return studentMap.get(name);
        }
        return null;

    }

    public Teacher getTeacherByName(String name) {
        if(teacherMap.containsKey(name)){
            return teacherMap.get(name);
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentlist = new ArrayList<>();

        if(studentTeacherMap.containsKey(teacher)){
             return studentTeacherMap.get(teacher);
        }
        return null;
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String currStudent : studentMap.keySet()){
            studentList.add(currStudent);
        }
        return studentList;

    }

    public void deleteTeacherByName(String teacher) {
        for(String currteacher : teacherMap.keySet()){
            if(currteacher.equals(teacher)){
                teacherMap.remove(teacher);
                studentTeacherMap.remove(teacher);
            }
        }
    }

    public void deleteAllTeachers() {
        teacherMap.clear();
        studentTeacherMap.clear();

    }
}
