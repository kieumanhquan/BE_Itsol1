package com.itsol.recruit.web.vm;

import com.itsol.recruit.core.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class Hello {
    @GetMapping("hello")
     public String HelloWorld(){
         return "hello";
     }
}
