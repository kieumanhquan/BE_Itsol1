package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.web.vm.SendJobRegisVm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
    private final JobRegisterRepository jobRegisterRepository;
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JobRegisterRepository jobRegisterRepository, JobRegisterRepository jobRegisterRepository1, UserRepository userRepository, JavaMailSender javaMailSender) {
        this.jobRegisterRepository = jobRegisterRepository1;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    // Đinh Xuân Lộc
    public boolean sendEmail(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setSubject("Confirm OTP");
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailJobRegister(Long id, String email) {
       JobRegister jobRegister = jobRegisterRepository.findJobRegisterById(id);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(jobRegister.getUser().getEmail());
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setSubject("Kết quả phỏng vấn");
            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String buildJobRegisterEmail(String name, SendJobRegisVm sendJobRegisVm) {
        return "Dear anh/chị ," +name +
                "\n" +
                "<br> Công ty ITSOL rất vui và vinh hạnh khi nhận được hồ sơ ứng tuyển của anh/chị vào vị trí ABC. Chúng tôi đã nhận được CV của anh/chị và mong muốn có một cuộc phỏng vấn để trao đổi trực tiếp về kiến thức cũng như công việc mà anh/chị đã ứng tuyển.\n" +
                "\n" +
                "<br>Thời gian phỏng vấn dự kiến vào lúc" + sendJobRegisVm.getTimeInterview()+", "+sendJobRegisVm.getDateInterview()+ "qua công cụ " + sendJobRegisVm.getMediaType()+" (chúng tôi sẽ gửi lại link sau khi anh/chị xác nhận đồng ý phỏng vấn bằng các reply lại mail này).\n" +
                "\n" +
                "<br>Chúng tôi rất hy vọng anh/chị sớm phản hồi và mong rằng chúng ta sẽ được hợp tác cùng nhau trong tương lai.\n" +
                "\n" +
                "<br>Mọi thắc mắc xin vui lòng liên hệ tới anh Nguyen Van A, SĐT: 0912345678 trong giờ hành chính để được giải đáp.\n" +
                "\n" +
                "<br>Thanks & best regards,\n" +
                "<br>ITSOL JSC\n" +
                "<br>Head office: Tầng 3, tòa nhà 3A, ngõ 82, phố Duy Tân, phường Dịch Vọng Hậu, quận Cầu Giấy, Hà Nội\n" +
                "<br>Hotline: 0123456789\n" +
                "\n" +
                "<br>Với các giá trị như X, vị trí ABC, thời gian dự kiến, liên hệ Nguyen Van A, … là các giá trị được quản trị viên nhập khi click button Đặt lịch phỏng vấn và là thông tin JE tạo job tương ứng.\n" +
                "<br>Tuyển thành công: button này chỉ xuất hiện khi trạng thái của hồ sơ là “Đang phỏng vấn”. Khi click button này trạng thái của hồ sơ sẽ chuyển từ “Đang phỏng vấn” thành “Đã tuyển” và số lượng người cần tuyển của công việc đang tuyển sẽ được tự động trừ đi 1.\n";
    }

    public String buildOtpEmail(String name, String otp) {
        return "<p>Xin chào " + name + ".Nhập mã OTP như dưới đây dể đổi mật khẩu </p>"
                + "<br>" + "<h3>" + otp + "</h3>" + "<br>"
                + "<p>Mã OTP này sẽ hết hạn trong 5 phút</p>";

    }
}




