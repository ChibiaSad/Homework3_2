package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }


    public StudentRecord addStudent(StudentRecord studentRecord) {
        logger.info("was invoking method addStudent");
        return recordMapper.toRecord(studentRepository.save(recordMapper.toEntity(studentRecord)));
    }

    public StudentRecord getStudent(long id) {
        logger.info("was invoking method getStudent");
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() -> {
            logger.error("There is not student with id = " + id);
            throw new StudentNotFoundException();
        }));
    }

    public StudentRecord setStudent(StudentRecord studentRecord) {
        logger.info("was invoking method setStudent");

        Student oldStudent = studentRepository.findById(studentRecord.getId()).orElseThrow(() -> {
            logger.error("There is not student with id = " + studentRecord.getId());
            throw new StudentNotFoundException();
        });

        oldStudent.setName(studentRecord.getName());
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setFaculty(facultyRepository.findById(studentRecord.getFacultyRecord().getId())
                .orElseThrow(() -> {
                    logger.error("There is not faculty with id = " + studentRecord.getFacultyRecord().getId());
                    throw new FacultyNotFoundException();
                }));
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public void deleteStudent(long id) {
        logger.info("was invoking method deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<StudentRecord> getAllByAge(int age) {
        logger.info("was invoking method getAllByAge");
        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAll() {
        logger.info("was invoking method getAll");
        return studentRepository.findAll().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAllByAgeInRange(int min, int max) {
        logger.info("was invoking method getAllByAgeInRange");
        return studentRepository.findAllByAgeBetween(min, max).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudentId(long id){
        logger.info("was invoking method getFacultyByStudentId");
        return getStudent(id).getFacultyRecord();
    }

    public Integer getStudentCount() {
        logger.info("was invoking method getStudentCount");
        return studentRepository.getStudentsCount();
    }

    public Double getStudentsAverageAge() {
        logger.info("was invoking method getStudentsAverageAge");
        return studentRepository.getStudentsAverageAge();
    }

    public Collection<StudentRecord> getLastFiveStudents() {
        logger.info("was invoking method getLastFiveStudents");
        return studentRepository.findLastFiveStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
}
