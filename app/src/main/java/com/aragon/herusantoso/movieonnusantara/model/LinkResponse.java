package com.aragon.herusantoso.movieonnusantara.model;

import java.util.List;

/**
 * Created by santoso on 11/17/17.
 */

public class LinkResponse {
    private String value;
    private String message;
    private List<Link> result;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Link> getResult() {
        return result;
    }

    public void setResult(List<Link> result) {
        this.result = result;
    }
}
