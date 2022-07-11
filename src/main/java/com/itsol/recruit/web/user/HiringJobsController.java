package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.HiringJobsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
