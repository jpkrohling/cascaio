@TypeDefs({
        @TypeDef(name = "CurrencyUnit", typeClass = PersistentCurrencyUnit.class),
        @TypeDef(name = "DateWithoutTZ", typeClass = PersistentLocalDate.class),
})
package com.cascaio.backend.v1.entity;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDate;
import org.jadira.usertype.moneyandcurrency.joda.PersistentCurrencyUnit;
