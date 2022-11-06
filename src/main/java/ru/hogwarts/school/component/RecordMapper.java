package ru.hogwarts.school.component;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repositories.FacultyRepository;

@Component
public class RecordMapper {
    private final FacultyRepository facultyRepository;

    public RecordMapper(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public StudentRecord toRecord(Student student){
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setId(student.getId());
        studentRecord.setName(student.getName());
        studentRecord.setAge(student.getAge());
        studentRecord.setFaculty(student.getFaculty().getId());
        return studentRecord;
    }

    public FacultyRecord toRecord(Faculty faculty){
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;
    }

    public Student toEntity(StudentRecord studentRecord){
        Student student = new Student();
        student.setName(studentRecord.getName());
        student.setAge(studentRecord.getAge());
        student.setFaculty(facultyRepository.findById(studentRecord.getFaculty()).orElse(null));
        return student;
    }

    public Faculty toEntity(FacultyRecord facultyRecord){
        Faculty faculty = new Faculty();
        faculty.setName(facultyRecord.getName());
        faculty.setColor(facultyRecord.getColor());
        return faculty;
    }
}
