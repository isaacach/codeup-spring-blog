package com.codeup.codeupspringblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeup.codeupspringblog.models.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    Post findById(long id);

}
