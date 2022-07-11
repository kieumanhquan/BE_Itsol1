package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.AuthenticateRepository;
import com.itsol.recruit.repository.OTPRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.mapper.UserMapper;
import com.itsol.recruit.web.vm.ChangePassVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    public final AuthenticateRepository authenticateRepository;

    public final UserMapper userMapper;

    public final RoleRepository roleRepository;

    public final UserRepository userRepository;
    public final OTPRepository otpRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(AuthenticateRepository authenticateRepository, UserMapper userMapper, RoleRepository roleRepository, UserRepository userRepository, OTPRepository otpRepository, PasswordEncoder passwordEncoder) {
        this.authenticateRepository = authenticateRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public String changePassword(ChangePassVM changePassVM) {
        String notfound = "notfound";
        String success = "success";
        String expired = "exprired";
        String fail = "fail";
        User user = userRepository.findUserByEmail(changePassVM.getEmail());
        if (!changePassVM.getPassword().matches("(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!#^~%*?&,.<>\"'\\;:{\\}\\[\\]\\|\\+\\-\\=\\_\\)\\(\\)\\`\\/\\\\\\]])[A-Za-z0-9d$@].{7,}")) {
            return notfound;
        }
        if (user == null) {
            return expired;
        } else {
            OTP optdb = otpRepository.findByUser(user);
            if (optdb.expired()) {
                return expired;
            } else if (!optdb.getCode().equals(changePassVM.getCode())) {
                return fail;
            } else if (optdb.getCode().equals(changePassVM.getCode())) {
                user.setPassword(passwordEncoder.encode(changePassVM.getPassword()));
                userRepository.save(user);
                return success;
            } else {
                return expired;
            }
        }
    }

}