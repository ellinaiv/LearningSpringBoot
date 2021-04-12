package com.example.techtask.DataStructures;

import lombok.Data;

@Data
public class Attachment {

    String type;
    String displayUrl;
    String title;
    String description;
    String expandedUrl;

    public Attachment(String type, String displayUrl, String title, String description, String expandedUrl) {
        this.type = type;
        this.displayUrl = displayUrl;
        this.title = title;
        this.description = description;
        this.expandedUrl = expandedUrl;
    }
}
