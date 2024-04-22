package com.microServices.compnayms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatecompanyById(@PathVariable Long id, @RequestBody Company company){
        boolean updated = companyService.updateCompany(company,id);
        if(updated)return new ResponseEntity<>("Company Updated", HttpStatus.OK);
        return new ResponseEntity<>("Unsuccessful", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Job Created", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompanyById(@PathVariable Long id){
        boolean deleted = companyService.deleteCompany(id);
        if(deleted)return new ResponseEntity<>("Company Deleted", HttpStatus.OK);
        return new ResponseEntity<>("Request Unsuccessful", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company c = companyService.getCompanybyId(id);
        return new ResponseEntity<>(c , HttpStatus.OK);

    }

}
