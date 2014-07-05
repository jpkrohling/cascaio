/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseUpdateRequest;
import com.cascaio.api.v1.validation.BIC;
import com.cascaio.api.v1.validation.Bankleitzahl;
import com.cascaio.api.v1.validation.CNPJ;
import com.cascaio.api.v1.validation.Country;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class FinancialInstitutionUpdateRequest implements BaseUpdateRequest {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Country
    private String country; // 2 chars

    @CNPJ
    private String cnpj; // Brazilian CNPJ

    @Bankleitzahl
    private String bankleitzahl; // German BLZ

    @BIC
    private String bic; // as per ISO 9362

    private String codCompensacao; // Brazilian Codigo de Compensacao

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodCompensacao() {
        return codCompensacao;
    }

    public void setCodCompensacao(String codCompensacao) {
        this.codCompensacao = codCompensacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBankleitzahl() {
        return bankleitzahl;
    }

    public void setBankleitzahl(String bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
