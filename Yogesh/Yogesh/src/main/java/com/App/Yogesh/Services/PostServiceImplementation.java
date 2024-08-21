package com.App.Yogesh.Services;

import com.App.Yogesh.Models.Post;
import com.App.Yogesh.Models.User;
import com.App.Yogesh.Repository.PostRepository;
import com.App.Yogesh.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class  PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;
   @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        User user = userService.findUserById(userId);
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
       // newPost.setCreatedAt(new LocalDateTime.now();
        newPost.setUser(user);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(post.getUser().getId()!=user.getId())
        {
           throw new Exception("you can't delete another users post ");
        }
        postRepository.delete(post);
        return "post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);

    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty())
        {
            throw new Exception("post not found with id "+postId);
        }
        return  opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPost().contains(post))
        {
            user.getSavedPost().remove(post);
        }
        else
        {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws  Exception{
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        post.getLiked().add(user);


        if(post.getLiked().contains(user))
        {
            post.getLiked().remove(user);
        }
        else
        {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
