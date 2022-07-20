package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.impl.JobServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.jar.JarOutputStream;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class JobController {

    public final JobService jobService ;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

/*
    @GetMapping(value = "/job")
    public ResponseEntity<List<Job>> getAllJob(){
        return  ResponseEntity.ok().body( jobService.getAllJob());
    }
*/

    @GetMapping("/job")
    public ResponseEntity<List<Job>> getAllJobRegister(@RequestParam(value = "pageNo") int pageNo,
                                                      @RequestParam(value = "pageSize") int pageSize,
                                                       @RequestParam(value = "sort", required = false) String sort,
                                                      @RequestParam(value = "type" ,required = false) boolean type) {

        Page<Job> page = jobService.getAllJob(pageNo, pageSize, sort, type);
        return ResponseEntity.ok().body(page.getContent());
    }
    @GetMapping(value = "/job/{id}")
    public ResponseEntity<?> findJobById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(jobService.findById(id));
    }

    @GetMapping(value = "/job/search")
    public ResponseEntity<List<Job>> findJobByManyCon(@RequestParam(value = "keyword") String keyword){
        return ResponseEntity.ok().body(jobService.findJobByManyCon(keyword));
    }

    @PostMapping(value = "/job/insert")
    public ResponseEntity<Job> insertJob(@RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok().body(jobService.insert(jobDTO));
    }

    @PutMapping(value = "/job/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return ResponseEntity.ok().body(jobService.update(id, job));
    }

    @PutMapping(value = "/job/updateStatus")
    public ResponseEntity<Job> updateStatusJob(@RequestParam(value = "id") Long id, @RequestParam(value = "idStatus") Long idStatus) {
        return ResponseEntity.ok().body(jobService.updateStatus(id,idStatus));
    }

    @DeleteMapping(value = "/job/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") Long id) {
        String result = jobService.delete(id)? "Success" : "Unsuccess";
        return ResponseEntity.ok().body(result);
    }
}
