package ru.gb.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.lesson7.domain.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
}
