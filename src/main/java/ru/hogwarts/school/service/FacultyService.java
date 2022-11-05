package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty setFaculty(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()).isPresent()) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> getAllByColorOrName(String color, String name) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
    public Collection<Student> getStudentByFacultyId(long id){
        return facultyRepository.findAllStudentsByFacultyId(id);
    }

}
