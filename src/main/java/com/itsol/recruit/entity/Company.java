package com.itsol.recruit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Company")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company  implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
    @SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "hotline")
    String hotline;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_incoporation")
    Date date_incoporation;

    @Column(name = "tax_code")
    String tax_code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "tax_date")
    Date tax_date;

    @Column(name = "tax_place")
    String tax_place;

    @Column(name = "head_office")
    String head_office;

    @Column(name = "number_staff")
    Long number_staff;
    @Column(name = "link_web")
    String link_web;
    @Column(name = "description")
    String description;
    @Column(name = "avatar")
    String avatar;
    @Column(name = "backdrop_img")
    String backdrop_img;
    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;

}

