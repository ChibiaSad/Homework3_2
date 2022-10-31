package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        Student student = studentService.getStudent(id);
        if(student.getId() == -1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/set")
    public ResponseEntity<Student> setStudent(@RequestBody Student student){
        Student temp = studentService.setStudent(student);
        if(temp == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> getAllByAge(@PathVariable int age){
        return ResponseEntity.ok(studentService.getAllByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getAll(){
        return ResponseEntity.ok(studentService.getAll());
    }
}
