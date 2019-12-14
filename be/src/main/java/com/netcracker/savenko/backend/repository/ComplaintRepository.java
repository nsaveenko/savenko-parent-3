package com.netcracker.savenko.backend.repository;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ComplaintRepository extends CrudRepository<ComplaintEntity, Integer>  {
    @Query(value = "select complaint.* from complaint where id_status_complaint = (?1) " +
            "order by complaint.date_complaimnt desc", nativeQuery = true)
    List<ComplaintEntity> getComplaintByStatusId(int id);
}
