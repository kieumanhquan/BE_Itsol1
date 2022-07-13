package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.BaseRepository;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import com.itsol.recruit.web.vm.SearchJobVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JobRepositoryImpl extends BaseRepository implements JobRepositoryExt {

    @Override
    public Job getAllJob() {
        return null;
    }
    
    public List<Job> searchJob(SearchJobVM searchVM){
        String[] parts = searchVM.getSalary().split("-");
        int working_form_id;
        if( searchVM.getWorking_form() == "full time"){
            working_form_id = 1;
        }else working_form_id = 2;
        String query = "SELECT * FROM\n" +
                "                JOB where skills like '%"+ searchVM.getSkill()+"%' and salary_min >= "+
                parts[0] +"and salary_max <= "+ parts[1]+" and working_form_id ="+working_form_id+"number_experience = "+
                searchVM.getExperience();
        return getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(Job.class));
    }
}
