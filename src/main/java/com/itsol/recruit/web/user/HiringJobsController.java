package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.AllJobDTO;
import com.itsol.recruit.dto.JobTypeDTO;
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
    public ResponseEntity<AllJobDTO> Hiring_Jobs (@RequestParam(name = "pageNumber") int pageNumber){
        return new ResponseEntity<>(hiringJobsService.getOnePageJobDTO(pageNumber), HttpStatus.OK);
    }

    @PostMapping(value = "/searchJob")
    public ResponseEntity<?> searchJob(@RequestBody SearchJobVM searchVM, @RequestParam(name = "pageNumber") int page){
        System.out.println(searchVM);
        try{
            return  new ResponseEntity<>(hiringJobsServiceImpl.searchJob(searchVM,page),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/viewMoreJob/{typeJob}")
    public ResponseEntity<JobTypeDTO> seeMoreJob(@PathVariable String typeJob, @RequestParam(name = "pageNumber")int pageNumber){
        return new ResponseEntity<>(hiringJobsServiceImpl.getMoreJobDTO(typeJob,pageNumber),HttpStatus.OK);
    }
}
