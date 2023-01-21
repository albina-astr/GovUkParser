package com.tool.govukparser.controller;

import com.tool.govukparser.entity.Company;
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

    @GetMapping("/company/{name}")
    public String getByCompanyName(@PathVariable String name, Model model) {
        List<Company> data = parserService.getByCompanyName(name);
        model.addAttribute("companies", data);
        return "companies";
    }

    @GetMapping("/city/{city}")
    public String getByCity(@PathVariable String city, Model model) {
        System.out.println(city); // todo delete
        List<Company> data = parserService.getByCity(city);
        model.addAttribute("companies", data);
        return "companies";
    }

    @GetMapping("/county/{county}")
    public String getByCounty(@PathVariable String county, Model model) {
        List<Company> data = parserService.getByCounty(county);
        model.addAttribute("companies", data);
        return "companies";
    }

    @GetMapping("/rating/{rating}")
    public String getByRating(@PathVariable String rating, Model model) {
        List<Company> data = parserService.getByRating(rating);
        model.addAttribute("companies", data);
        return "companies";
    }

    @GetMapping("/route/{route}")
    public String getByRoute(@PathVariable String route, Model model) {
        List<Company> data = parserService.getByRating(route);
        model.addAttribute("companies", data);
        return "companies";
    }

    @GetMapping("/companies")
    public String getUniqueCompanyNames(Model model) {
        List<String> data = parserService.getUniqueCompanyNames();
        model.addAttribute("string_objects", data);
        return "object_list";
    }

    @GetMapping("/cities")
    public String getUniqueCities(Model model) {
        List<String> data = parserService.getUniqueCities();
        model.addAttribute("string_objects", data);
        return "object_list";
    }

    @GetMapping("/counties")
    public String getUniqueCounties(Model model) {
        List<String> data = parserService.getUniqueCounties();
        model.addAttribute("string_objects", data);
        return "object_list";
    }

    @GetMapping("/ratings")
    public String getUniqueRatings(Model model) {
        List<String> data = parserService.getUniqueRatings();
        model.addAttribute("string_objects", data);
        return "object_list";
    }

    @GetMapping("/routes")
    public String getUniqueRoutes(Model model) {
        List<String> data = parserService.getUniqueRoutes();
        model.addAttribute("string_objects", data);
        return "object_list";
    }
}
