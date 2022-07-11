package com.itsol.recruit.repository;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister,Long> {
    @Query("SELECT  j FROM job_register j order by j.dateRegister asc")
    List<JobRegister> findAllOrderByDateAsc();


    List<JobRegister> findJobRegisterByUser(User user);

}
