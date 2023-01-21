package com.tool.govukparser.service;

import com.opencsv.CSVReader;
import com.tool.govukparser.entity.Company;
import com.tool.govukparser.repository.CompanyRepository;
import org.apache.commons.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
@Service
public class GovUkParserServiceImpl implements GovUkParserService {
    private static final String MAIN_PAGE_URL =
            "https://www.gov.uk/government/publications/register-of-licensed-sponsors-workers";

    @Autowired
    private CompanyRepository repository;

    @Override
    public void parseAndStoreData() {
        Set<Company> companiesData = readCsvFile(retrieveCsvFileUrl());
        storeData(companiesData);
    }

    @Override
    public List<Company> getByCompanyName(String companyName) {
        validate(companyName);

        List<Company> data = repository.findByName(companyName);
        System.out.println("Found " + data.size() + " rows of data by company name.");
        return data;
    }

    @Override
    public List<Company> getByCity(String city) {
        validate(city);
        city = normalizeCity(city);

        List<Company> data = repository.findByCity(city);
        System.out.println("Found " + data.size() + " rows of data by city.");
        return data;
    }

    @Override
    public List<Company> getByCounty(String county) {
        validate(county);

        List<Company> data = repository.findByCounty(county);
        System.out.println("Found " + data.size() + " rows of data by county.");
        return data;
    }

    @Override
    public List<Company> getByRating(String rating) {
        validate(rating);

        List<Company> data = repository.findByRating(rating);
        System.out.println("Found " + data.size() + " rows of data by rating.");
        return data;
    }

    @Override
    public List<Company> getByRoute(String route) {
        validate(route);

        List<Company> data = repository.findByRoute(route);
        System.out.println("Found " + data.size() + " rows of data by route.");
        return data;
    }

    @Override
    public List<String> getUniqueCompanyNames() {
        return repository.findDistinctCompanyNames();
    }

    @Override
    public List<String> getUniqueCities() {
        return repository.findDistinctCities();
    }

    @Override
    public List<String> getUniqueCounties() {
        return repository.findDistinctCounties();
    }

    @Override
    public List<String> getUniqueRatings() {
        return repository.findDistinctRatings();
    }

    @Override
    public List<String> getUniqueRoutes() {
        return repository.findDistinctRoutes();
    }

    private static void validate(String city) {
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("CsvFileUrl and city must not be null or empty!");
        }
    }

    private static String normalizeCity(String city) {
        city = city.trim();

        if (city.startsWith(",")) {
            city = city.substring(1);
        }
        if (city.endsWith(",")) {
            city = city.substring(0, city.length() - 1);
        }
        return WordUtils.capitalizeFully(city, '-', ',', ' ');
    }

    private String retrieveCsvFileUrl() {
        String csvUrl = "";
        try {
            Document doc = Jsoup.connect(MAIN_PAGE_URL).get();
            Element element = doc.select("span.download").select("a.govuk-link").first();
            csvUrl = element.attr("abs:href");
            System.out.println(csvUrl);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return csvUrl;
    }

    private Set<Company> readCsvFile(String csvFileUrl) {
        System.out.println("Starting read from CSV file...");
        Set<Company> cityData = new HashSet<>();
        try {
            URL csvDataUrl = new URL(csvFileUrl);
            URLConnection csvData = csvDataUrl.openConnection();
            CSVReader csvReader = new CSVReader(new InputStreamReader(csvData.getInputStream()));
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length != 5) {
                    throw new RuntimeException("There are not 5 columns!");
                }
                Company obj = new Company(nextRecord[0], normalizeCity(nextRecord[1]), nextRecord[2], nextRecord[3],
                        nextRecord[4]);
                cityData.add(obj);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        Iterator<Company> it = cityData.stream().iterator();
        while (it.hasNext()) {
            Company current = it.next();
            if (current.getCity().matches("^[\\d]+") && !current.getCounty().isEmpty()) {

            }
        }

        System.out.println("Successfully read the file! Got " + cityData.size() + " rows.");
        return cityData;
    }

    private void storeData(Collection<Company> companiesData) {
        repository.deleteAll();
        System.out.println("Start to store all the companies data, " + companiesData.size() + " rows...");
        repository.saveAll(companiesData);
        System.out.println("Successfully saved all the companies data!");
    }
}
