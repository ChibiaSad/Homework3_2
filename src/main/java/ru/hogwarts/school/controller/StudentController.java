package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public StudentRecord addStudent(@RequestBody StudentRecord studentRecord) {
        return studentService.addStudent(studentRecord);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRecord> getStudent(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PutMapping()
    public ResponseEntity<StudentRecord> setStudent(@RequestBody StudentRecord studentRecord) {
        return ResponseEntity.ok(studentService.setStudent(studentRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "age")
    public ResponseEntity<Collection<StudentRecord>> getAllByAge(@RequestParam int age) {
        return ResponseEntity.ok(studentService.getAllByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<StudentRecord>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/between")
    public ResponseEntity<Collection<StudentRecord>> getAllByAgeInRange(@RequestParam int min,
                                                                        @RequestParam int max) {
        return ResponseEntity.ok(studentService.getAllByAgeInRange(min, max));
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<FacultyRecord> getFacultyOfStudentById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getStudentCount(){
        return ResponseEntity.ok(studentService.getStudentCount());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getStudentsAverageAge(){
        return ResponseEntity.ok(studentService.getStudentsAverageAge());
    }

    @GetMapping("/last-five")
    public ResponseEntity<Collection<StudentRecord>> getLastFiveStudents(){
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }
}
