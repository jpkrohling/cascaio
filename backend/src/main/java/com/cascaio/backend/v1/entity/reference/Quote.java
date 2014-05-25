package com.cascaio.backend.v1.entity.reference;

import com.cascaio.backend.v1.entity.CascaioEntity;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@MappedSuperclass
public class Quote extends CascaioEntity {

    @NotNull
    private LocalDate date;

    @NotNull
    @Column(precision = 19, scale = 6)
    private BigDecimal price;

    // JPA happy
    protected Quote() {
    }

    public Quote(LocalDate date, BigDecimal price) {
        this.date = date;
        this.price = price;
    }

    public Quote(String id, LocalDate date, BigDecimal price) {
        super(id);
        this.date = date;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;

        Quote quote = (Quote) o;

        if (!date.isEqual(quote.date)) return false;
        if (price.compareTo(quote.price) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "date=" + date +
                ", price=" + price +
                ", entity=" + super.toString() +
                '}';
    }
}
