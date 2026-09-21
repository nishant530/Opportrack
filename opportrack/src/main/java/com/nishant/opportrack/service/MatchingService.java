package com.nishant.opportrack.service;

import com.nishant.opportrack.model.Company;
import com.nishant.opportrack.model.OpportunityItem;
import com.nishant.opportrack.model.User;
import com.nishant.opportrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdzunaService adzunaService;

    @Autowired
    private EmailService emailService; // yeh next step mein banayenge

    // Har 6 ghante mein chalega (milliseconds mein: 6 * 60 * 60 * 1000)
    @Scheduled(fixedRate = 21600000)
    public void checkAndNotify() {
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            for (Company company : user.getSelectedCompanies()) {
                List<OpportunityItem> items = adzunaService.fetchInternships(company.getName());

                if (!items.isEmpty()) {
                    emailService.sendDigestEmail(user.getEmail(), items);
                }
            }
        }
    }
}