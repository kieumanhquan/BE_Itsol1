package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.service.DetailFileService;
import com.itsol.recruit.service.impl.DetailFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController

public class DetailFileController {
    @Autowired
    DetailFileService detailFile;

    @PostMapping("/api/detailfile")
    public ResponseEntity<?> getDetailFile(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok().body(detailFile.getDetailFile(id));
    }
}
