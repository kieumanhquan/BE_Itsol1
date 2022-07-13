package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.WorkingFormRepository;
import com.itsol.recruit.service.WorkingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkingFormServiceImpl implements WorkingFormService {

    @Autowired
    WorkingFormRepository workingFormRepository;
    @Override
    public List<WorkingForm> getAllWorkingForm() {
        return workingFormRepository.findAll();
    }

    @Override
    public WorkingForm findById(Long id) {
        return workingFormRepository.findById(id).get();
    }
}
