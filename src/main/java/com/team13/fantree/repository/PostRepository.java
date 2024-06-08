package com.team13.fantree.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team13.fantree.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findAllByOrderByCreateAtDesc(Pageable pageable);

	// List<Post> findByCreateAtBetween(LocalDate start, LocalDate end);

	List<Post> findByCreateAtBetween(String startDate, String endDate);
}
