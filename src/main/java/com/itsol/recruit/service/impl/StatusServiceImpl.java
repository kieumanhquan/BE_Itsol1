package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.StatusJob;
import com.itsol.recruit.repository.StatusJobRepository;
import com.itsol.recruit.service.StatusJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusJobService {

    @Autowired
    StatusJobRepository statusJobRepository;

    @Override
    public List<StatusJob> getAllStatusJob() {
        return statusJobRepository.findAll();
    }

    @Override
    public StatusJob findById(Long id) {
        return statusJobRepository.findById(id).get();
    }
}
