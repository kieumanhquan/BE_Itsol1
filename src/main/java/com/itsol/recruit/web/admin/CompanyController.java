package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.ResponseDTO;
import com.itsol.recruit.entity.Company;
import com.itsol.recruit.entity.CompanyAvatar;
import com.itsol.recruit.service.CompanyService;
import com.itsol.recruit.service.emailRegister.AvatarCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class CompanyController {
    public final CompanyService companyService;
    public final AvatarCompanyService avatarCompanyService;

    public CompanyController(CompanyService companyService, AvatarCompanyService avatarCompanyService) {
        this.companyService = companyService;
        this.avatarCompanyService = avatarCompanyService;
    }

    @PutMapping(value = "/home/company-edit")
    public ResponseEntity<Company> UpdateCompany(@RequestBody Company company){
        System.out.println(company);
        return new ResponseEntity<>(companyService.updateCompany(company), HttpStatus.OK);
    }
    @GetMapping(value = "/home/get-company/{id}")
    public ResponseEntity<Company> GetCompany(@PathVariable Long id){
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/image/{id}")
    public ResponseEntity<CompanyAvatar> getImageDetail(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok().body(avatarCompanyService.findCompanyAvatarsById(id));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    @PutMapping(value = "/upload/image")
    public ResponseEntity<ResponseDTO> uploadImage(@RequestParam("image") MultipartFile file){
        try {
            ResponseDTO responseDTO=avatarCompanyService.uploadImage(file);
            responseDTO.setStatus(HttpStatus.OK);
            return ResponseEntity.ok().body(responseDTO);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NOT_FOUND,"Fail upload file"));
        }
    }
}
