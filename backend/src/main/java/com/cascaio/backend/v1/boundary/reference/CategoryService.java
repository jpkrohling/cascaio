package com.cascaio.backend.v1.boundary.reference;

import com.cascaio.api.v1.BaseQueryRequest;
import com.cascaio.api.v1.ReadRequestById;
import com.cascaio.api.v1.reference.CategoryCreateRequest;
import com.cascaio.api.v1.reference.CategoryResponse;
import com.cascaio.api.v1.reference.CategoryUpdateRequest;
import com.cascaio.backend.v1.boundary.BaseService;
import com.cascaio.backend.v1.entity.reference.Category;
import com.cascaio.backend.v1.entity.reference.adapter.CategoryAdapter;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/categories")
@Stateless
public class CategoryService extends BaseService<
        CategoryCreateRequest,
        CategoryUpdateRequest,
        BaseQueryRequest,
        ReadRequestById,
        CategoryResponse,
        Category,
        CategoryAdapter> {
}
