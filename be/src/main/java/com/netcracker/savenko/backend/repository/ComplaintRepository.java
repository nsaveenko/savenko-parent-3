package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComplaintRepository extends CrudRepository<ComplaintEntity, Integer>  {
    //ComplaintEntity[] FindComplaintByPostId(Integer idPost);
}
