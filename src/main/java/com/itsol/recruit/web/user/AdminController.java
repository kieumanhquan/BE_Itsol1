package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.ADMIN)
public class AdminController {
    UserService userService;
    @GetMapping("helloAdmin")
    public String helloAdmin(){
        return "hello admin";
    }




}
