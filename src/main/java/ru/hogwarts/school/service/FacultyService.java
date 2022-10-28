package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long id = 1L;

    public Faculty addFaculty(Faculty faculty){
        faculty.setId(id);
        faculties.put(id++, faculty);
        return faculty;
    }

    public Faculty getFaculty(long id){
        if(faculties.containsKey(id)){
            return faculties.get(id);
        }
        return null;
    }

    public Faculty setFaculty(Faculty faculty){
        if(faculties.containsKey(faculty.getId())){
            return faculties.put(faculty.getId(), faculty);
        }
        return null;
    }

    public Faculty deleteFaculty(long id){
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllByAge(String color) {
        return faculties.values().stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }
}
