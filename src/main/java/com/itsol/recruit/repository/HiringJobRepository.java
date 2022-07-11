package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringJobRepository extends JpaRepository<Job,Long> {
}
