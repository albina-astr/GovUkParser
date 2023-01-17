package com.tool.govukparser.controller;

import com.tool.govukparser.entity.OrganizationData;
import com.tool.govukparser.service.GovUkParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
@Controller
public class PublicationsController {
    @Autowired
    private GovUkParserService parserService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", "GovUkParserApplication");
        return "home";
    }

    @GetMapping("/city/{city}")
    public String getByCity(@PathVariable String city, Model model) {
        try {
            String csvUrl = parserService.retrieveCsvFileUrl();
            List<OrganizationData> byCityData = parserService.getByCity(csvUrl, city);
            model.addAttribute("organizations", byCityData);

            return "organizations";
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "organizations";
    }
}
