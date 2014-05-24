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
