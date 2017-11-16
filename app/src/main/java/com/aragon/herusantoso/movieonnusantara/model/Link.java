package com.aragon.herusantoso.movieonnusantara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by santoso on 11/17/17.
 */

public class Link {

    @SerializedName("link")
    @Expose
    private String link;

    public Link(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
