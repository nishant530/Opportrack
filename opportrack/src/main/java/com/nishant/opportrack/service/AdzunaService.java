package com.nishant.opportrack.service;

import com.nishant.opportrack.dto.AdzunaJob;
import com.nishant.opportrack.dto.AdzunaResponse;
import com.nishant.opportrack.model.OpportunityItem;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdzunaService {

    private final String APP_ID = "e2368d3d";
    private final String APP_KEY = "739fcc2f63d4f48b6cf12db093d362eb";

    public List<OpportunityItem> fetchInternships(String companyName) {
        List<OpportunityItem> items = new ArrayList<>();
        try {
            String url = "https://api.adzuna.com/v1/api/jobs/in/search/1"
        + "?app_id=" + APP_ID
        + "&app_key=" + APP_KEY
       + "&what=intern"
+ "&results_per_page=20";

            RestTemplate restTemplate = new RestTemplate();
            AdzunaResponse response = restTemplate.getForObject(url, AdzunaResponse.class);
System.out.println("DEBUG: Response is " + (response == null ? "NULL" : "NOT NULL, results size: " + (response.getResults() == null ? "results NULL" : response.getResults().size())));
            if (response != null && response.getResults() != null) {
                for (AdzunaJob job : response.getResults()) {
                    String company = job.getCompany() != null ? job.getCompany().getDisplay_name() : companyName;
                    items.add(new OpportunityItem(job.getTitle(), job.getRedirect_url(), company));
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching Adzuna data for " + companyName + ": " + e.getMessage());
        }
        return items;
    }
}