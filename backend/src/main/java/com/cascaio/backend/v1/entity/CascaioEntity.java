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
package com.cascaio.backend.v1.entity;

import java.io.Serializable;
import org.joda.time.DateTime;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.util.UUID;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@MappedSuperclass
public abstract class CascaioEntity implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();

    private final DateTime createdAt = new DateTime();
    private DateTime updatedAt = new DateTime();

    protected CascaioEntity() {
    }

    public CascaioEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new DateTime();
    }

    /**
     * We may want to override this on classes with collections that are important enough to make the object
     * be understood as modified. For instance, a new transaction into an account means that the account was
     * last modified when this transaction was added.
     *
     * @return DateTime representing the last time that the object was changed.
     */
    public DateTime getLastModifiedAt() {
        return this.getUpdatedAt();
    }

    @Override
    public String toString() {
        return "CascaioEntity{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
