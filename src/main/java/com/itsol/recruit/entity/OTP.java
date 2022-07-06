package com.itsol.recruit.entity;

import com.itsol.recruit.core.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity(name = "OTP")
@Data
public class OTP implements Serializable {
    private final static Long EXPIRED_TIME = Constants.OTP.EXPIRED_TIME;
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_OTP_ID")
    @SequenceGenerator(name = "GEN_OTP_ID", sequenceName = "SEQ_OTP", allocationSize = 1)
    private int id;

    private String code;

    @Column(name = "issue_at")
    private Date issueAt;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public OTP(User user){
        this.user=user;
        Random random=new Random();
        int number=100000+random.nextInt(900000);
        this.code=String.valueOf(number);
        this.issueAt=new Date();
    }
    public OTP(){
        Random random=new Random();
        int number=100000+random.nextInt(900000);
        this.code=String.valueOf(number);
        this.issueAt=new Date();
    }
    public boolean expired(){
        return this.issueAt.getTime()+EXPIRED_TIME<new Date().getTime();
    }
}
