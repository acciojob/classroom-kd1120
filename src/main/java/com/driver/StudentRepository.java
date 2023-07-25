package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    List<Student> studentMap = new ArrayList<>();

    List<Teacher> teacherMap = new ArrayList<>();

    Map<Teacher, List<Student>> teacherStudentsMap = new HashMap<>();
    public void addStudent(Student student) {
        studentMap.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.add(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        Student student1=null;
        for (Student s:studentMap) {
            if(s.getName().equals(student)){
                student1=s;
                break;
            }
        }

        Teacher teacher1=null;
        for (Teacher t:teacherMap) {
            if (t.getName().equals(teacher)){
                teacher1=t;
                break;
            }
        }

        if(student1==null || teacher1==null) return;
        List<Student> studentList=teacherStudentsMap.getOrDefault(teacher1,new ArrayList<>());
        studentList.add(student1);
        teacher1.setNumberOfStudents(1+teacher1.getNumberOfStudents());
        teacherStudentsMap.put(teacher1,studentList);
    }

    public Student getStudentByName(String name) {
        for (Student s:studentMap) {
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    public Teacher getTeacherByName(String name) {
        for (Teacher t:teacherMap) {
            if (t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList = new ArrayList<>();
        for (Teacher t:teacherStudentsMap.keySet()) {
            if(t.getName().equals(teacher)){
                for (Student s:teacherStudentsMap.get(t)) {
                    studentList.add(s.getName());
                }
            }
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for (Student s:studentMap) {
            studentList.add(s.getName());
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        Teacher teacher1=null;
        for (Teacher t:teacherMap) {
            if (t.getName().equals(teacher)){
                teacher1=t;
                break;
            }
        }
        if (teacher1==null) return;
        List<Student> studentList = teacherStudentsMap.getOrDefault(teacher1,new ArrayList<>());
        teacherMap.remove(teacher1);
        teacherStudentsMap.remove(teacher1);
        studentMap.removeAll(studentList);
    }

    public void deleteAllTeachers() {
        for (Teacher teacher:teacherMap) {
            if(teacherStudentsMap.get(teacher)!=null) studentMap.removeAll(teacherStudentsMap.get(teacher));
        }
        teacherMap.clear();
        teacherStudentsMap.clear();
    }
}