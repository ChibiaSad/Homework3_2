package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord addFaculty(FacultyRecord facultyRecord) {
        return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord getFaculty(long id) {
        return recordMapper.toRecord(facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new));
    }

    public FacultyRecord setFaculty(FacultyRecord facultyRecord) {
        Faculty oldFaculty = facultyRepository.findById(facultyRecord.getId()).orElseThrow(FacultyNotFoundException::new);
        oldFaculty.setColor(facultyRecord.getColor());
        oldFaculty.setName(facultyRecord.getName());
        return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<FacultyRecord> getAllByColor(String color) {
        return facultyRepository.findAllByColorIgnoreCase(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> getAllByColorOrName(String color, String name) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> getAll() {
        return facultyRepository.findAll().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getStudentsByFacultyId(long id) {
        return facultyRepository.findAllStudentsByFacultyId(id);
    }

}
