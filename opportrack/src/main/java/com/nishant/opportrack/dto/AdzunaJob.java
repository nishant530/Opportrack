package com.nishant.opportrack.dto;

public class AdzunaJob {
    private String title;
    private AdzunaCompany company;
    private String redirect_url;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public AdzunaCompany getCompany() { return company; }
    public void setCompany(AdzunaCompany company) { this.company = company; }
    public String getRedirect_url() { return redirect_url; }
    public void setRedirect_url(String redirect_url) { this.redirect_url = redirect_url; }
}