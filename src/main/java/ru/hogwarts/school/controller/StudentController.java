package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
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
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        Student student = studentService.getStudent(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity<Student> setStudent(@RequestBody Student student){
        Student temp = studentService.setStudent(student);
        if(temp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "age")
    public ResponseEntity<Collection<Student>> getAllByAge(@RequestParam int age){
        return ResponseEntity.ok(studentService.getAllByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/between")
    public ResponseEntity<Collection<Student>> getAllByAgeInRange(@RequestParam int min,
                                                                  @RequestParam int max){
        return ResponseEntity.ok(studentService.getAllByAgeInRange(min, max));
    }

    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> getFacultyOfStudentById(@PathVariable long id){
        return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
    }
}
