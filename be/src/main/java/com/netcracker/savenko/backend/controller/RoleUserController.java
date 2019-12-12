package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.RoleUserEntity;
import com.netcracker.savenko.backend.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/roleUser")
public class RoleUserController {

    private RoleUserService roleUserService;

    @Autowired
    public RoleUserController(RoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleUserEntity> getRoleUserById(@PathVariable(name = "id") Integer id) {
        Optional<RoleUserEntity> roleUser = roleUserService.getRoleUserById(id);
        if (roleUser.isPresent()) {
            return ResponseEntity.ok(roleUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<RoleUserEntity> getAllStatus() {
        return roleUserService.getAllRoleUser();
    }
}
