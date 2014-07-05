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
