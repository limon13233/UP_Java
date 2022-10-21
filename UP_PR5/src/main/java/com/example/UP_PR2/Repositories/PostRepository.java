package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.DopInfo;
import com.example.UP_PR2.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Post findBypostname(String PostName);
}
