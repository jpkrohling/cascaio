/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        Exception causedBy = e.getCausedByException();

        if (causedBy instanceof NoResultException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (causedBy instanceof DataAccessForbiddenException) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        if (causedBy != null) {
            return Response.serverError().entity(causedBy.getMessage()).build();
        } else {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
