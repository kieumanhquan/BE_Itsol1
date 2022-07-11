package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.FileRecruitDTO;
import com.itsol.recruit.service.filerecruit.FileRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//@RestController("/api")
@RequestMapping(value = Constants.Api.Path.ADMIN)

public class FileRecruitController {
    @Autowired
    private FileRecruitService fileRecruitService;

    @GetMapping("/file-recruit")
    public List<FileRecruitDTO> getAllFileRecruit() {
        return fileRecruitService.getAllFileRecruit();
    }

    @GetMapping("/api/file-recruit-sort")
    public List<FileRecruitDTO> getAllFileRecruitSort() {
        return fileRecruitService.getAllFileRecruitSort();
    }
    @GetMapping("/api/find-name")
    public List<FileRecruitDTO> getFindName(@RequestParam("name" ) String name) {
        return fileRecruitService.findName(name);
    }

}
