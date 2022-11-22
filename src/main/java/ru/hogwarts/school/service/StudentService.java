package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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
import java.util.List;
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
        logger.debug("was invoking method addStudent");
        return recordMapper.toRecord(studentRepository.save(recordMapper.toEntity(studentRecord)));
    }

    public StudentRecord getStudent(long id) {
        logger.debug("was invoking method getStudent");
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() -> {
            logger.error("There is not student with id = " + id);
            throw new StudentNotFoundException();
        }));
    }

    public StudentRecord setStudent(StudentRecord studentRecord) {
        logger.debug("was invoking method setStudent");

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
        logger.debug("was invoking method deleteStudent");
        studentRepository.deleteById(id);
    }

    public Collection<StudentRecord> getAllByAge(int age) {
        logger.debug("was invoking method getAllByAge");
        return studentRepository.findAllByAge(age).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAll() {
        logger.debug("was invoking method getAll");
        return studentRepository.findAll().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getAllByAgeInRange(int min, int max) {
        logger.debug("was invoking method getAllByAgeInRange");
        return studentRepository.findAllByAgeBetween(min, max).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudentId(long id){
        logger.debug("was invoking method getFacultyByStudentId");
        return getStudent(id).getFacultyRecord();
    }

    public Integer getStudentCount() {
        logger.debug("was invoking method getStudentCount");
        return studentRepository.getStudentsCount();
    }

    public Double getStudentsAverageAge() {
        logger.debug("was invoking method getStudentsAverageAge");
        return studentRepository.getStudentsAverageAge();
    }

    public Collection<StudentRecord> getLastFiveStudents() {
        logger.debug("was invoking method getLastFiveStudents");
        return studentRepository.findLastFiveStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<String> getStudentsStartsWith(char letter) {
        logger.debug("was invoking method getStudentsStartsWith");
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().startsWith(String.valueOf(letter)))
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getStudentsAverageAgeByStream(){
        logger.debug("was invoking method getStudentsAverageAgeByStream");
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(FacultyNotFoundException::new);
    }

    public void multiThreadMethod(){
        logger.debug("was invoking method multiThreadMethod");
        List<Student> list = studentRepository.findAll(PageRequest.of(0,6)).getContent();

        logger.info(list.get(0).getName());
        logger.info(list.get(1).getName());

        new Thread(() ->{
            logger.info(list.get(2).getName());
            logger.info(list.get(3).getName());
        }).start();

        new Thread(() ->{
            logger.info(list.get(4).getName());
            logger.info(list.get(5).getName());
        }).start();
    }

    public void multiThreadMethod2(){
        logger.debug("was invoking method multiThreadMethod2");
        List<Student> list = studentRepository.findAll(PageRequest.of(0,6)).getContent();

        printNames(list.subList(0, 2));
        new Thread(() -> printNames(list.subList(2, 4))).start();
        new Thread(() -> printNames(list.subList(4, 6))).start();
    }

    private synchronized void printNames(List<Student> students){
        students.forEach(s -> logger.info(s.getName()));
    }
}
