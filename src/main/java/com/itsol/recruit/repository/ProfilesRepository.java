package com.itsol.recruit.repository;

import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
//    @Query("select u from profiles u join u.user r where r.id = 1")
    @Query("select p from profiles p where p.id = 1")
    Profiles findProfilesByUser(Long user_id);
}
