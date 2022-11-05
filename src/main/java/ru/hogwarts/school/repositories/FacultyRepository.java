package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

    @Query(value = "SELECT s.* FROM student as s, faculty as f WHERE s.faculty_id = f.id and f.id = :id", nativeQuery = true)
    Collection<Student> findAllStudentsByFacultyId(@Param("id") long id);
}
