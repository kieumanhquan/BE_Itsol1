package com.itsol.recruit.repository;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExt {

    User findByUserName(String userName);
    User findByNameContaining(String Name);

    User findById(int id);

    @Query("select u from Users u join u.roles r where r.id = 2")
    List<User> getJE();

    @Query("select u from Users u join u.roles r where r.id = 2 ORDER BY u.name ASC ")
    List<User> getJESortByName();


    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);

    User findUserByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String username);


    /*@Query("select u from Users u join u.roles ur "
            + "where  ur.id in ( "
            + "select r from roles r join r.users ru where ru.id = : roleId)" )*/

    @Query("select u from Users u join u.roles r where r.id = 2")
    List<User> findJE();
}
