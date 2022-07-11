package com.itsol.recruit.service.filerecruit;

import com.itsol.recruit.dto.FileRecruitDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.Profiles;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.ProfilesRepository;
import com.itsol.recruit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileRecruitService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRegisterRepository jobRegister;

    public List<FileRecruitDTO> getAllFileRecruit() {
        return jobRegister.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

//    public String getReason(){
//        return
//    }
    public List<FileRecruitDTO> getAllFileRecruitSort() {
        return jobRegister.findAllOrderByDateAsc().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public List<FileRecruitDTO> findAllOrderByDateAsc(int id) {
        User user = userRepository.findById(id);
        return jobRegister.findAllOrderByDateAsc().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
    public List<FileRecruitDTO> findName(String name) {
        User user = userRepository.findByNameContaining(name);
        return jobRegister.findJobRegisterByUser(user).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public FileRecruitDTO convertEntityToDTO(JobRegister jobRegister) {
        FileRecruitDTO fileRecruitDTO = new FileRecruitDTO();
        fileRecruitDTO.setName(jobRegister.getUser().getName());
        fileRecruitDTO.setJobPosition(jobRegister.getJob().getJobPosition().getDescription());
        fileRecruitDTO.setDateRegister(jobRegister.getDateRegister());
        fileRecruitDTO.setCv(jobRegister.getCv());
        fileRecruitDTO.setStatus(jobRegister.getStatusJobRegister().getDescription());
        fileRecruitDTO.setReason(jobRegister.getReason());
        return fileRecruitDTO;
    }
}
