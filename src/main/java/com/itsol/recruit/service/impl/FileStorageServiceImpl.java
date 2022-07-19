package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.FileDB;
import com.itsol.recruit.repository.FileDBRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl {
    private FileDBRepository fileDBRepository;
    public FileDB store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileDB fileDB = new FileDB(fileName,file.getContentType(),file.getBytes());
        return fileDBRepository.save(fileDB);
    }
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}

