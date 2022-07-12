package com.itsol.recruit.web.auth;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.ResponseDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.security.jwt.JWTFilter;
import com.itsol.recruit.security.jwt.TokenProvider;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.service.jobregister.email.EmailService;
import com.itsol.recruit.service.mapper.OTPService;
import com.itsol.recruit.service.mapper.UserMapper;
import com.itsol.recruit.web.vm.ChangePassVM;
import com.itsol.recruit.web.vm.LoginVM;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateController {

    AuthenticationManagerBuilder authenticationManagerBuilder;
    UserService userService ;
    EmailService emailService;
    TokenProvider tokenProvider;
    RoleRepository roleRepository;
    UserMapper userMapper;
    UserRepository userRepository;
    OTPService otpService;
    PasswordEncoder passwordEncoder;
    AuthenticateService authenticateService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTO dto) {

        Set<Role> roles = userService.findByCode(Constants.Role.USER);
        User user = userMapper.toEntity(dto);
        user.setDelete(false);
        user.setActive(false);
        user.setActive(false);
        user.setDelete(false);
        user.setRoles(roles);

        if (userService.findUserByEmail(user.getEmail()) != null) {
            System.out.println("mail trungf");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (userService.findUserByPhone(user.getPhoneNumber()) != null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } else if (userService.findUserByUserName(user.getUserName()) != null) {
            System.out.println("user name trùng");
            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enCryptPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(enCryptPassword);
        userService.save(user);
        String url = "http://localhost:4200/public/active_account/" + user.getId();
        String urlLink = emailService.buildActiveLink(url);
        emailService.sendEmail(user.getEmail(), urlLink);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    /*
    Login api
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginVM loginVM) {
//		Tạo chuỗi authentication từ username và password (object LoginRequest
//		- file này chỉ là 1 class bình thường, chứa 2 trường username và password)
        if (userService.findUserByUserName(loginVM.getUserName()) == null) {
            return ResponseEntity.ok().body(
                    new ResponseDTO(HttpStatus.NOT_FOUND, "NOT_FOUND"));
        }
        UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                loginVM.getUserName(),
                loginVM.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
        return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Trả về chuỗi jwt(authentication string)

//        User userLogin = userService.findUserByUserName(adminLoginVM.getUserName());
//        return ResponseEntity.ok().body(new JWTTokenResponse(jwt, userLogin.getUserName())); //Trả về chuỗi jwt(authentication string)

    }

    @PostMapping("/send-otp")
    public ResponseEntity<Object> sendOtpEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.ok().body(Collections.singletonMap("message", otpService.sendOTP(email)));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePassVM changePassVM) {
        return ResponseEntity.ok().body(Collections.singletonMap("change", authenticateService.changePassword(changePassVM)));
    }

    @GetMapping("/je")
    public ResponseEntity<List<User>> getJE () {
        List<User> users = userService.getJE();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/je/sortByName")
    public ResponseEntity<List<User>> getJEByName () {
        List<User> users = userRepository.getJESortByName();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User update = userService.updateUser(user);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }
    @GetMapping("/getuser/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name){
        User user = userService.findUserByUserName(name);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }


}
