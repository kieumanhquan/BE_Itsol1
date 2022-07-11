package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Company;
import com.itsol.recruit.repository.CompanyIntroRepository;
import com.itsol.recruit.service.CompanyIntroEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyIntroEditServiceIml implements CompanyIntroEditService {
    @Autowired
    CompanyIntroRepository companyIntroRepository;
    @Override
    public Company UpdateCompany(Company company) {
        return companyIntroRepository.save(company);
    }
}
