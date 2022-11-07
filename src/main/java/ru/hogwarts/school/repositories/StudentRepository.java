package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);
}
