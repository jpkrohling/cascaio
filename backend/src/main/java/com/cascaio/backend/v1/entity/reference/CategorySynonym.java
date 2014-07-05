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
package com.cascaio.backend.v1.entity.reference;

import com.cascaio.backend.v1.entity.CascaioEntity;

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
