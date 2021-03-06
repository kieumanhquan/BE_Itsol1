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
    public User signup(UserDTO dto) {
        try {
            Set<Role> roles = roleRepository.findByCode(Constants.Role.USER);
            User user = userMapper.toEntity(dto);
            user.setDelete(false);
            user.setActive(false);
            user.setActive(false);
            user.setDelete(false);
            user.setRoles(roles);
//
            userRepository.save(user);
//        OTP otp = userService.generateOTP(user);
//        String linkActive = accountActivationConfig.getActivateUrl() + user.getId();
//        emailService.sendSimpleMessage(user.getEmail(),
//                "Link active account",
//                "<a href=\" " + linkActive + "\">Click v??o ????y ????? k??ch ho???t t??i kho???n</a>");
            return user;
        } catch (Exception e) {
            log.error("cannot save to database");
            return null;
        }

    }

    @Override
    public String forgotPassword(String code, UserDTO userDto) {
        String message;
        User user = userRepository.findUserByEmail(userDto.getEmail());
        if (user != null) {

            OTP otp = otpRepository.findByUser(user);
            if (otp.expired()) {
                message = "M?? otp ???? h???t h???n";
            } else if (otp.getCode().equals(code)) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                userRepository.save(user);
                message = "?????i m???t kh???u th??nh c??ng";
            } else {
                message = "M?? otp kh??ng ch??nh x??c";
            }
        } else {
            message = "Email kh??ng t???n t???i";
        }
        return message;

    }


}
