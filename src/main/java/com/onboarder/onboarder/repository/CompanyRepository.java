package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findById(int companyId);

    Optional<List<Company>> findByName(String name);
}
