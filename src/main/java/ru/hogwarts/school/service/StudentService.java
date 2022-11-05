package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student setStudent(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getAllByAgeInRange(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudentId(long id){
        return studentRepository.findFacultyByStudentId(id);
    }
}
