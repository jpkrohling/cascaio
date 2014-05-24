package com.cascaio.backend.v1.entity.adapters.api;

import com.cascaio.api.v1.CategoryCreateRequest;
import com.cascaio.api.v1.CategoryResponse;
import com.cascaio.api.v1.CategoryUpdateRequest;
import com.cascaio.backend.v1.entity.Category;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CategoryAdapter extends EntityAdapter<CategoryCreateRequest, CategoryUpdateRequest, CategoryResponse, Category> {

    // TODO: figure out a clean way to retrieve objects of the same type, without circular dependencies
    // for now, we use the entity manager, but would be better to use the service (which injects this adapter)
    @Inject
    EntityManager entityManager;

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
    public Category adaptUpdate(CategoryUpdateRequest request, Category category) {
        category.setName(currentOrUpdated(request.getName(), category.getName()));

        String parentId = request.getParentId();
        if (isSet(parentId)) {
            category.setParent(entityManager.find(Category.class, parentId));
        }

        return category;
    }

    @Override
    public Category adaptCreate(CategoryCreateRequest request) {
        Category category = new Category(request.getName());

        String parentId = request.getParentId();
        if (isSet(parentId)) {
            category.setParent(entityManager.find(Category.class, parentId));
        }

        return category;
    }
}
