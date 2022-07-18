package com.itsol.recruit.web.admin;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.JobPosition;
import com.itsol.recruit.entity.Rank;
import com.itsol.recruit.service.JobPositionService;
import com.itsol.recruit.service.RankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class RankController {
    public final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping(value = "/rank")
    public ResponseEntity<List<Rank>> getAllRank(){
        return  ResponseEntity.ok().body( rankService.getAllRank());
    }

    @GetMapping(value = "/rank/{id}")
    public ResponseEntity<Rank> findRankById(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(rankService.findById(id));
    }
}
