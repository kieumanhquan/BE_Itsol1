package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepositoryExt{
    public Job getAllJob();
}
