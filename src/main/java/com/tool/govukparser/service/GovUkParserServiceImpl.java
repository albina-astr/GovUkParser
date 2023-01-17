package com.tool.govukparser.service;

import com.tool.govukparser.entity.OrganizationData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
@Service
public class GovUkParserServiceImpl implements GovUkParserService {
    private static final String MAIN_PAGE_URL =
            "https://www.gov.uk/government/publications/register-of-licensed-sponsors-workers";
    private static final int ROWS_LIMIT = 100;

    @Override
    public String retrieveCsvFileUrl() {
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

    @Override
    public List<OrganizationData> getByCity(String csvFileUrl, String city) {
        if (csvFileUrl == null || csvFileUrl.isEmpty() || city == null || city.isEmpty()) {
            throw new IllegalArgumentException("CsvFileUrl and city must not be null or empty!");
        }

        List<OrganizationData> cityData = new ArrayList<>();
        try {
            Scanner input = getInputStreamScanner(csvFileUrl);

            int counter = 1;
            while (input.hasNextLine()) {
                String line = input.nextLine().replaceAll("\"", "");
                String[] pieces = line.split(",");
                if (pieces[1].contains(city)) {
                    cityData.add(new OrganizationData(pieces[0], pieces[1], pieces[2], pieces[3], pieces[4]));
                }
                if (++counter > ROWS_LIMIT) {
                    System.out.println("Row limit has been reached. Stopping to read the file");
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("Found " + cityData.size() + " organizations");

        return cityData;
    }

    private static Scanner getInputStreamScanner(String csvFileUrl) throws IOException {
        URL csvDataUrl = new URL(csvFileUrl);
        URLConnection csvData = csvDataUrl.openConnection();
        return new Scanner(csvData.getInputStream());
    }
}
