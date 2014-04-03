package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.backend.v1.entity.CascaioEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public abstract class EntityAdapter<CreateRequest, UpdateRequest, Response, Persistent extends CascaioEntity> {

    public abstract Response adaptPersistent(Persistent persistent);
    public abstract Persistent adaptUpdate(UpdateRequest request);
    public abstract Persistent adaptCreate(CreateRequest request);

    public List<Response> adaptPersistent(List<Persistent> persistentList) {
        List<Response> responses = new ArrayList<>(persistentList.size());
        for (Persistent persistent : persistentList) {
            responses.add(adaptPersistent(persistent));
        }
        return responses;
    }

    public List<Persistent> adaptUpdate(List<UpdateRequest> persistentList) {
        List<Persistent> responses = new ArrayList<>(persistentList.size());
        for (UpdateRequest persistent : persistentList) {
            responses.add(adaptUpdate(persistent));
        }
        return responses;
    }

    public List<Persistent> adaptCreate(List<CreateRequest> persistentList) {
        List<Persistent> responses = new ArrayList<>(persistentList.size());
        for (CreateRequest persistent : persistentList) {
            responses.add(adaptCreate(persistent));
        }
        return responses;
    }

}
