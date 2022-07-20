package com.itsol.recruit.web.user.auth;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.ResponseDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.*;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.security.jwt.JWTFilter;
import com.itsol.recruit.security.jwt.TokenProvider;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.service.emailRegister.EmailService;
import com.itsol.recruit.service.impl.AuthenticateServiceImpl;
import com.itsol.recruit.service.impl.ImageServiceImp;
import com.itsol.recruit.service.impl.ProfilesImp;
import com.itsol.recruit.service.impl.UserServiceImpl;
//import com.itsol.recruit.service.jobregister.email.EmailService;
import com.itsol.recruit.service.mapper.OTPService;
import com.itsol.recruit.service.mapper.UserMapper;
import com.itsol.recruit.web.vm.LoginVM;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateController {
    UserServiceImpl userServiceImpl;
    ImageServiceImp imageServiceImp;
    UserRepository userRepository;
    ProfilesImp profilesImp;

     private final AuthenticateService authenticateService;

    private final AuthenticateServiceImpl authenticateServiceImpl;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final OTPService otpService;
    private final EmailService emailService;
    private final UserMapper userMapper;

    public AuthenticateController(AuthenticateService authenticateService, ImageServiceImp imageServiceImp, UserServiceImpl userServiceImpl, UserRepository userRepository, ProfilesImp profilesImp,
    AuthenticateServiceImpl authenticateServiceImpl, AuthenticationManagerBuilder authenticationManagerBuilder,
    UserService userService, TokenProvider tokenProvider, OTPService otpService, EmailService emailService, UserMapper userMapper) {
        this.userServiceImpl = userServiceImpl;
        this.imageServiceImp = imageServiceImp;
        this.userRepository = userRepository;
        this.profilesImp = profilesImp;


        this.authenticateServiceImpl = authenticateServiceImpl;
        this.authenticateService = authenticateService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.otpService = otpService;
        this.emailService = emailService;
        this.userMapper = userMapper;
    }

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
                    new ResponseDTO(HttpStatus.BAD_REQUEST, "NOT_FOUND"));
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

//    @PostMapping("/change-password")
//    public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePassVM changePassVM) {
//        return ResponseEntity.ok().body(Collections.singletonMap("change", authenticateServiceImpl.changePassword(changePassVM)));
//    }

    @GetMapping("/je")
    public ResponseEntity<List<User>> getJE () {
        List<User> users = userRepository.getJE();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/je/sortByName")
    public ResponseEntity<List<User>> getJEByName () {
        List<User> users = userRepository.getJESortByName();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userEdit){
        User user = userService.findById(id);
        if(user != null){
            user.setUserName(userEdit.getUserName());
//            user.setAvatarName(userEdit.getAvatarName());
            user.setEmail(userEdit.getEmail());
            user.setBirthDay(userEdit.getBirthDay());
            user.setHomeTown(userEdit.getHomeTown());
            user.setGender(userEdit.getGender());
            user.setPhoneNumber(userEdit.getPhoneNumber());
        }
        User update = userRepository.save(user);
        return ResponseEntity.ok(update);
    }

//    @PutMapping("/updateActive/{id}")
//    public ResponseEntity<User> active(@PathVariable Long id, @RequestBody User UserActive){
//        User user = userService.findById(id);
//    }
    @GetMapping("/getuser/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name){
        User user = userService.findUserByUserName(name);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @GetMapping("/pageje")
    public ResponseEntity<List<User>> getAllJe(@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize ,   @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "type", required = false) boolean type ) {
        Page<User> page = userServiceImpl.getAllJe(pageNo, pageSize, sort, type);
        return ResponseEntity.ok().body(page.getContent());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/deactive/{id}")
    public ResponseEntity<User> Deactive(@PathVariable("id") Long id){
        User user = userService.deactive(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signupJe")
    public ResponseEntity<?> signupJe(@Valid @RequestBody User user) {

        Set<Role> roles = userService.findByCode(Constants.Role.JE);
        user.setDelete(false);
        user.setActive(true);
        user.setRoles(roles);

//        if (userService.findUserByEmail(user.getEmail()) != null) {
//            System.out.println("mail trungf");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        } else if (userService.findUserByPhone(user.getPhoneNumber()) != null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//
//        } else if (userService.findUserByUserName(user.getUserName()) != null) {
//            System.out.println("user name trùng");
//            return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
//        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enCryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(enCryptPassword);
        userService.save(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

   @PostMapping("/upload/image")
    public ResponseEntity<ResponseDTO> upload(@RequestParam("image") MultipartFile file){
        try{
            ResponseDTO responseDTO = imageServiceImp.upload(file);
            responseDTO.setStatus(HttpStatus.OK);
            return ResponseEntity.ok().body(responseDTO);
        } catch (IOException e) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NOT_FOUND, "Not Found"));
        }
   }

   @GetMapping("/image/{name}")
    public ResponseEntity<Image> getImage(@PathVariable("name") String name){
       try {
           return ResponseEntity.ok().body(imageServiceImp.getImageByName(name));
       } catch (IOException e) {
           throw new RuntimeException(e);

       }
   }
   @GetMapping("/profile")
    public ResponseEntity<Profiles> getProfile(@PathVariable("id") Long id){
        Profiles profiles = profilesImp.findProfindByUser(id);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
   }

}
