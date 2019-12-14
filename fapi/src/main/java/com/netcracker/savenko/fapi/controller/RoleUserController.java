package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.RoleUser;
import com.netcracker.savenko.fapi.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roleUser")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @RequestMapping
    public ResponseEntity<List<RoleUser>> getAllRoleUser() {
        return ResponseEntity.ok(roleUserService.getAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<RoleUser> getAllRoleUser(@PathVariable int id) {
        return ResponseEntity.ok(roleUserService.getRoleUserById(id));
    }
}