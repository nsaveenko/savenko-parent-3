package com.netcracker.savenko.fapi.controller;

import com.netcracker.savenko.fapi.models.Post;
import com.netcracker.savenko.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postService.getAll());
    }

    @RequestMapping(value = "/followers/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostBySub(@PathVariable int id){
        return ResponseEntity.ok(postService.getPostBySub(id));
    }

    @RequestMapping(value = "/currUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostByCurrUser(@PathVariable int id){
        return ResponseEntity.ok(postService.getPostByCurrUser(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> savePost(@RequestBody Post post /*todo server validation*/) {
        if (post != null) {
            return ResponseEntity.ok(postService.savePost(post));
        }
        return null;
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public ResponseEntity<Post> saveFile(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {

        Post post= postService.getPostById(id);

        String fileExtension = (file.getOriginalFilename()).split("\\.")[1];
        String fileName = "image_" + id + "." + fileExtension;
        String filePath = "/Users/anastasiasavenko/Documents/nc/savenko-parent/fapi/image/" + fileName;

        File dest = new File(filePath);
        file.transferTo(dest);
        post.setFilePath(fileName);

        Post savedPost = postService.savePost(post);

        return ResponseEntity.ok(savedPost);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}