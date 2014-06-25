package com.cascaio.api.v1.admin;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class FinancialInstitutionImportBundesbankResponse {

    private int success;
    private int skipped;
    private String errorMessage;

    public FinancialInstitutionImportBundesbankResponse() {
    }

    public FinancialInstitutionImportBundesbankResponse(int success, int skipped) {
        this.success = success;
        this.skipped = skipped;
    }

    public FinancialInstitutionImportBundesbankResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
