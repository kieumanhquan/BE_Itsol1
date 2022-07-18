//package com.itsol.recruit.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="image")
//public class Image {
//    @Id
//    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_SEQ")
//    @SequenceGenerator(name = "IMAGE_SEQ", sequenceName = "IMAGE_SEQ", allocationSize = 1, initialValue = 1)
//    private Long id;
//
//    private Long userId;
//    @Column(name= "name")
//    private String name;
//
//    @Column(name = "type")
//    private String types;
//
//    @Column(name = "image", unique = false, nullable = false, length = 100000)
//    private byte[] image;
//}
