package ru.hogwarts.school.controller;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AvatarRecord upload(@RequestParam MultipartFile avatar,
                               @RequestParam long studentId) throws IOException {
        return avatarService.upload(avatar, studentId);
    }

    @GetMapping("/{id}/from-DB")
    public ResponseEntity<byte[]> readFromDB(@PathVariable long id){
        Pair<byte[], String> pair = avatarService.readFromDB(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());
    }

    @GetMapping(value = "/{id}/from-file")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) throws IOException{
        Pair<byte[], String> pair = avatarService.readFromFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());
    }
}
