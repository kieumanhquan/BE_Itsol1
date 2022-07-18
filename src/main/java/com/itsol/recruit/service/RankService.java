package com.itsol.recruit.service;

import com.itsol.recruit.entity.Rank;

import java.util.List;

public interface RankService {
    public List<Rank> getAllRank();

    public Rank findById(Long id);
}
