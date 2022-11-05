package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping()
    public Faculty addFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id){
        Faculty faculty = facultyService.getFaculty(id);
        if(faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> setFaculty(@RequestBody Faculty faculty){
        Faculty temp = facultyService.setFaculty(faculty);
        if(temp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "color")
    public ResponseEntity<Collection<Faculty>> getAllByColor(@RequestParam String color,
                                                             @RequestParam(required = false) String name){
        if(name == null){
            return ResponseEntity.ok(facultyService.getAllByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllByColorOrName(color, name));
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> getAll(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Collection<Student>> getStudentsOfFacultyById(@PathVariable long id){
        return ResponseEntity.ok(facultyService.getStudentByFacultyId(id));
    }
}
