package com.nishant.opportrack.config;

import com.nishant.opportrack.model.Company;
import com.nishant.opportrack.repository.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    public DataSeeder(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) {
        if (companyRepository.count() == 0) {
            List<String> defaultCompanies = List.of(
                "Google", "Microsoft", "JP Morgan", "GSoC",
                "Amazon", "Infosys", "Adobe", "Goldman Sachs"
            );
            defaultCompanies.forEach(name -> {
                Company c = new Company();
                c.setName(name);
                companyRepository.save(c);
            });
            System.out.println("DataSeeder: default companies added.");
        }
    }
}