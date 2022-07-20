package com.itsol.recruit.service;

import com.itsol.recruit.dto.DetailFileDTO;
import org.springframework.stereotype.Service;

public interface DetailFileService {
    public DetailFileDTO getDetailFile(long id);
}
