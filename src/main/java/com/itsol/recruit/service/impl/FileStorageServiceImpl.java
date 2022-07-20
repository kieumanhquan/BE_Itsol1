package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.FileDB;
import com.itsol.recruit.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl {
    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            FileDB fileDB = new FileDB();
            fileDB.setName(fileName);
            fileDB.setType(file.getContentType());
            fileDB.setData(file.getBytes());
            return fileDBRepository.save(fileDB);
        }catch (MultipartException e){
            return null;
        }

    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}

