@TypeDefs({
        @TypeDef(name = "CurrencyUnit", typeClass = PersistentCurrencyUnit.class),
        @TypeDef(name = "DateWithoutTZ", typeClass = PersistentLocalDate.class),
})
@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type=DateTime.class,
                value=DateTimeAdapter.class),
        @XmlJavaTypeAdapter(type=LocalDate.class,
                value=LocalDateAdapter.class),
        @XmlJavaTypeAdapter(type=CurrencyUnit.class,
                value=CurrencyUnitAdapter.class),
})
package com.cascaio.backend.v1.entity;

import com.cascaio.backend.v1.entity.adapter.CurrencyUnitAdapter;
import com.cascaio.backend.v1.entity.adapter.DateTimeAdapter;
import com.cascaio.backend.v1.entity.adapter.LocalDateAdapter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.jadira.usertype.moneyandcurrency.joda.PersistentCurrencyUnit;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
