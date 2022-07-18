package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.StatusJob;
import com.itsol.recruit.service.StatusJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class StatusJobController {
    public final StatusJobService statusJobService;

    public StatusJobController(StatusJobService statusJobService) {
        this.statusJobService = statusJobService;
    }

    @GetMapping(value = "/status-job")
    public ResponseEntity<List<StatusJob>> getAllStatusJob(){
        return  ResponseEntity.ok().body( statusJobService.getAllStatusJob());
    }

    @GetMapping(value = "/status-job/{id}")
    public ResponseEntity<StatusJob> findStatusJobById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(statusJobService.findById(id));
    }
}

