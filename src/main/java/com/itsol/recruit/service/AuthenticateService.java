package com.itsol.recruit.service;


import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.web.vm.ChangePassVM;

public interface AuthenticateService {

    public String changePassword(ChangePassVM changePassVM);
}
