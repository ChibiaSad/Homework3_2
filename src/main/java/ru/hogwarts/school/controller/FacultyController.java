package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id){
        Faculty faculty = facultyService.getFaculty(id);
        if(faculty.getId() == -1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/set")
    public ResponseEntity<Faculty> setFaculty(@RequestBody Faculty faculty){
        Faculty temp = facultyService.setFaculty(faculty);
        if(temp == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{color}")
    public ResponseEntity<Collection<Faculty>> getAllByColor(@PathVariable String color){
        return ResponseEntity.ok(facultyService.getAllByColor(color));
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> getAll(){
        return ResponseEntity.ok(facultyService.getAll());
    }
}
