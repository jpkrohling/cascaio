package com.cascaio.backend.v1.entity.user;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@MappedSuperclass
public class NamedUserData extends UserData {

    @NotNull
    String name;

    // JPA happy
    protected NamedUserData() {
    }

    public NamedUserData(CascaioUser user, String name) {
        super(user);
        this.name = name;
    }

    public NamedUserData(String id, CascaioUser user, String name) {
        super(id, user);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
