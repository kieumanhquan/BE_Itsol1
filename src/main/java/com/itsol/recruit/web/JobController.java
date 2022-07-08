package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class JobController {

    public final JobService jobService ;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/job")
    public ResponseEntity<List<Job>> getAllJob(){
        return  ResponseEntity.ok().body( jobService.getAllJob());
    }

    @GetMapping(value = "/job/{id}")
    public ResponseEntity<Job> findJobById(@RequestParam("id") Long id){
        return  ResponseEntity.ok().body(jobService.findById(id));
    }

    @PostMapping(value = "/job/insert")
    public ResponseEntity<String> insertJob(@RequestBody JobDTO jobDTO) {
        jobService.insert(jobDTO);
        String result = (jobService.insert(jobDTO) == null)? "Unsuccess" : "Success";
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/job/insert")
    public ResponseEntity<String> deleteJob(@RequestParam("id") Long id) {
        String result = jobService.delete(id)? "Success" : "Unsuccess";
        return ResponseEntity.ok().body(result);
    }
}
