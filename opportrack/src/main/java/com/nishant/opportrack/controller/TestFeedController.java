package com.nishant.opportrack.controller;

import com.nishant.opportrack.model.OpportunityItem;
import com.nishant.opportrack.service.RssFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestFeedController {

    @Autowired
    private RssFetchService rssFetchService;

    @GetMapping("/api/test-feed")
    public List<OpportunityItem> testFeed() {
        // Devpost hackathons feed - wahi URL jo humne n8n mein use kiya tha
        return rssFetchService.fetchFeed("https://hnrss.org/newest", "HackerNews");
    }
}