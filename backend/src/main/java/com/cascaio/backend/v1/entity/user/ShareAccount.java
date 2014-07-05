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

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
public abstract class ShareAccount extends Account {

    @Formula("( select sum(sts.amount) from Transaction sts where sts.account_id = id )")
    private BigDecimal total;

    // JPA happy
    protected ShareAccount() {
    }

    public ShareAccount(CascaioUser user, String name) {
        super(user, name);
    }

    public ShareAccount(String id, CascaioUser user, String name) {
        super(id, user, name);
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }
}
