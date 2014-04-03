package com.cascaio.backend.v1.entity;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CascaioUser extends CascaioEntity {

    protected CascaioUser() {
    }

    public CascaioUser(String id) {
        super(id);
    }
}
