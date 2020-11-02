package com.web.blog.dao.admin;




import java.time.LocalDateTime;

import com.web.blog.model.visit.Visit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Visit,Integer> {

    Integer countByCurrenttimeBetween(LocalDateTime start, LocalDateTime end);
    
}
