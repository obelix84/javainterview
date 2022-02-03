package ru.gb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.gb.domain.Student;

import java.util.List;

public class StudentDao implements AbstractDaoInterface<Student, Long> {

    private Session currentSession;
    private Transaction currentTransaction;

    public StudentDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    @Override
    public void persist(Student entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(Student entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Student findById(Long id) {
        Student stud = (Student) getCurrentSession().get(Student.class, id);
        return stud;
    }

    @Override
    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = (List<Student>) getCurrentSession().createQuery("from Student ").list();
        return students;
    }

    @Override
    public void deleteAll() {
        List<Student> entityList = findAll();
        for (Student entity : entityList) {
            this.delete(entity);
        }
    }
}
