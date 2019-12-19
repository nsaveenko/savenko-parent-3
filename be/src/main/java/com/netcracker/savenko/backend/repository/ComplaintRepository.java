package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

@Repository
public interface ComplaintRepository extends CrudRepository<ComplaintEntity, Integer>  {
    @Query(value = "select complaint.* from complaint where id_status_complaint = (?1) " +
            "order by complaint.date_complaimnt desc", nativeQuery = true)
    List<ComplaintEntity> getComplaintByStatusId(int id);

//    @Query(value = "select user.* from complaint " +
////            "join post on complaint.id_post = post.id " +
////            "join user on post.id_user = user.id " +
////            "where complaint.id_post=(?1)", nativeQuery = true)
////    List<UserEntity> getUserByPostIdAndComplaintId(int id);

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


}