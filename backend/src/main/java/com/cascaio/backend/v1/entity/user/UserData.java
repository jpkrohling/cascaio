package com.cascaio.backend.v1.entity.user;

import com.cascaio.backend.v1.entity.CascaioEntity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@MappedSuperclass
public abstract class UserData extends CascaioEntity {
    @ManyToOne
    private CascaioUser user;

    protected UserData() {
    }

    public UserData(CascaioUser user) {
        this.user = user;
    }

    public UserData(String id, CascaioUser user) {
        super(id);
        this.user = user;
    }

    public CascaioUser getUser() {
        return user;
    }
}
