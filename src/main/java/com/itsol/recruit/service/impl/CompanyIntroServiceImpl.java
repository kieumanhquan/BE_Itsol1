package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Company;
import com.itsol.recruit.repository.CompanyIntroRepository;
import com.itsol.recruit.service.CompanyIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyIntroServiceImpl implements CompanyIntroService {
    @Autowired
    CompanyIntroRepository companyIntroRepository;

    public CompanyIntroServiceImpl(CompanyIntroRepository companyIntroRepository) {
        this.companyIntroRepository = companyIntroRepository;
    }

    @Override
    public Company findById(Long id) {
        return companyIntroRepository.findById(id).get();
    }
}
