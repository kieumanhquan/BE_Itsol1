package com.itsol.recruit.web.user;

import com.itsol.recruit.core.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.Api.Path.ADMIN)
public class AdminController {
    @GetMapping("helloAdmin")
    public String helloAdmin(){
        return "hello admin";
    }

}
