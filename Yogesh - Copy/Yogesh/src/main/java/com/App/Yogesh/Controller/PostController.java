package com.App.Yogesh.Controller;

import com.App.Yogesh.Models.Post;
import com.App.Yogesh.Response.ApiResponse;
import com.App.Yogesh.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Creates a new post for a specific user.
     */
    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    /**
     * Deletes a specific post for a user.
     */
    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse response = new ApiResponse(message, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a post by its ID.
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves all posts made by a specific user.
     */
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Retrieves all posts.
     */
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Saves a post for a specific user (e.g., bookmark or favorite).
     */
    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    /**
     * Likes or unlikes a post for a specific user.
     */
    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
