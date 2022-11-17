package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord addFaculty(FacultyRecord facultyRecord) {
        logger.info("was invoking method addFaculty");
        return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord getFaculty(long id) {
        logger.info("was invoking method getFaculty");
        return recordMapper.toRecord(facultyRepository.findById(id).orElseThrow(() -> {
            logger.error("There is not faculty with id = " + id);
            throw new FacultyNotFoundException();
        }));
    }

    public FacultyRecord setFaculty(FacultyRecord facultyRecord) {
        logger.info("was invoking method setFaculty");
        Faculty oldFaculty = facultyRepository.findById(facultyRecord.getId()).orElseThrow(() -> {
            logger.error("There is not faculty with id = " + facultyRecord.getId());
            throw new FacultyNotFoundException();
        });

        oldFaculty.setColor(facultyRecord.getColor());
        oldFaculty.setName(facultyRecord.getName());
        return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public void deleteFaculty(long id) {
        logger.info("was invoking method deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<FacultyRecord> getAllByColor(String color) {
        logger.info("was invoking method getAllByColor");
        return facultyRepository.findAllByColorIgnoreCase(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> getAllByColorOrName(String color, String name) {
        logger.info("was invoking method getAllByColorOrName");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> getAll() {
        logger.info("was invoking method getAll");
        return facultyRepository.findAll().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getStudentsByFacultyId(long id) {
        logger.info("was invoking method getStudentsByFacultyId");
        return facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new)
                .getStudents().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
}
