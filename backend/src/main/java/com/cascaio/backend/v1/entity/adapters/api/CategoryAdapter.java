package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.api.v1.CategoryCreateRequest;
import com.cascaio.api.v1.CategoryResponse;
import com.cascaio.api.v1.CategoryUpdateRequest;
import com.cascaio.backend.v1.boundary.CategoryService;
import com.cascaio.backend.v1.entity.Category;

import javax.inject.Inject;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CategoryAdapter extends EntityAdapter<CategoryCreateRequest, CategoryUpdateRequest, CategoryResponse, Category> {

    @Inject
    CategoryService service;

    @Override
    public CategoryResponse adaptPersistent(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        response.setId(category.getId());
        for (Category subCategory : category.getSubCategories()) {
            response.addSubCategory(adaptPersistent(subCategory));
        }
        return response;
    }

    @Override
    public Category adaptUpdate(CategoryUpdateRequest request) {
        Category category = service.readAsEntity(request.getId());
        category.setName(currentOrUpdated(request.getName(), category.getName()));

        String parentId = request.getParentId();
        if (isSet(parentId)) {
            category.setParent(service.readAsEntity(parentId));
        }

        return category;
    }

    @Override
    public Category adaptCreate(CategoryCreateRequest request) {
        Category category = new Category(request.getName());

        String parentId = request.getParentId();
        if (isSet(parentId)) {
            category.setParent(service.readAsEntity(parentId));
        }

        return category;
    }
}
