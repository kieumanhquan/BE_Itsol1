package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.ResponseDTO;
import com.itsol.recruit.entity.Image;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.ImageRepository;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class ImageServiceImp {
    public ImageRepository imageRepository;
    @Autowired
    public UserService userService;
    public ImageServiceImp (ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public ResponseDTO upload(MultipartFile file) throws IOException{
        Image image = Image.builder().name(file.getOriginalFilename()).type(file.getContentType()).image(ImageUtil.compress(file.getBytes())).build();
        imageRepository.save(image);
        return new ResponseDTO("upload thanh cong");
    }

    public Image getImageByName(String name) throws IOException{
        Image image = imageRepository.findImageByName(name);
        System.out.println(image);
        image.setImage(ImageUtil.decompress(image.getImage()));
        return image;
    }
}
