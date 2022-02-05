package ru.gb.service;

import ru.gb.dao.StudentDao;
import ru.gb.domain.Student;

import java.util.List;

public class StudentService {

    private StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDao();
    }

    public void persist(Student student) {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.persist(student);
        studentDao.closeCurrentSessionWithTransaction();
    }

    public void persistAll(List<Student> list) {
        studentDao.openCurrentSessionWithTransaction();
        for (Student student : list) {
            studentDao.persist(student);
        }
        studentDao.closeCurrentSessionWithTransaction();
    }

    public void update(Student student) {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.update(student);
        studentDao.closeCurrentSessionWithTransaction();
    }

    public Student findById(long id) {
        studentDao.openCurrentSession();
        Student stud = studentDao.findById(id);
        studentDao.closeCurrentSession();
        return stud;
    }

    public void deleteById(long id) {
        studentDao.openCurrentSessionWithTransaction();
        Student student = studentDao.findById(id);
        if(student != null) {
            studentDao.delete(student);
        }
        studentDao.closeCurrentSessionWithTransaction();
    }

    public List<Student> findAll() {
        studentDao.openCurrentSession();
        List<Student> list = studentDao.findAll();
        studentDao.closeCurrentSession();
        return list;
    }

    public void deleteAll() {
        studentDao.openCurrentSessionWithTransaction();
        studentDao.deleteAll();
        studentDao.closeCurrentSessionWithTransaction();
    }

}
