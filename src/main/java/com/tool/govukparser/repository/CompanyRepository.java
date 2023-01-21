package com.tool.govukparser.repository;

import com.tool.govukparser.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Albina Gimaletdinova on 21/01/2023
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByName(String name);

    List<Company> findByCity(String city);

    List<Company> findByCounty(String county);

    List<Company> findByRating(String rating);

    List<Company> findByRoute(String route);

    @Query(value = "SELECT DISTINCT name FROM company ORDER BY name", nativeQuery = true)
    List<String> findDistinctCompanyNames();

    @Query(value = "SELECT DISTINCT city FROM company ORDER BY city", nativeQuery = true)
    List<String> findDistinctCities();

    @Query(value = "SELECT DISTINCT county FROM company ORDER BY county", nativeQuery = true)
    List<String> findDistinctCounties();

    @Query(value = "SELECT DISTINCT rating FROM company ORDER BY rating", nativeQuery = true)
    List<String> findDistinctRatings();

    @Query(value = "SELECT DISTINCT route FROM company ORDER BY route", nativeQuery = true)
    List<String> findDistinctRoutes();
}
