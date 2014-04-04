package com.cascaio.backend.v1.boundary;

import com.cascaio.backend.v1.entity.CascaioEntity_;
import com.cascaio.backend.v1.entity.CascaioUser;
import com.cascaio.backend.v1.entity.UserData;
import com.cascaio.backend.v1.entity.adapters.api.UserDataAdapter;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@RolesAllowed("user")
public abstract class BaseUserService
        <   CreateRequest,
            UpdateRequest,
            ApiResponse,
            Persistent extends UserData,
            Adapter extends UserDataAdapter<CreateRequest, UpdateRequest, ApiResponse, Persistent>
        > extends BaseService<CreateRequest, UpdateRequest, ApiResponse, Persistent, Adapter> {

    @Resource
    SessionContext sessionContext;

    @Inject
    Logger logger;

    @Override
    public Adapter instrumentAdapter(Adapter adapter) {
        adapter.setCascaioUser(getCurrentUser());
        return super.instrumentAdapter(adapter);
    }

    public CascaioUser getCurrentUser() {
        return getUserFromId(sessionContext.getCallerPrincipal().getName());
    }

    private CascaioUser getUserFromId(String id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CascaioUser> query = builder.createQuery(CascaioUser.class);
        Root<CascaioUser> root = query.from(CascaioUser.class);
        query.select(root);
        query.where(builder.equal(root.get(CascaioEntity_.id), id));

        List<CascaioUser> results = getEntityManager().createQuery(query).getResultList();
        if (results.size() == 1) {
            logger.debug("User {} is registered already, returning it to the caller", id);
            return results.get(0);
        }

        if (results.size() >= 1) {
            throw new IllegalStateException("Duplicate user id found.");
        }

        logger.debug("User doesn't exists, creating user with ID {}", id);
        CascaioUser cascaioUser = new CascaioUser(id);
        getEntityManager().persist(cascaioUser);
        return cascaioUser;
    }
}