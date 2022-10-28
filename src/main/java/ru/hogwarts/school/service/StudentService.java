package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long id = 1L;

    public Student addStudent(Student student){
        student.setId(id);
        students.put(id++, student);
        return student;
    }

    public Student getStudent(long id){
        if(students.containsKey(id)){
            return students.get(id);
        }
        return null;
    }

    public Student setStudent(Student student){
        if(students.containsKey(student.getId())){
            return students.put(student.getId(), student);
        }
        return null;
    }

    public Student deleteStudent(long id){
        return students.remove(id);
    }

    public Collection<Student> getAllByAge(int age){
        return students.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> getAll() {
        return students.values();
    }
}
