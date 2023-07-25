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
//package com.driver;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class StudentRepositry {
//
//    HashMap<String, Student> studentDb= new HashMap<>();
//    HashMap<String, Teacher> teacherDb= new HashMap<>();
//    HashMap<String, List<String>> studentTeacherDb= new HashMap<>();
//
//    public void addStudent(Student student) {
//        studentDb.put(student.getName(), student);
//
//    }
//
//    public void addTeacher(Teacher teacher) {
//        teacherDb.put(teacher.getName(), teacher);
//    }
//
//    public void addStudentTeacherPair(String student, String teacher) {
//    // put mapping is only ofr existing case that's why we are checking in student and teacher db
//        if (studentDb.containsKey(student) && teacherDb.containsKey((teacher))) {
//
//            if (studentTeacherDb.containsKey(teacher)) {
//                List<String> list = studentTeacherDb.get(teacher);
//                list.add(student);
//                studentTeacherDb.put(teacher, list);
//            } else {
//                List<String> al = new ArrayList<>();
//                al.add(student);
//                studentTeacherDb.put(teacher, al);
//            }
//        }
//    }
//
//    public Student getStudentByName(String name) {
//        if(studentDb.containsKey(name)) {
//            return studentDb.get(name);
//        } else {
//            return null;
//        }
//    }
//
//    public Teacher getTeacherByName(String name) {
//        if(teacherDb.containsKey(name)) {
//            return teacherDb.get(name);
//        } else {
//            return null;
//        }
//    }
//
//    public List<String> getStudentsByTeacherName(String teacher) {
//        if(studentTeacherDb.containsKey(teacher)) {
//            return studentTeacherDb.get(teacher);
//        } else {
//            return new ArrayList<>();
//        }
//    }
//
//    public List<String> getAllStudents() {
//        List<String> ans = new ArrayList<>();
//        for(String k: studentDb.keySet()) {
//            ans.add(k);
//        }
//        return ans;
//    }
//
//    public void deleteTeacherByName(String teacher) {
//        teacherDb.remove(teacher);
//
//        List<String> al = studentTeacherDb.remove(teacher);
//        for(int i=0; i<al.size(); i++) {
//            String temp = al.get(i);
//            studentDb.remove(temp);
//        }
//    }
//
//    public void deleteAllTeachers() {
//
//        for(String k: studentTeacherDb.keySet()) {
//            teacherDb.remove(k);
//            List<String> al = studentTeacherDb.remove(k);
//            for(String p: al) {
//                if(studentDb.containsKey(p)) {
//                    studentDb.remove(p);
//                }
//            }
//        }
//    }
//}