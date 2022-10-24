package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.DopInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DopInfoRepository extends CrudRepository<DopInfo, Long> {

    DopInfo findByemail(String email);
}
