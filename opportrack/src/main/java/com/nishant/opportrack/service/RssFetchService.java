package com.nishant.opportrack.service;

import com.nishant.opportrack.model.OpportunityItem;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssFetchService {

    public List<OpportunityItem> fetchFeed(String feedUrl, String sourceName) {
        List<OpportunityItem> items = new ArrayList<>();
        try {
            URL url = new URL(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));

            for (SyndEntry entry : feed.getEntries()) {
                items.add(new OpportunityItem(entry.getTitle(), entry.getLink(), sourceName));
            }
        } catch (Exception e) {
            System.out.println("Error fetching feed: " + feedUrl + " - " + e.getMessage());
        }
        return items;
    }
}