package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Rank;
import com.itsol.recruit.repository.RankRepository;
import com.itsol.recruit.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;

    @Override
    public List<Rank> getAllRank() {
        return rankRepository.findAll();
    }

    @Override
    public Rank findById(Long id) {
        return rankRepository.findById(id).get();
    }
}
