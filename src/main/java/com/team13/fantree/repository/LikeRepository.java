package com.team13.fantree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team13.fantree.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
