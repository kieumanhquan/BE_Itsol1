package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.AllJobDTO;
import com.itsol.recruit.dto.JobSearchDTO;
import com.itsol.recruit.dto.JobTypeDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.HiringJobRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.repoimpl.JobRepositoryImpl;
import com.itsol.recruit.service.HiringJobsService;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HiringJobsServiceImpl implements HiringJobsService {
    @Autowired
    private final  HiringJobRepository hiringJobRepository;
    private final JobRepositoryImpl jobRepositoryImpl;
    private final JobRepository jobRepository;
    public HiringJobsServiceImpl(HiringJobRepository hiringJobRepository, JobRepository jobRepository, JobRepositoryImpl jobRepositoryImpl) {
        this.hiringJobRepository = hiringJobRepository;
        this.jobRepositoryImpl = jobRepositoryImpl;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJob() {
        return hiringJobRepository.findAll();
    }

    @Override
    public AllJobDTO getOnePageJobDTO(int page) {
        return jobRepositoryImpl.getOnePageJobDTO(page);
    }

    @Override
    public JobTypeDTO getMoreJobDTO(String typeJob, int page) {
        JobTypeDTO jobTypeDTO = new JobTypeDTO();
        int pagesize = 9;
        if(typeJob.equals("newJob")){
            jobTypeDTO.setJobList(getOnePageJobDTO(page).getNewJob());
            int numberPage = 0;
            int tmp = jobRepositoryImpl.numberRecord().getNewJob().size()/pagesize;
            System.out.println( jobRepositoryImpl.numberRecord().getNewJob().size());
            if(jobRepositoryImpl.numberRecord().getNewJob().size()% pagesize == 0){
                numberPage = tmp;
            }else {
                numberPage = tmp+1;
            }

            List<Integer> listPage = new ArrayList<>();
            for(int i = 1; i<= numberPage;i ++) listPage.add(i);
            jobTypeDTO.setListPage(listPage);
        } else if (typeJob.equals("urgentJob")) {

            jobTypeDTO.setJobList(getOnePageJobDTO(page).getUrgentJob());
            int numberPage = 0;
            int tmp = jobRepositoryImpl.numberRecord().getUrgentJob().size()/pagesize;
            System.out.println("\n" + tmp);
            if(jobRepositoryImpl.numberRecord().getUrgentJob().size()% pagesize == 0){
                numberPage = tmp;
            }else {
                numberPage = tmp+1;
            }
            List<Integer> listPage = new ArrayList<>();
            for(int i = 1; i<= numberPage;i ++) listPage.add(i);
            jobTypeDTO.setListPage(listPage);
        } else if (typeJob.equals("highPJob")) {
            jobTypeDTO.setJobList(getOnePageJobDTO(page).getHighPJob());
            int numberPage = 0;
            int tmp = jobRepositoryImpl.numberRecord().getHighPJob().size()/pagesize;
            if(jobRepositoryImpl.numberRecord().getHighPJob().size()% pagesize == 0){
                numberPage = tmp;
            }else {
                numberPage = tmp+1;
            }
            List<Integer> listPage = new ArrayList<>();
            for(int i = 1; i<= numberPage;i ++) listPage.add(i);


        }

        return  jobTypeDTO;
    }


    @Override
    public JobSearchDTO searchJob(SearchJobVM searchVM, int page) {
        JobSearchDTO jobSearchDTO = new JobSearchDTO();
        List<Job> jobListOnePage = jobRepositoryImpl.searchJob(searchVM,page);
        jobSearchDTO.setJobList(jobListOnePage);
        int sizeOnepage = 9;
        int pages = 0;
        int c = (jobRepositoryImpl.numberRecordSearch(searchVM))/(sizeOnepage);
        System.out.println(jobRepositoryImpl.numberRecordSearch(searchVM));
        if(jobRepositoryImpl.numberRecordSearch(searchVM)%sizeOnepage == 0){
            pages = c;
        }else {
            pages = c+1;
        }
        List<Integer> arr = new ArrayList();
        for(int i = 1; i<= pages; i++){
            arr.add(i);
        }
        jobSearchDTO.setPageNumberList(arr);
        return jobSearchDTO;
    }

}
