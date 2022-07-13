package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.JobPosition;
import com.itsol.recruit.service.JobPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class JobPositionController {

    public final JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping(value = "/job-position")
    public ResponseEntity<List<JobPosition>> getAllJobPosition(){
        return  ResponseEntity.ok().body( jobPositionService.getAllJobPosition());
    }

    @GetMapping(value = "/job-position/{id}")
    public ResponseEntity<JobPosition> findJobPositionById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(jobPositionService.findById(id));
    }
}
