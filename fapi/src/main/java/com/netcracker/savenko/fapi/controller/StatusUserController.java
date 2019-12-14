package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.StatusUser;
import com.netcracker.savenko.fapi.service.StatusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statusUser")
public class StatusUserController {
    @Autowired
    private StatusUserService statusUserService;

    @RequestMapping
    public ResponseEntity<List<StatusUser>> getAllStatusUser() {
        return ResponseEntity.ok(statusUserService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<StatusUser> getAllStatusUser(@PathVariable int id) {
        return ResponseEntity.ok(statusUserService.getStatusUserById(id));
    }
}