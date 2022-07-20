package com.itsol.recruit.web.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class RegisterProfileVM {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date birthDay;
    int numberYearsExperience;
    String desiredSalary;
    String desiredWorkingAdress;
    String desiredWorkingForm;
}
