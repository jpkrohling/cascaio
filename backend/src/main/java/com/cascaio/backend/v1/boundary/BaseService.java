package com.cascaio.backend.v1.boundary;

import com.cascaio.backend.v1.entity.CascaioEntity;
import com.cascaio.backend.v1.entity.CascaioEntity_;
import com.cascaio.backend.v1.entity.FinancialInstitution;
import com.cascaio.backend.v1.entity.adapters.api.EntityAdapter;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User: jpkroehling
 * Date: 2014-02-20
 * Time: 9:09 PM
 */
@RolesAllowed("user")
public abstract class BaseService<
        CreateRequest,
        UpdateRequest,
        ApiResponse,
        Persistent extends CascaioEntity,
        Adapter extends EntityAdapter<CreateRequest, UpdateRequest, ApiResponse, Persistent>
        > {
    public abstract Adapter getAdapter();
    public abstract Class<Persistent> getPersistentClass();
    public abstract EntityManager getEntityManager();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse createAsJson(CreateRequest request) {
        return create(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ApiResponse createAsFormParameters(@BeanParam CreateRequest request) {
        return create(request);
    }

    public ApiResponse create(CreateRequest request) {
        Persistent persistent = getAdapter().adaptCreate(request);
        getEntityManager().persist(persistent);
        return getAdapter().adaptPersistent(persistent);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse updateAsJson(UpdateRequest request) {
        return update(request);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ApiResponse updateAsFormParameters(@BeanParam UpdateRequest request) {
        return update(request);
    }

    public ApiResponse update(UpdateRequest request) {
        Persistent persistent = getAdapter().adaptUpdate(request);
        getEntityManager().persist(persistent);
        return getAdapter().adaptPersistent(persistent);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse read(@PathParam("id") String id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persistent> query = builder.createQuery(getPersistentClass());
        Root<Persistent> root = query.from(getPersistentClass());
        query.select(root);
        query.where(builder.equal(root.get(CascaioEntity_.id), id));

        return getAdapter().adaptPersistent(getEntityManager().createQuery(query).getSingleResult());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiResponse> list() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persistent> query = builder.createQuery(getPersistentClass());

        Root<Persistent> root = query.from(getPersistentClass());
        query.select(root);

        return getAdapter().adaptPersistent(getEntityManager().createQuery(query).getResultList());
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        Persistent financialInstitution = getEntityManager().find(getPersistentClass(), id);
        getEntityManager().remove(financialInstitution);
    }

    @OPTIONS
    public void preflight() {
        // do nothing, as this is handled as a filter
    }
}
