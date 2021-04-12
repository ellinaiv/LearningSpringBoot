package com.example.techtask.DataStructures;

import lombok.Data;

import java.util.Arrays;

@Data
public class Document {

    String id;
    Source source;
    String socialsource;
    String story;
    String url;
    String profileId;
    String profileName;
    String isGroup;
    String docDate;
    String updated;
    Attachment[] attachments;
    String[] tags;
    Author author;
    Mention[] mentioned;
    Url[] urls;

    public Document(String id, Source source, String socialsource,
                    String story, String url, String profileId, String profileName,
                    String isGroup, String docDate, String updated, Attachment[] attachments,
                    String[] tags, Author author, Mention[] mentioned, Url[] urls) {
        this.id = id;
        this.source = source;
        this.socialsource = socialsource;
        this.story = story;
        this.url = url;
        this.profileId = profileId;
        this.profileName = profileName;
        this.isGroup = isGroup;
        this.docDate = docDate;
        this.updated = updated;
        this.attachments = attachments;
        this.tags = tags;
        this.author = author;
        this.mentioned = mentioned;
        this.urls = urls;
    }

    public boolean contains(String word) {
        if (story != null) {
            if (story.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public void encapsulate(String tag) {
        story = "<" + tag + ">" + story + "</" + tag + ">";
    }

    public boolean compareId(String idToCompare) {
        return id.equals(idToCompare);
    }

    public boolean isMentioned(String id) {
        if(mentioned == null){
            return false;
        }
        return Arrays.stream(mentioned).anyMatch(mention -> mention.getId().equals(id));

    }
}
