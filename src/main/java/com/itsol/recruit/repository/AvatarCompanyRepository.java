package com.itsol.recruit.repository;

import com.itsol.recruit.entity.CompanyAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarCompanyRepository extends JpaRepository<CompanyAvatar,Long> {
    CompanyAvatar findCompanyAvatarsById(Long id);
}

