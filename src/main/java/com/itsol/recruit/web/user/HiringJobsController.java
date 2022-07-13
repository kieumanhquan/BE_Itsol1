package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.HiringJobsService;
import com.itsol.recruit.service.impl.HiringJobsServiceImpl;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class HiringJobsController {
    @Autowired
    HiringJobsService hiringJobsService;

    HiringJobsServiceImpl hiringJobsServiceImpl;



//    public ResponseEntity<List<JobRegisterDTO>> getAllJobRegister(@RequestParam(value = "pageNo") int pageNo,
//                                                                  @RequestParam(value = "pageSize") int pageSize ,   @RequestParam(value = "sort", required = false) String sort) {
//
//        Page<JobRegisterDTO> page = jobRegisterService.getAllJobRegister(pageNo,pageSize,sort);
//        return ResponseEntity.ok().body(page.getContent());
//    }

    public HiringJobsController(HiringJobsService hiringJobsService,HiringJobsServiceImpl hiringJobsServiceImpl) {
        this.hiringJobsService = hiringJobsService;
        this.hiringJobsServiceImpl = hiringJobsServiceImpl;
    }

    @GetMapping(value = "itsol_recruitment")
    public ResponseEntity<List<Job>> Hiring_Jobs (){
        return new ResponseEntity<>(hiringJobsService.getAllJob(), HttpStatus.OK);
    }
    @PostMapping(value = "/searchJob")
    public ResponseEntity<?> searchJob(@RequestBody SearchJobVM searchVM){
        System.out.println(searchVM);
        return  new ResponseEntity<>(hiringJobsServiceImpl.searchJob(searchVM),HttpStatus.OK);
    }
}
