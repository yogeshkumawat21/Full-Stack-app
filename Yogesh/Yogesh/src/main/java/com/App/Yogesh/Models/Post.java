package com.App.Yogesh.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Post {

    public Post(Integer id, String video, String caption, User user, LocalDateTime createdAt) {
        super();
        this.id = id;
        this.video = video;
        this.image= image;
        Caption = caption;
        this.user = user;
        this.createdAt = createdAt;
    }

    public  Post(){};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String image;
    private String Caption;
    private String video ;
    private  User user ;
    private LocalDateTime createdAt;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        image = image;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
