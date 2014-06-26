package com.cascaio.api.v1;

import javax.ws.rs.PathParam;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ReadRequestById extends BaseReadRequest {

    @PathParam("id")
    private String id;

    public ReadRequestById(String id) {
        this.id = id;
    }

    public ReadRequestById() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
