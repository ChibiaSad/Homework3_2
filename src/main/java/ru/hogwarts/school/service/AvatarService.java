package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public void upload(long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Path path = Path.of(avatarDir, student + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);

        try(InputStream in = avatarFile.getInputStream();
            OutputStream out = Files.newOutputStream(path, CREATE_NEW);
            BufferedInputStream bin = new BufferedInputStream(in, 1024);
            BufferedOutputStream bout = new BufferedOutputStream(out, 1024))
        {
            bin.transferTo(bout);
        }

        Avatar avatar = avatarRepository.findById(studentId).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long id){
        return avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);
    }
    private String getExtensions(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
