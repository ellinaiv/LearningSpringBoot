package com.example.techtask.DataStructures;

import lombok.Data;

@Data
public class Mention {
    String id;

    public Mention(String id) {
        this.id = id;
    }
}
