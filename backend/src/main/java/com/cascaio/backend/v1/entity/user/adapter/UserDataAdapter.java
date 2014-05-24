package com.cascaio.backend.v1.entity.user.adapter;

import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.user.CascaioUser;
import com.cascaio.backend.v1.entity.user.UserData;

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
