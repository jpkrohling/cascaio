package com.cascaio.backend.v1.boundary;

import com.cascaio.api.v1.CategoryCreateRequest;
import com.cascaio.api.v1.CategoryResponse;
import com.cascaio.api.v1.CategoryUpdateRequest;
import com.cascaio.backend.v1.entity.Category;
import com.cascaio.backend.v1.entity.adapters.api.CategoryAdapter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Path("/reference/categories")
@Stateless
public class CategoryService extends BaseService<
        CategoryCreateRequest,
        CategoryUpdateRequest,
        CategoryResponse,
        Category,
        CategoryAdapter> {

    @Inject
    CategoryAdapter adapter;

    @Override
    public CategoryAdapter getAdapter() {
        return adapter;
    }

    @Override
    public Class<Category> getPersistentClass() {
        return Category.class;
    }
}
