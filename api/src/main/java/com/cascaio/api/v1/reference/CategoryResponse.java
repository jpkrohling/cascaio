package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.BaseResponse;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class CategoryResponse extends BaseResponse {

    private String name;
    private String id;
    private Collection<CategoryResponse> subCategories = new ArrayList<>();
    private CategoryResponse parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<CategoryResponse> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(CategoryResponse subCategory) {
        this.subCategories.add(subCategory);
    }

    public CategoryResponse getParent() {
        return parent;
    }

    public void setParent(CategoryResponse parent) {
        this.parent = parent;
    }
}
