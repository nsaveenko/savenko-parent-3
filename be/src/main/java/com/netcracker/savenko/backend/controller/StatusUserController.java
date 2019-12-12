package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.StatusUserEntity;
import com.netcracker.savenko.backend.service.StatusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/statusUser")
public class StatusUserController {

    private StatusUserService statusUserService;

    @Autowired
    public StatusUserController(StatusUserService statusUserService) {
        this.statusUserService = statusUserService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StatusUserEntity> getStatusUserById(@PathVariable(name = "id") Integer id) {
        Optional<StatusUserEntity> statusUser = statusUserService.getStatusUserById(id);
        if (statusUser.isPresent()) {
            return ResponseEntity.ok(statusUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<StatusUserEntity> getAllStatus() {
        return statusUserService.getAllStatusUser();
    }
}
