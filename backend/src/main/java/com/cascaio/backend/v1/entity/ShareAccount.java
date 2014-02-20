package com.cascaio.backend.v1.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

/**
 * User: jpkrohling
 * Date: 6/30/13
 * Time: 7:43 PM
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
