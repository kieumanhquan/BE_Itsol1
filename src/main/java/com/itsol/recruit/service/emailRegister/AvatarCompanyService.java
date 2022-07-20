package com.itsol.recruit.service.emailRegister;

import com.itsol.recruit.dto.ResponseDTO;
import com.itsol.recruit.entity.CompanyAvatar;
import com.itsol.recruit.repository.AvatarCompanyRepository;
import com.itsol.recruit.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class AvatarCompanyService {
    private final AvatarCompanyRepository avatarCompanyRepository;

    public AvatarCompanyService(AvatarCompanyRepository avatarCompanyRepository) {
        this.avatarCompanyRepository = avatarCompanyRepository;
    }

    public ResponseDTO uploadImage(MultipartFile file) throws IOException {
        CompanyAvatar image = CompanyAvatar.builder().name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtils.compressImage(file.getBytes())).build()
                ;
        image.setId(1L);
        avatarCompanyRepository.save(image);
        return new ResponseDTO("Upload image success");
    }

    public CompanyAvatar findCompanyAvatarsById(Long id) throws IOException{
        CompanyAvatar image=avatarCompanyRepository.findCompanyAvatarsById(id);

        image.setImage(ImageUtils.decompressImage(image.getImage()));
        return image;
    }
}
