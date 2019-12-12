package com.netcracker.savenko.fapi.service;

import com.netcracker.savenko.fapi.models.StatusUser;

import java.util.List;

public interface StatusUserService {
    List<StatusUser> getAll();
    StatusUser getStatusUserById(Integer id);
}
