package com.codeup.codeupspringblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeup.codeupspringblog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
