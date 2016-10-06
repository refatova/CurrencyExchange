package com.exchange.model;


import javax.persistence.*;
import javax.persistence.EnumType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Saniye on 03.09.16.
 */

@Entity
@Table(name = "Currency")
public class Currency  implements Serializable{

    public Currency() {
    }

    public Currency(int id, CurrencyType currencyType, Date date, double buy, double sell) {
        this.id = id;
        this.currencyType = currencyType;
        this.date = date;
        this.buy = buy;
        this.sell = sell;
    }

    @Id
    @GeneratedValue
    @Column(name="CurID")
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyType currencyType;


    @Column(name="date")
    private Date date;

    @PrePersist
    public void prePersist(){
        if (getDate() == null){
          setDate(new Date());
        }
    }

    private double buy;
    private double sell;


    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (id != currency.id) return false;
        if (Double.compare(currency.buy, buy) != 0) return false;
        if (Double.compare(currency.sell, sell) != 0) return false;
        if (currencyType != currency.currencyType) return false;
        return date != null ? date.equals(currency.date) : currency.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (currencyType != null ? currencyType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(buy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sell);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyType=" + currencyType +
                ", date=" + date +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
