package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.BaseUpdateRequest;
import com.cascaio.backend.v1.entity.CascaioEntity;
import com.cascaio.backend.v1.entity.CascaioEntity_;
import com.cascaio.backend.v1.entity.EntityAdapter;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
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
    Logger logger;

    public abstract Adapter getAdapter();
    public abstract Class<Persistent> getPersistentClass();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse createAsJson(CreateRequest request) {
        return create(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse createAsFormParameters(@BeanParam CreateRequest request) {
        return create(request);
    }

    public ApiResponse create(CreateRequest request) {
        if (null != servletRequest.getUserPrincipal()) {
            logger.info("******* User for this request:" + servletRequest.getUserPrincipal().getName());
        } else {
            logger.info("******* NO User for this request");
        }
        preCreate(request);
        Persistent persistent = getInstrumentedAdapter().adaptCreate(request);
        getEntityManager().persist(persistent);
        ApiResponse response = getInstrumentedAdapter().adaptPersistent(persistent);
        postCreate(response);
        return response;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse updateAsJson(UpdateRequest request) {
        return update(request);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse updateAsFormParameters(@BeanParam UpdateRequest request) {
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
    public ApiResponse read(@PathParam("id") String id) {
        preRead(id);
        ApiResponse response = getInstrumentedAdapter().adaptPersistent(readAsEntity(id));
        postRead(response);
        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApiResponse> list() {
        preList();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Persistent> query = builder.createQuery(getPersistentClass());

        Root<Persistent> root = query.from(getPersistentClass());
        query.select(root);
        instrumentQuery(builder, root, query);

        List<ApiResponse> response = getInstrumentedAdapter().adaptPersistent(getEntityManager().createQuery(query).getResultList());
        postList(response);
        return response;
    }

    public void instrumentQuery(CriteriaBuilder builder, Root<Persistent> root, CriteriaQuery<Persistent> query) {
        // no op on this implementation
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        preDelete(id);
        Persistent financialInstitution = readAsEntity(id);
        getEntityManager().remove(financialInstitution);
    }

    @OPTIONS
    public void preflight() {
        // do nothing, as this is handled as a filter
    }

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

    public void preRead(String id) {

    }

    public void postRead(ApiResponse response) {

    }

    public void preUpdate(UpdateRequest request) {

    }

    public void postUpdate(ApiResponse response) {

    }

    public void preDelete(String id) {

    }

    public void preList() {

    }

    public void postList(List<ApiResponse> response) {

    }

    private Adapter getInstrumentedAdapter() {
        Adapter adapter = getAdapter();
        return instrumentAdapter(adapter);
    }

    public Adapter instrumentAdapter(Adapter adapter) {
        return adapter;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
