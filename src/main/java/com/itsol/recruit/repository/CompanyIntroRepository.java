package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyIntroRepository extends JpaRepository<Company, Long> {
}
