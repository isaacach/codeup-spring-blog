package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.Models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {}
