package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.ComplaintEntity;
import com.netcracker.savenko.backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
