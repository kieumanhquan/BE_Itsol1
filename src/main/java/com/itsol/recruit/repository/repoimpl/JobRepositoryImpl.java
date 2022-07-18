package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.AllJobDTO;
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
    
    public List<Job> searchJob(SearchJobVM searchVM,int page){
        String salary_min = "0";
        String salary_max = "10000";

        if(searchVM.getSkills().equals("Tất cả")){
            searchVM.setSkills("");
        }
        if(searchVM.getWorking_form().equals("Tất cả")){
            searchVM.setWorking_form("");
        }
        if(searchVM.getAddress_work().equals("Tất cả")){
            searchVM.setAddress_work("");
        }
        if(searchVM.getAcademic_level().equals("Tất cả")){
            searchVM.setAcademic_level("");
        }

        if(searchVM.getSalary().equals("Tất cả") == false){
            String salaryRemovem = searchVM.getSalary().substring(0, searchVM.getSalary().length()-1);
            if(searchVM.getSalary().equals("dưới 10m") == false&&searchVM.getSalary().equals("trên 30m") == false){
                salary_min = salaryRemovem.split("m-")[0];
                salary_max = salaryRemovem.split("m-")[1];
            }

            if(searchVM.getSalary().equals("dưới 10m")){
                salary_min = "0";
                salary_max = "9";
            }
            if(searchVM.getSalary().equals("trên 30m")){
                salary_min = "31";
                salary_max = "10000";
            }
        }
        String experience = "";
        if(searchVM.getNumber_experience().equals("Tất cả") == false){
            experience = searchVM.getNumber_experience().split(" ")[0];
        }



//        String query = "select * from job where id = null";
//        if()


        String qMain = "select * from (job " +
                "JOIN academic_level " +
                "  ON job.ACADEMIC_LEVEL_ID = academic_level.id " +
                "JOIN working_form  " +
                "  ON job.WORKING_FORM_ID = WORKING_FORM.id) where skills like'%"+searchVM.getSkills()+"%' and (salary_min" +">= "+
                salary_min +" and salary_max"+" <="+salary_max+")" +
                "and address_work like '%"+searchVM.getAddress_work()+"%' and academic_level.description like '%"+
                searchVM.getAcademic_level()+"%' and working_form.description like '%"+ searchVM.getWorking_form()+"%'"+
                 " and number_experience like '%"+experience+"%'";



        String query = "select * from" +
                "(select rownum r,a.* from ("+qMain+")a) " +
                " where r >="+ ((page-1)*9+1)+" and r "+"<="+ (page*9);





















//        String query = "select * from" +
//                "(select rownum r,a.* from (select * from job where skills like'%"+searchVM.getSkills()+"%' and (salary_min" +">= "+ Long.parseLong(parts[0]) +" or salary_max"+" <="+Long.parseLong(parts[1])+")\n" +
//                "and address_work like '%"+searchVM.getAddress_work()+"%' and academic_level_id like '%"
//                +"(select academic_level.id from academic_level where academic_level.description like '%"+searchVM.getAcademic_level()+ "%')%'" +" and working_form_id like '%"+"(select working_form.id from working_form where working_form.description like '%"+searchVM.getWorking_form()+ "%')%'" +
//                " and number_experience like '%"+experience+"%')a) " +
//                " where r >="+ ((page-1)*9+1)+" and r "+"<="+ (page*9);






























        return getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(Job.class));
    }
    public int numberRecordSearch(SearchJobVM searchVM){
        String salary_min = "0";
        String salary_max = "10000";
        if(searchVM.getSkills().equals("Tất cả")){
            searchVM.setSkills("");
        }
        if(searchVM.getWorking_form().equals("Tất cả")){
            searchVM.setWorking_form("");
        }
        if(searchVM.getAddress_work().equals("Tất cả")){
            searchVM.setAddress_work("");
        }
        if(searchVM.getAcademic_level().equals("Tất cả")){
            searchVM.setAcademic_level("");
        }

        if(searchVM.getSalary().equals("Tất cả") == false){
            String salaryRemovem = searchVM.getSalary().substring(0, searchVM.getSalary().length()-1);
            if(searchVM.getSalary().equals("dưới 10m") == false&&searchVM.getSalary().equals("trên 30m") == false){
                salary_min = salaryRemovem.split("m-")[0];
                salary_max = salaryRemovem.split("m-")[1];
            }
            if(searchVM.getSalary().equals("dưới 10m")){
                salary_min = "0";
                salary_max = "9";
            }
            if(searchVM.getSalary().equals("trên 30m")){
                salary_min = "31";
                salary_max = "10000";
            }
        }
        String experience = "";
        if(searchVM.getNumber_experience().equals("Tất cả") == false){
            experience = searchVM.getNumber_experience().split(" ")[0];
        }
        String query = "select * from (job " +
                "JOIN academic_level " +
                "  ON job.ACADEMIC_LEVEL_ID = academic_level.id " +
                "JOIN working_form  " +
                "  ON job.WORKING_FORM_ID = WORKING_FORM.id) where skills like'%"+searchVM.getSkills()+"%' and (salary_min" +">= "+ salary_min +" and salary_max"+" <="+salary_max+")" +
                "and address_work like '%"+searchVM.getAddress_work()+"%' and academic_level.description like '%"+ searchVM.getAcademic_level()+"%' and working_form.description like '%"+ searchVM.getWorking_form()+"%'"+
                " and number_experience like '%"+experience+"%'";
        return getJdbcTemplate().query(query,new BeanPropertyRowMapper<>(Job.class)).size();
    }
    public AllJobDTO numberRecord(){
        String queryNewJob = " select * from job where  trunc(sysdate) - to_date(create_date, 'dd-mm-yy') <=7 ";
        String queryUrgentJob = "select * from job where  to_date(due_date, 'dd-mm-yy')- trunc(sysdate) <=7 and to_date(due_date, 'dd-mm-yy')- trunc(sysdate)>0 and qty_person >0";
        String queryHighPJob = "select * from job where salary_min > 18 and trunc(sysdate) - to_date(create_date, 'dd-mm-yy')>0 and qty_person >0";
        AllJobDTO allJobDTO = new AllJobDTO();
        allJobDTO.setNewJob(getJdbcTemplate().query(queryNewJob,new BeanPropertyRowMapper<>(Job.class)));
        allJobDTO.setUrgentJob(getJdbcTemplate().query(queryUrgentJob,new BeanPropertyRowMapper<>(Job.class)));
        allJobDTO.setHighPJob(getJdbcTemplate().query(queryHighPJob, new BeanPropertyRowMapper<>(Job.class)));
        System.out.println(allJobDTO);
        return allJobDTO;
    }
    public AllJobDTO getOnePageJobDTO(int page){
        String queryNewJob = " select* from (select rownum r,a.* from (select * from job where  trunc(sysdate) - to_date(create_date, 'dd-mm-yy') <=7)a) where r >= " + ((page-1)*9+1) +" and r <="+ (page*9);
        String queryUrgentJob = "select * from (select rownum r, a.* from (select * from job where  to_date(due_date, 'dd-mm-yy')- trunc(sysdate) <=7 and to_date(due_date, 'dd-mm-yy')- trunc(sysdate)>0 and qty_person >0)a) where r >="+((page-1)*9+1) +" and r <="+ (page*9);
        String queryHighPJob = "select * from (select rownum r,a.* from (select * from job where salary_min > 18 and trunc(sysdate) - to_date(create_date, 'dd-mm-yy')>0 and qty_person >0)a) where r >="+((page-1)*9+1) +" and r <="+ (page*9);
        AllJobDTO allJobDTO = new AllJobDTO();
        allJobDTO.setNewJob(getJdbcTemplate().query(queryNewJob,new BeanPropertyRowMapper<>(Job.class)));
        allJobDTO.setUrgentJob(getJdbcTemplate().query(queryUrgentJob,new BeanPropertyRowMapper<>(Job.class)));
        allJobDTO.setHighPJob(getJdbcTemplate().query(queryHighPJob, new BeanPropertyRowMapper<>(Job.class)));
        System.out.println(allJobDTO);
        return allJobDTO;
    }
}
