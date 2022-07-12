package com.itsol.recruit.web.admin.file;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.service.jobregister.JobRegisterService;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController()
//@RequestMapping(value = Constants.Api.Path.ADMIN)

public class JobRegisterController {

    @Autowired
    private JobRegisterService jobRegisterService;

    @GetMapping("/api/job-register")
    public ResponseEntity<List<JobRegisterDTO>> getAllJobRegister(@RequestParam(value = "pageNo") int pageNo,
    @RequestParam(value = "pageSize") int pageSize ,   @RequestParam(value = "sort", required = false) String sort) {

        Page<JobRegisterDTO> page = jobRegisterService.getAllJobRegister(pageNo,pageSize,sort);
        return ResponseEntity.ok().body(page.getContent());
    }


}
