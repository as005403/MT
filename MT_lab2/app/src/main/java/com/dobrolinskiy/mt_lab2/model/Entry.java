package com.dobrolinskiy.mt_lab2.model;

import com.fasterxml.jackson.annotation.*;

public class Entry {
    private String api;
    private String description;
    private String auth;
    private boolean https;
    private String cors;
    private String link;
    private String category;

    @JsonProperty("API")
    public String getAPI() { return api; }
    @JsonProperty("API")
    public void setAPI(String value) { this.api = value; }

    @JsonProperty("Description")
    public String getDescription() { return description; }
    @JsonProperty("Description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("Auth")
    public String getAuth() { return auth; }
    @JsonProperty("Auth")
    public void setAuth(String value) { this.auth = value; }

    @JsonProperty("HTTPS")
    public boolean getHTTPS() { return https; }
    @JsonProperty("HTTPS")
    public void setHTTPS(boolean value) { this.https = value; }

    @JsonProperty("Cors")
    public String getCors() { return cors; }
    @JsonProperty("Cors")
    public void setCors(String value) { this.cors = value; }

    @JsonProperty("Link")
    public String getLink() { return link; }
    @JsonProperty("Link")
    public void setLink(String value) { this.link = value; }

    @JsonProperty("Category")
    public String getCategory() { return category; }
    @JsonProperty("Category")
    public void setCategory(String value) { this.category = value; }
}
