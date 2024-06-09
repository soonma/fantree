package com.team13.fantree.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team13.fantree.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

	@Query(value = "SELECT * FROM post WHERE DATE_FORMAT(created_at, '%Y-%m-%d') BETWEEN :start AND :end", nativeQuery = true)
	List<Post> findByCustomCondition(String start, String end);
}
