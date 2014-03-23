package com.cascaio.api.v1;

/**
 * User: jpkroehling
 * Date: 2014-02-20
 * Time: 9:15 PM
 */
public class FinancialInstitutionUpdateRequest extends FinancialInstitutionCreateRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
