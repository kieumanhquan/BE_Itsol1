package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.dto.FileUploadDTO;
import com.itsol.recruit.service.impl.jobregister.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    FileUploadDTO fileUploadDTO;
    @PostMapping("/upload-file")
    public ResponseEntity<FileUploadDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        uploadFileService.saveFile(fileName,multipartFile);
        fileUploadDTO.setFileName(fileName);
        fileUploadDTO.setSize(size);
        fileUploadDTO.setDownloadUri("/downloadFile");
        return new ResponseEntity<>(fileUploadDTO , HttpStatus.OK);
    }
}
