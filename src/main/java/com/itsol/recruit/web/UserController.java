package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class UserController {

   public final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAllUser(){
        return  ResponseEntity.ok().body( userService.getAllUser());
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body( userService.findById(id));
    }
    @GetMapping(value = "/active_account/{id}")
    public ResponseEntity<?> activeAccount(@PathVariable("id") Long id){
        try{
            userService.activeAccount(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }

    }
}
