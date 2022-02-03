package ru.gb;

import org.hibernate.Session;
import ru.gb.domain.Student;
import ru.gb.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class Lesson5App {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        List<Student> students = new ArrayList<>();

        studentService.deleteAll();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student(i,"Student " + i, (float) i));
        }
        studentService.persistAll(students);

        studentService.persist(new Student(1000, "Last student", 0.1f));
        System.out.println(studentService.findById(1000));
        studentService.deleteById(1L);
        List<Student> list = studentService.findAll();
        System.out.println(list);
    }
}
