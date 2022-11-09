package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.entity.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
