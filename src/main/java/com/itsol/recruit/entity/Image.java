package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image" , unique = false, nullable = false, length = 100000)
    private byte[] image;

    private Long userId;

}
