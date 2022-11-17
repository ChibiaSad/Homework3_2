package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final RecordMapper recordMapper;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }


    public StudentRecord addStudent(StudentRecord studentRecord) {
        return recordMapper.toRecord(studentRepository.save(recordMapper.toEntity(studentRecord)));
    }

    public StudentRecord getStudent(long id) {
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(StudentNotFoundException::new));
    }

    public StudentRecord setStudent(StudentRecord studentRecord) {
        Student oldStudent = studentRepository.findById(studentRecord.getId()).orElseThrow(StudentNotFoundException::new);
        oldStudent.setName(studentRecord.getName());
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setFaculty(facultyRepository.findById(studentRecord.getFacultyRecord().getId())
                .orElseThrow(FacultyNotFoundException::new));
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<StudentRecord> getAllByAge(int age) {
        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAll() {
        return studentRepository.findAll().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAllByAgeInRange(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudentId(long id){
        return getStudent(id).getFacultyRecord();
    }

    public Integer getStudentCount() {
        return studentRepository.getStudentsCount();
    }

    public Double getStudentsAverageAge() {
        return studentRepository.getStudentsAverageAge();
    }

    public Collection<StudentRecord> getLastFiveStudents() {
        return studentRepository.findLastFiveStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
}
