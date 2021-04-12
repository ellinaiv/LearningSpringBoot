package com.example.techtask.DataStructures;


import lombok.Data;

@Data
public class Url {

    String displayUrl;
    String expandedUrl;

    public Url(String displayUrl, String expandedUrl) {
        this.displayUrl = displayUrl;
        this.expandedUrl = expandedUrl;
    }
}
