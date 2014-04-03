package com.cascaio.backend.v1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CategorySynonym extends CascaioEntity {

    @ManyToOne
    @NotNull
    private Category category;

    @NotNull
    @Column(unique = true)
    private String synonym;

    protected CategorySynonym() {
    }

    public CategorySynonym(String id, Category category, String synonym) {
        super(id);
        this.category = category;
        this.synonym = synonym;
    }

    public CategorySynonym(Category category, String synonym) {
        this.category = category;
        this.synonym = synonym;
    }

    public String getSynonym() {
        return synonym;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
