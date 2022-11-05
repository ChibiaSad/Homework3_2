package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);

    @Query(value = "SELECT f.* FROM student AS s, faculty AS f WHERE s.faculty_id = f.id AND s.id = :id", nativeQuery = true)
    Faculty findFacultyByStudentId(@Param("id") long id);
}
