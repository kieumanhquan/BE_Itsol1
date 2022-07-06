package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OTPRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.mapper.EmailService;
import com.itsol.recruit.service.mapper.OTPService;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {
    private final UserRepository userRepository;

    private final OTPRepository otpRepository;

    private final EmailServiceImpl emailServiceImpl;

    public OTPServiceImpl(UserRepository userRepository, OTPRepository otpRepository, EmailServiceImpl emailServiceImpl) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.emailServiceImpl = emailServiceImpl;
    }

    @Override
    public String sendOTP(String email) {
        try {
            User user = userRepository.findUserByEmail(email);
            if (user == null) {
                return "Không tìm thấy email";
            }

            OTP otp = new OTP(user);
            OTP oldOTP = otpRepository.findByUser(user);
            if (oldOTP != null) {
                oldOTP.setCode(otp.getCode());
                oldOTP.setIssueAt(otp.getIssueAt());
                otpRepository.save(oldOTP);
            } else
                otpRepository.save(otp);
            String emails = emailServiceImpl.buildOtpEmail(user.getName(), otp.getCode());
            emailServiceImpl.sendEmail(user.getEmail(), emails);
            return "Gửi mã OTP đến mail thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Gửi mail không thành công";
        }

    }
}