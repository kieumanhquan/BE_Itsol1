package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.HiringJobsService;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class HiringJobsController {
    HiringJobsService hiringJobsService;

    public HiringJobsController(HiringJobsService hiringJobsService) {
        this.hiringJobsService = hiringJobsService;
    }

    @GetMapping(value = "itsol_recruitment")
    public ResponseEntity<List<Job>> Hiring_Jobs (){
        return new ResponseEntity<>(hiringJobsService.getAllJob(), HttpStatus.OK);
    }
    @PostMapping(value = "/searchJob")
    public ResponseEntity<List<Job>> searchJob(@RequestBody SearchJobVM searchVM){
        return ResponseEntity.ok().body(hiringJobsService.searchJob(searchVM));
    }
}
