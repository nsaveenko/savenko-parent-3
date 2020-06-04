package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface ComplaintRepository extends PagingAndSortingRepository<ComplaintEntity, Integer> {

    @Query(value = "select * from complaint where id_status_complaint = (?1) " +
            "order by complaint.date_complaimnt desc", nativeQuery = true)
    Page<ComplaintEntity> getComplaintByStatusId(int id, Pageable pageable);

    @Query(value = "select user.id from complaint " +
            "join post on complaint.id_post = post.id " +
            "join user on post.id_user = user.id " +
            "where complaint.id_post=(?1)", nativeQuery = true)
    Integer getUserIdByPostIdAndComplaintId(int id);

    @Query(value = "select status_complaint.status_complaint from complaint " +
            "join status_complaint on complaint.id_status_complaint = status_complaint.id " +
            "where complaint.id = (?1)", nativeQuery = true)
    String getStatusComplaintByComplaintId(int id);

    @Query(value = "select user.username from complaint " +
            "join user on complaint.id_user = user.id " +
            "where complaint.id = (?1)", nativeQuery = true)
    String getUsernameByComplaintId(int id);

    @Query(value = "select complaint.* from complaint where id_post=(?1); ", nativeQuery = true)
    List<ComplaintEntity> getComplaintOnPost(int id);

    @Query(value = "select post.* from post join complaint on complaint.id_post = post.id " +
            "where complaint.id_post=(:postId) and complaint.id=(:complaintId)", nativeQuery = true)
    PostEntity getPostByPostIdAndComplaintId(int postId, int complaintId);

}