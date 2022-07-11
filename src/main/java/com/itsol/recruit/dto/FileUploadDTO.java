package com.itsol.recruit.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class FileUploadDTO {
    private String fileName;
    private String downloadUri;
    private long size;
}
