package com.example.techtask.DataStructures;

import lombok.Data;

@Data
public class Influence {
    String followers;

    public Influence(String followers) {
        this.followers = followers;
    }
}
