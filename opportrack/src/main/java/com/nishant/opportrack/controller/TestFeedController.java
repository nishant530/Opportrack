package com.nishant.opportrack.controller;
import com.nishant.opportrack.service.EmailService;
import com.nishant.opportrack.service.AdzunaService;
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

    @Autowired
    private AdzunaService adzunaService;

    @Autowired
private EmailService emailService;

    @GetMapping("/api/test-feed")
    public List<OpportunityItem> testFeed() {
        // Devpost hackathons feed - wahi URL jo humne n8n mein use kiya tha
        return rssFetchService.fetchFeed("https://hnrss.org/newest", "HackerNews");
    }

    @GetMapping("/api/test-adzuna")
    public List<OpportunityItem> testAdzuna() {
        return adzunaService.fetchInternships("Google");
    }
    @GetMapping("/api/test-email")
public String testEmail() {
    var items = adzunaService.fetchInternships("Google");
    if (items.isEmpty()) {
        return "No items found from Adzuna to send.";
    }
    emailService.sendDigestEmail("nishantsoni1335@gmail.com", items);
    return "Email sent with " + items.size() + " items!";
}
}