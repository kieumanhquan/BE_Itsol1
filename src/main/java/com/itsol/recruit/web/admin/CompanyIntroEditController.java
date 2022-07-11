package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Company;
import com.itsol.recruit.service.CompanyIntroEditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
public class CompanyIntroEditController {
    public final CompanyIntroEditService companyIntroEditService;

    public CompanyIntroEditController(CompanyIntroEditService companyIntroEditService) {
        this.companyIntroEditService = companyIntroEditService;
    }

    @PutMapping(value = "edit_intro_company")
    public ResponseEntity<Object> UpdateCompanyIntro(@RequestBody Company company){
        System.out.println(company);
        return new ResponseEntity<>(companyIntroEditService.UpdateCompany(company), HttpStatus.OK);
    }
}
