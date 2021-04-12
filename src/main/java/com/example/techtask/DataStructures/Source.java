package com.example.techtask.DataStructures;


import lombok.Data;

@Data
public class Source {

    String id;
    String name;
    String mediaType1;

    public Source(String id, String name, String mediaType1) {
        this.id = id;
        this.name = name;
        this.mediaType1 = mediaType1;
    }
}
