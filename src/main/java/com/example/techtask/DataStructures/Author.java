package com.example.techtask.DataStructures;


import lombok.Data;

@Data
public class Author {
    String id;
    String userName;
    String displayName;
    String profileUrl;
    String description;
    Influence influence;
    String likes;
    String followers;
    String posts;

    public Author(String id, String userName, String displayName,
                  String profileUrl, String description, Influence influence,
                  String likes, String followers, String posts) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.description = description;
        this.influence = influence;
        this.likes = likes;
        this.followers = followers;
        this.posts = posts;
    }
}
