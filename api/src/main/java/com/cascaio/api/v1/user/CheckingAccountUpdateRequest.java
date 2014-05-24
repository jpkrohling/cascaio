package com.cascaio.api.v1.user;

import com.cascaio.api.v1.BaseUpdateRequest;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CheckingAccountUpdateRequest extends CheckingAccountCreateRequest implements BaseUpdateRequest {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
