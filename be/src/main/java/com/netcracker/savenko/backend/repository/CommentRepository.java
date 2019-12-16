package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Integer> {
    @Query(value = "select * from comment_post where id_post=(?1)", nativeQuery = true)
    Page<CommentEntity> findAllByPostId(int postId, Pageable pageable);
}

