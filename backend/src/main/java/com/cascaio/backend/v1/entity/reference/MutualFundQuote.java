package com.cascaio.backend.v1.entity.reference;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mutualFund_id", "date"})})
public class MutualFundQuote extends Quote {

    @ManyToOne
    private MutualFund mutualFund;

    // JPA happy
    protected MutualFundQuote() {
    }

    protected MutualFundQuote(DateTime date, BigDecimal price, MutualFund mutualFund) {
        this(UUID.randomUUID().toString(), date, price, mutualFund);
    }

    protected MutualFundQuote(String id, DateTime date, BigDecimal price, MutualFund mutualFund) {
        super(id, date, price);
        this.mutualFund = mutualFund;
    }

    public MutualFund getMutualFund() {
        return mutualFund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutualFundQuote)) return false;

        MutualFundQuote that = (MutualFundQuote) o;

        if (mutualFund != null ? !mutualFund.equals(that.mutualFund) : that.mutualFund != null) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = getMutualFund().hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MutualFundQuote{" +
                "mutualFund=" + mutualFund +
                "quote=" + super.toString() +
                '}';
    }
}
