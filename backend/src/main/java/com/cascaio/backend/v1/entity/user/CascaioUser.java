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
package com.cascaio.backend.v1.entity.user;

import com.cascaio.backend.v1.entity.CascaioEntity;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public class CascaioUser extends CascaioEntity {

    protected CascaioUser() {
    }

    public CascaioUser(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CascaioUser) {
            return getId().equals(((CascaioUser)o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += getId().hashCode();
        return hash;
    }


}
