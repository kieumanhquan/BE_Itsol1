package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.BaseRepository;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import com.itsol.recruit.web.vm.SearchJobVM;
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
        String query = "SELECT * FROM\n" +
                "                JOB where skills like "+ searchVM.getSkill();
        return getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(Job.class));
    }
}
