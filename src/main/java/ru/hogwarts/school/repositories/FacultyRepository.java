package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColorIgnoreCase(String color);

    Collection<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color, String name);
}
