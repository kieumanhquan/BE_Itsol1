package com.itsol.recruit.repository;

import com.itsol.recruit.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB,String> {
    FileDB findFileDBById(String id);
}
