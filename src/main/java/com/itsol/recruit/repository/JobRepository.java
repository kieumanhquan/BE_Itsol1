package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryExt {

    Job findJobByName(String name);

    List<Job> findJobsBySkills(String skills);

    @Query("select j from job j")
    Page<Job> findJobPage(Pageable pageable);

//    Job findJobByName(String name);
    Job findJobById(Long id);

    @Query("select j from job j where j.salaryMin <= ?2 and j.salaryMax >=?1")
    List<Job> findJobsBySalaryMaxAndsAndSalaryMax(Long salaryMax, Long salaryMin);

    List<Job> findAll();

//    @Query("select j from job j join j.jobPosition jp where lower(j.name) like lower(concat('%', :name, '%'))")
    @Query("select j from job j where lower(j.name) like lower(concat('%', :keyword, '%')) " +
                                    "or lower(j.jobPosition.description) like lower(concat('%', :keyword, '%'))" +
                                    "or lower(j.status.description) like lower(concat('%', :keyword, '%'))")
    List<Job> findJobByManyCon(String keyword);

}
