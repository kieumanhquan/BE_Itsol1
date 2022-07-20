package com.itsol.recruit.repository.repoext;

import com.itsol.recruit.entity.StatusJobRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusJobRegisRepository extends JpaRepository<StatusJobRegister,Long> {
    StatusJobRegister findStatusJobRegisterByDescription(String name);
}
