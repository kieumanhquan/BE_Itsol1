package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.StatusJob;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.service.StatusJobService;
import com.itsol.recruit.service.WorkingFormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class WorkingFormController {
    public final WorkingFormService workingFormService;

    public WorkingFormController(WorkingFormService workingFormService) {
        this.workingFormService = workingFormService;
    }

    @GetMapping(value = "/working-form")
    public ResponseEntity<List<WorkingForm>> getAllWorkingForm(){
        return  ResponseEntity.ok().body( workingFormService.getAllWorkingForm());
    }

    @GetMapping(value = "/working-form/{id}")
    public ResponseEntity<WorkingForm> findWorkingFormById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(workingFormService.findById(id));
    }
}
