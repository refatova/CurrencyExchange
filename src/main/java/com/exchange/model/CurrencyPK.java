package com.exchange.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Saniye on 05.09.16.
 */

@Embeddable
public class CurrencyPK implements Serializable {

//    @Column(name="date")
//    private Date date;
//
//    @Column(name="currency")
//    private String currency;

    protected Date date;
    protected String currency;

    public CurrencyPK(Date date, String currency) {
        this.date = date;
        this.currency = currency;
    }

    public CurrencyPK() {


    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyPK that = (CurrencyPK) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return currency != null ? currency.equals(that.currency) : that.currency == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
