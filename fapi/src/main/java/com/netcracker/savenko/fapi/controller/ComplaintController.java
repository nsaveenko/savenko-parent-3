package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.Complaint;
import com.netcracker.savenko.fapi.service.ComplaintService;
import com.netcracker.savenko.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Complaint> saveComplaint(@RequestBody Complaint complaint /*todo server validation*/) {
        if (complaint != null) {
            return ResponseEntity.ok(complaintService.saveComplaint(complaint));
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        return ResponseEntity.ok(complaintService.getAll());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComplaint(@PathVariable String id) {
        complaintService.deleteComplaint(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String idPost) {
        complaintService.deletePost(Integer.valueOf(idPost));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable String id) throws InterruptedException {
        int complaintId = Integer.valueOf(id);
        return ResponseEntity.ok(complaintService.getComplaintById(complaintId));
    }
}
