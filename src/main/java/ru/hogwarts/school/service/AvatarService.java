package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;
    private final RecordMapper recordMapper;

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository, RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.recordMapper = recordMapper;
    }

    public AvatarRecord upload(MultipartFile avatarFile, long studentId) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);

        String extension = Optional.ofNullable(avatarFile.getOriginalFilename())
                .map(f -> f.substring(f.lastIndexOf(".")))
                .orElse("");
        Path path = Path.of(avatarDir, student + extension);

        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        Files.write(path, avatarFile.getBytes());

        Avatar avatar = avatarRepository.findByStudent_id(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        return recordMapper.toRecord(avatarRepository.save(avatar));
    }

    public Pair<byte[], String> readFromDB(long id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);
        return Pair.of(avatar.getData(), avatar.getMediaType());
    }

    public Pair<byte[], String> readFromFile(long id) throws IOException {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);
        Path path = Path.of(avatar.getFilePath());
        return Pair.of(Files.readAllBytes(path), avatar.getMediaType());
    }

    public Collection<AvatarRecord> getAllByPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent().stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }
}
