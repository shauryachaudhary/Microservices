package com.microServices.compnayms.company;

import java.util.List;

public interface CompanyService {
     List<Company> getAllCompanies();
     boolean updateCompany(Company company, Long id);
     void createCompany(Company company);
     boolean deleteCompany(Long Id);
     Company getCompanybyId(Long Id);

}
