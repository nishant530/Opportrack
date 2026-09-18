package com.nishant.opportrack.model;

public class OpportunityItem {
    private String title;
    private String link;
    private String source; // jaise "Devpost", "Unstop"

    public OpportunityItem(String title, String link, String source) {
        this.title = title;
        this.link = link;
        this.source = source;
    }

    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getSource() { return source; }
}