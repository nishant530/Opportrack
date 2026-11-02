package com.nishant.opportrack.controller;

import com.nishant.opportrack.dto.LoginRequest;
import com.nishant.opportrack.dto.LoginResponse;
import com.nishant.opportrack.security.JwtUtil;
import com.nishant.opportrack.model.User;
import com.nishant.opportrack.model.Company;
import com.nishant.opportrack.model.OpportunityItem;
import com.nishant.opportrack.service.UserService;
import com.nishant.opportrack.service.AdzunaService;
import com.nishant.opportrack.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AdzunaService adzunaService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        var user = userService.findByEmail(request.getEmail());
        if (user == null || !userService.checkPassword(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }

    @PostMapping("/select-companies")
    public User selectCompanies(@RequestHeader("Authorization") String token, @RequestBody List<Long> companyIds) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email);

        Set<Company> companies = new HashSet<>(companyRepository.findAllById(companyIds));
        user.setSelectedCompanies(companies);

        return userService.registerUser(user);
    }

    @GetMapping("/my-opportunities")
    public List<OpportunityItem> getMyOpportunities(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email);

        List<OpportunityItem> allItems = new ArrayList<>();
        for (Company company : user.getSelectedCompanies()) {
            allItems.addAll(adzunaService.fetchInternships(company.getName()));
        }
        return allItems;
    }
}