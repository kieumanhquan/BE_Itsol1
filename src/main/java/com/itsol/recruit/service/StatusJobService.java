package com.itsol.recruit.service;

import com.itsol.recruit.entity.StatusJob;

import java.util.List;

public interface StatusJobService {
    public List<StatusJob> getAllStatusJob();

    public StatusJob findById(Long id);
}
