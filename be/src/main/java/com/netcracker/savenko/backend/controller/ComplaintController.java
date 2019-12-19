package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.service.ComplaintService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaint/")
public class ComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<ComplaintEntity> getAllComplaint() {
        return complaintService.getAllComplaint();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ComplaintEntity> getComplaintById(@PathVariable(name = "id") Integer id) {
        Optional<ComplaintEntity> complaint = complaintService.getComplaintById(id);
        if (complaint.isPresent()) {
            return ResponseEntity.ok(complaint.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ComplaintEntity saveComplaint(@RequestBody ComplaintEntity complaint) {
        return complaintService.saveComplaint(complaint);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComplaint(@PathVariable(name = "id") Integer id) {
        complaintService.deleteComplaint(id);
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable(name = "id") Integer idPost) {
        complaintService.deletePost(idPost);
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public List<ComplaintEntity> getComplainByStatusId(@PathVariable(name = "id") int id) {
        return complaintService.getComplaintByStatusId(id);
    }

    @RequestMapping(value = "user/post/{id}")
    public Integer getUser(@PathVariable(name = "id") int id) {
        return complaintService.getUserId(id);
    }

    @RequestMapping(value = "statusComplaint/{id}", method = RequestMethod.GET)
    public String getStatusComplaintId(@PathVariable(name = "id") int id){
        return complaintService.getStatusComplaintId(id);
    }

    @RequestMapping(value = "username/{id}", method = RequestMethod.GET)
    public String getUsernameByComplaintId(@PathVariable(name = "id") int id){
        return complaintService.getUsernameByComplaintId(id);
    }

}
