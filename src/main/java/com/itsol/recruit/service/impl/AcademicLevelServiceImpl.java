package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.AcademicLevel;
import com.itsol.recruit.repository.AcademicLevelRepository;
import com.itsol.recruit.service.AcademicLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AcademicLevelServiceImpl implements AcademicLevelService {
    @Autowired
    AcademicLevelRepository academicLevelRepository;

    @Override
    public List<AcademicLevel> getAllAcademicLevel() {
        return academicLevelRepository.findAll();
    }

    @Override
    public AcademicLevel findById(Long id) {
        return academicLevelRepository.findById(id).get();
    }

}
