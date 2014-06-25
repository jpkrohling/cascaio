package com.cascaio.backend.v1.entity.reference.adapter;

import com.cascaio.api.v1.reference.CategoryCreateRequest;
import com.cascaio.api.v1.reference.CategoryResponse;
import com.cascaio.api.v1.reference.CategoryUpdateRequest;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.Category;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CategoryAdapter extends
        EntityAdapter<CategoryCreateRequest, CategoryUpdateRequest, CategoryResponse, Category> {

    // TODO: figure out a clean way to retrieve objects of the same type, without circular dependencies
    // for now, we use the entity manager, but would be better to use the service (which injects this dateTimeAdapter)
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

        if (null != category.getParent()) {
            response.setParent(adaptParent(category.getParent()));
        }

        return response;
    }

    private CategoryResponse adaptParent(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        response.setId(category.getId());

        if (null != category.getParent()) {
            response.setParent(adaptParent(category.getParent()));
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
