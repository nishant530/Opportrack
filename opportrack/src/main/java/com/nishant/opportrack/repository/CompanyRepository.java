package com.nishant.opportrack.repository;

import com.nishant.opportrack.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}