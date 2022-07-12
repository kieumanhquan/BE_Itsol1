package com.itsol.recruit.service;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.web.vm.SearchJobVM;

import java.util.List;

public interface HiringJobsService {
    List<Job> getAllJob();
    List<Job> searchJob(SearchJobVM searchVM);
}
