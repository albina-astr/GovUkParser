package com.tool.govukparser.service;

import com.tool.govukparser.entity.Company;

import java.util.List;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
public interface GovUkParserService {
    void parseAndStoreData();

    List<Company> getByCompanyName(String companyName);
    List<Company> getByCity(String city);
    List<Company> getByCounty(String county);
    List<Company> getByRating(String rating);
    List<Company> getByRoute(String route);

    List<String> getUniqueCompanyNames();
    List<String> getUniqueCities();
    List<String> getUniqueCounties();
    List<String> getUniqueRatings();
    List<String> getUniqueRoutes();
}
