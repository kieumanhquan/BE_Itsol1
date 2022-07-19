package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Company;
import com.itsol.recruit.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class CompanyPublicController {

    CompanyService companyIntroService;

    public CompanyPublicController(CompanyService companyIntroService) {
        this.companyIntroService = companyIntroService;
    }

    @GetMapping(value = "aboutus")

    public ResponseEntity<Company> IntroCompany(){
        return  new ResponseEntity<>(companyIntroService.findById(1L), HttpStatus.OK);
    }
}
