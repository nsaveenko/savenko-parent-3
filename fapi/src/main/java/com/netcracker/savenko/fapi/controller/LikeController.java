package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.Like;
import com.netcracker.savenko.fapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @RequestMapping
    public ResponseEntity<List<Like>> getAllLike() {
        return ResponseEntity.ok(likeService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Like> saveLike(@RequestBody Like like /*todo server validation*/) {
        if (like != null) {
            return ResponseEntity.ok(likeService.saveLike(like));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLike(@PathVariable String id) {
        likeService.deleteLike(Integer.valueOf(id));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable String id) throws InterruptedException {
        int likeId = Integer.valueOf(id);
        return ResponseEntity.ok(likeService.getLikeById(likeId));
    }
}
