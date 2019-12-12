package com.netcracker.savenko.backend.controller;

import com.netcracker.savenko.backend.entity.LikePostEntity;
import com.netcracker.savenko.backend.entity.UserEntity;
import com.netcracker.savenko.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LikePostEntity> getLikeById(@PathVariable(name = "id") Integer id) {
        Optional<LikePostEntity> like = likeService.getLikeById(id);
        if (like.isPresent()) {
            return ResponseEntity.ok(like.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "userByIdUser") Integer id) {
        Optional<UserEntity> user = likeService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<LikePostEntity> getAllLike() {
        return likeService.getAllLike();
    }

    @RequestMapping(method = RequestMethod.POST)
    public LikePostEntity saveLike(@RequestBody LikePostEntity like) {
        return likeService.saveLike(like);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLike(@PathVariable(name = "id") Integer id) {
        likeService.deleteLike(id);
    }
}
