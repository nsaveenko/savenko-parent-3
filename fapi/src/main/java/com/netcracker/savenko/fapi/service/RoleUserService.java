package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.RoleUser;

import java.util.List;

public interface RoleUserService {
    List<RoleUser> getAll();
    RoleUser getRoleUserById(Integer id);
}
