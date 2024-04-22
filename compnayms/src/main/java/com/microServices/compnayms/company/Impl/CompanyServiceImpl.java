package com.microServices.compnayms.company.Impl;

import com.microServices.compnayms.company.Company;
import com.microServices.compnayms.company.CompanyRepository;
import com.microServices.compnayms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedcompany,Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()) {
            Company compy = companyOptional.get();
            compy.setName(updatedcompany.getName());
            compy.setDescription(updatedcompany.getDescription());

            companyRepository.save(compy);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanybyId(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
