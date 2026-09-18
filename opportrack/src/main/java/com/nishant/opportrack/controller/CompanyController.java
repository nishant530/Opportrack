package com.nishant.opportrack.controller;

import com.nishant.opportrack.model.Company;
import com.nishant.opportrack.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    // Saari companies dikhana
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Nayi company add karna (admin ke liye, testing mein hum khud add karenge)
    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }
}