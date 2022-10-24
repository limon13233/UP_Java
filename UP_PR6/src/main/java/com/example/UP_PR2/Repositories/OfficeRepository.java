package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Office;
import com.example.UP_PR2.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface OfficeRepository extends CrudRepository<Office, Long> {

    Office findByaddress(String Address);
    public Office findByid(String id);
}
