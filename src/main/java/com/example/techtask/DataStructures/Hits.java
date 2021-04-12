package com.example.techtask.DataStructures;

import com.example.techtask.DataStructures.Document;
import lombok.Data;

public @Data
class Hits {

    String totalHits;
    String offset;
    String keyset;
    String returnedHits;
    Document[] documents;

    public Hits(String totalHits, String offset, String keyset, String returnedHits, Document[] documents) {
        this.totalHits = totalHits;
        this.offset = offset;
        this.keyset = keyset;
        this.returnedHits = returnedHits;
        this.documents = documents;
    }
}
