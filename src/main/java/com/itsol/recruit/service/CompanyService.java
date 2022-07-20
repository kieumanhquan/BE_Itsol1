package com.itsol.recruit.service;

import com.itsol.recruit.entity.Company;

public interface CompanyService {
    Company findById(Long id);

    Company updateCompany(Company company);

}
