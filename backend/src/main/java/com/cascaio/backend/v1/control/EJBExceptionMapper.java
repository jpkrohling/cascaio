package com.cascaio.backend.v1.control;

import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Override
    public Response toResponse(EJBException e) {
        if (e.getCausedByException() instanceof NoResultException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.serverError().entity(e.getCausedByException().getMessage()).build();
    }

}
