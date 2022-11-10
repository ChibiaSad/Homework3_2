package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
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
    public FacultyRecord addFaculty(@RequestBody FacultyRecord facultyRecord){
        return facultyService.addFaculty(facultyRecord);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyRecord> getFaculty(@PathVariable long id){
        return ResponseEntity.ok(facultyService.getFaculty(id));
    }

    @PutMapping()
    public ResponseEntity<FacultyRecord> setFaculty(@RequestBody FacultyRecord facultyRecord){
        return ResponseEntity.ok(facultyService.setFaculty(facultyRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "color")
    public ResponseEntity<Collection<FacultyRecord>> getAllByColor(@RequestParam String color,
                                                             @RequestParam(required = false) String name){
        if(name == null){
            return ResponseEntity.ok(facultyService.getAllByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllByColorOrName(color, name));
    }

    @GetMapping()
    public ResponseEntity<Collection<FacultyRecord>> getAll(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<StudentRecord>> getStudentsOfFacultyById(@PathVariable long id){
        return ResponseEntity.ok(facultyService.getStudentsByFacultyId(id));
    }
}
