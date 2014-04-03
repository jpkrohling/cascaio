package com.cascaio.backend.v1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account extends NamedUserData {

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Transaction> transactions = new ArrayList<>();

    protected Account() {
    }

    protected Account(CascaioUser user, String name) {
        super(user, name);
    }

    protected Account(String id, CascaioUser user, String name) {
        super(id, user, name);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract BigDecimal getTotal();
}
