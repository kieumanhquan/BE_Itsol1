package com.itsol.recruit.service;

import com.itsol.recruit.dto.AllJobDTO;
import com.itsol.recruit.dto.JobSearchDTO;
import com.itsol.recruit.dto.JobTypeDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HiringJobsService {
    List<Job> getAllJob();
    AllJobDTO getOnePageJobDTO(int page);
    JobTypeDTO getMoreJobDTO(String typeJob,int page);
    JobSearchDTO searchJob(SearchJobVM searchVM, int page);
}
