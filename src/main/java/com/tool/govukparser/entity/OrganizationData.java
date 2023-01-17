package com.tool.govukparser.entity;

import java.util.Objects;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
public class OrganizationData {
    private final String name;
    private final String city; // todo add town library
    private final String county;
    private final String rating;
    private final String route;

    public OrganizationData(String name, String city, String county, String rating, String route) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.rating = rating;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getRating() {
        return rating;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationData that = (OrganizationData) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city) && Objects.equals(county, that.county) && Objects.equals(rating, that.rating) && Objects.equals(route, that.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, county, rating, route);
    }

    @Override
    public String toString() {
        return "CompanyDataRow{" +
                "companyName='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", rating='" + rating + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
