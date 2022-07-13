package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryExt {

    Job findJobByName(String name);

    List<Job> findJobsBySkills(String skills);

    List<Job> findJobsByAddressWork(String addressWork);

    @Query("select j from job j where j.salaryMin <= ?2 and j.salaryMax >=?1")
    List<Job> findJobsBySalaryMaxAndsAndSalaryMax(Long salaryMax, Long salaryMin);

    List<Job> findJobsByWorkingForm(Long workingFormId);

    List<Job> findJobsByNumberExperience(String numberExperience);

    List<Job> findJobsByAcademicLevel(Long academicLevelId);
    Page<Job> findAll(Pageable pageable);


}
