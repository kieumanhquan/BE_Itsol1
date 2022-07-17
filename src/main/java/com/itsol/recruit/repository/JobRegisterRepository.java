package com.itsol.recruit.repository;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister, Long> {

    @Query("select j from job_register j")
    Page<JobRegister> findJobRegister(Pageable pageable);

    JobRegister findJobRegisterById(long id);
    JobRegister findJobRegisterByUser(User user);
}
