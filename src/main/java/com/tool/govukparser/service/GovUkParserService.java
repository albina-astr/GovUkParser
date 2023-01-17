package com.tool.govukparser.service;

import com.tool.govukparser.entity.OrganizationData;

import java.util.List;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
public interface GovUkParserService {
    String retrieveCsvFileUrl();

    List<OrganizationData> getByCity(String csvFileUrl, String city);
}
