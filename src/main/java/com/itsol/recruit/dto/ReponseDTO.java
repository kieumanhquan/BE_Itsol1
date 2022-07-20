package com.itsol.recruit.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ReponseDTO {
    HttpStatus status;
    String code;
    public ReponseDTO(String code){
        this.code=code;
    }

    public ReponseDTO(HttpStatus status,String code){
        this.status=status;
        this.code=code;
    }
}
