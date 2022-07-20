package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.service.impl.FileStorageServiceImpl;
import com.itsol.recruit.service.jobregister.JobRegisterService;
import com.itsol.recruit.web.vm.RegisterProfileVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
public class JobRegistrationController {
    @Autowired
    FileStorageServiceImpl fileStorageService;
    @Autowired
    JobRegisterService jobRegisterService;

    @PostMapping("api/registration")
    public ResponseEntity<?> jobRegistration(@RequestParam String userName, @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(Collections.singletonMap("response", jobRegisterService.jobRegistration(userName, file)));
    }
}
