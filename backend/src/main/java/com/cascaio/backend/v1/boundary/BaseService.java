package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.BaseUpdateRequest;
import com.cascaio.backend.v1.entity.CascaioEntity;
import com.cascaio.backend.v1.entity.CascaioEntity_;
import com.cascaio.backend.v1.entity.EntityAdapter;
import org.slf4j.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@RolesAllowed({"admin"})
public abstract class BaseService<
        CreateRequest,
        UpdateRequest extends BaseUpdateRequest,
        ApiResponse,
        Persistent extends CascaioEntity,
        Adapter extends EntityAdapter<CreateRequest, UpdateRequest, ApiResponse, Persistent>
        > {
    @Inject
    EntityManager entityManager;

    @Inject
    HttpServletRequest servletRequest;

    @Inject
    Adapter adapter;

    @Inject
    Persistent persistentSample;

    @Inject
    Logger logger;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse createAsJson(@Valid CreateRequest request) {
        return create(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse createAsFormParameters(@Valid @BeanParam CreateRequest request) {
        return create(request);
    }

    public ApiResponse create(CreateRequest request) {
        preCreate(request);
        Persistent persistent = getInstrumentedAdapter().adaptCreate(request);
        prePersist(persistent);
        createAsEntity(persistent);
        ApiResponse response = getInstrumentedAdapter().adaptPersistent(persistent);
        postCreate(response);
        return response;
    }

    public Persistent createAsEntity(Persistent persistent) {
        getEntityManager().persist(persistent);
        return persistent;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse updateAsJson(@Valid UpdateRequest request) {
        return update(request);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse updateAsFormParameters(@Valid @BeanParam UpdateRequest request) {
        return update(request);
    }

    public ApiResponse update(UpdateRequest request) {
        preUpdate(request);
        Persistent persistent = getInstrumentedAdapter().adaptUpdate(request, readAsEntity(request.getId()));
        getEntityManager().persist(persistent);
        ApiResponse response = getInstrumentedAdapter().adaptPersistent(persistent);
        postUpdate(response);
        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public ApiResponse read(@PathParam("id") String id) {
        preRead(id);
        ApiResponse response = getInstrumentedAdapter().adaptPersistent(readAsEntity(id));
        postRead(response);
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<ApiResponse> list() {
        List<ApiResponse> response = getInstrumentedAdapter().adaptPersistent(listAsEntity());
        postList(response);
        return response;
    }

    @RolesAllowed({"user", "admin"})
    public List<Persistent> listAsEntity() {
        preList();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persistent> query = builder.createQuery(getPersistentClass());

        Root<Persistent> root = query.from(getPersistentClass());
        query.select(root);
        instrumentQuery(builder, root, query);

        List<Persistent> response = getEntityManager().createQuery(query).getResultList();
        postListAsEntity(response);
        return response;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        preDelete(id);
        Persistent financialInstitution = readAsEntity(id);
        getEntityManager().remove(financialInstitution);
    }

    @RolesAllowed({"user", "admin"})
    public Persistent readAsEntity(String id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persistent> query = builder.createQuery(getPersistentClass());
        Root<Persistent> root = query.from(getPersistentClass());
        query.select(root);
        query.where(builder.equal(root.get(CascaioEntity_.id), id));
        instrumentQuery(builder, root, query);
        return getEntityManager().createQuery(query).getSingleResult();
    }

    public void preCreate(CreateRequest request) {
    }

    public void postCreate(ApiResponse response) {
    }

    @RolesAllowed({"user", "admin"})
    public void preRead(String id) {
    }

    @RolesAllowed({"user", "admin"})
    public void postRead(ApiResponse response) {
    }

    public void preUpdate(UpdateRequest request) {
    }

    public void postUpdate(ApiResponse response) {
    }

    public void preDelete(String id) {
    }

    @RolesAllowed({"user", "admin"})
    public void preList() {
    }

    public void prePersist(Persistent persistent) {
    }

    @RolesAllowed({"user", "admin"})
    public void postList(List<ApiResponse> response) {
    }

    @RolesAllowed({"user", "admin"})
    public void postListAsEntity(List<Persistent> response) {
    }

    public Adapter instrumentAdapter(Adapter adapter) {
        return adapter;
    }

    public Adapter getAdapter() {
        return this.adapter;
    }

    public void instrumentQuery(CriteriaBuilder builder, Root<Persistent> root, CriteriaQuery<Persistent> query) {
        // no op on this implementation
    }

    private Adapter getInstrumentedAdapter() {
        Adapter adapter = getAdapter();
        return instrumentAdapter(adapter);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private Class<Persistent> getPersistentClass() {
        return (Class<Persistent>) persistentSample.getClass();
    }
}
