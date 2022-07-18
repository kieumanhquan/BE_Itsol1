package com.itsol.recruit.web.admin;


import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.service.AcademicLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class AcademicLevelController {

    public final AcademicLevelService academicLevelService;

    public AcademicLevelController(AcademicLevelService academicLevelService) {
        this.academicLevelService = academicLevelService;
    }

    @GetMapping(value = "/academic-level")
    public ResponseEntity<List<AcademicLevel>> getAllAcademicLevel(){
        return  ResponseEntity.ok().body( academicLevelService.getAllAcademicLevel());
    }

    @GetMapping(value = "/academic-level/{id}")
    public ResponseEntity<AcademicLevel> findAcademicLevelById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(academicLevelService.findById(id));
    }

}
