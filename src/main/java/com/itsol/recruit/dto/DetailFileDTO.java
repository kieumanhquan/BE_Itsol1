package com.itsol.recruit.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class DetailFileDTO {
    long id;

    String numberExperience;

    String addressWork;

    Long qtyPerson;

    String skills;

    String description;

    String jobRequirement;

    Long salaryMax;

    Long salaryMin;



    long views;

    String nameJob;

    String name;


    String email;

    String phoneNumber;

    String homeTown;


    String gender;

    String status;
    String reason;
}
