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

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Complaint>> getComplaintByStatusId(@PathVariable int id){
        return ResponseEntity.ok(complaintService.getComplaintByStatusId(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComplaint(@PathVariable int id) {
        complaintService.deleteComplaint(id);
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        complaintService.deletePost(id);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable int id) {
        return ResponseEntity.ok(complaintService.getComplaintById(id));
    }
}
