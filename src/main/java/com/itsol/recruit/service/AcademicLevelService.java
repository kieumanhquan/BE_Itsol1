package com.itsol.recruit.service;

import com.itsol.recruit.entity.AcademicLevel;
import java.util.List;

public interface AcademicLevelService {
    public List<AcademicLevel> getAllAcademicLevel();

    public AcademicLevel findById(Long id);
}
