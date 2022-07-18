package com.itsol.recruit.repository;

import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepository extends JpaRepository<User, Long> {
}
