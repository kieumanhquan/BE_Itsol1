package com.itsol.recruit.service;

import com.itsol.recruit.entity.WorkingForm;

import java.util.List;

public interface WorkingFormService {
    public List<WorkingForm> getAllWorkingForm();

    public WorkingForm findById(Long id);
}
