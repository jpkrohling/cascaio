package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.backend.v1.entity.CascaioUser;
import com.cascaio.backend.v1.entity.UserData;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public abstract class UserDataAdapter<CreateRequest, UpdateRequest, Response, Persistent extends UserData>
        extends EntityAdapter<CreateRequest, UpdateRequest, Response, Persistent> {

    private CascaioUser cascaioUser;

    public CascaioUser getCascaioUser() {
        return cascaioUser;
    }

    public void setCascaioUser(CascaioUser cascaioUser) {
        this.cascaioUser = cascaioUser;
    }
}
