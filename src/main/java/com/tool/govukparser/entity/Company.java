package com.tool.govukparser.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Albina Gimaletdinova on 21/01/2023
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueRow", columnNames = { "name", "city", "county", "rating", "route" })
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city; // todo add UK cities library
    private String county;
    private String rating;
    private String route;

    public Company() {
    }

    public Company(String name, String city, String county, String rating, String route) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.rating = rating;
        this.route = route;
    }

    public Long getId() {
        return id;
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
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(city, company.city) && Objects.equals(county, company.county) && Objects.equals(rating, company.rating) && Objects.equals(route, company.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, county, rating, route);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", rating='" + rating + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
