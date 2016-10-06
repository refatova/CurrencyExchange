package com.exchange.model;


import javax.persistence.*;
import javax.persistence.EnumType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Saniye on 04.10.16.
 */

@Entity
@Table(name = "Orders")
public class Orders implements Serializable {
    public Orders(double amount, OperationType operationType) {
        this.amount = amount;
        this.operationType = operationType;

    }

    public Orders(double amount, Currency currency, OperationType operationType) {
        this.amount = amount;
        this.currency = currency;
        this.operationType = operationType;

    }

    public Orders(double amount, Currency currency, OperationType operationType, Date date, Status status, int orderId,String contacts) {
        this.amount = amount;
        this.currency = currency;
        this.operationType = operationType;
        this.date = date;
        this.status = status;
        this.orderId = orderId;
        this.contacts=contacts;


    }

    public Orders() {
    }

    @Column(name = "Amount")
    private double amount;


    @Enumerated(EnumType.STRING)
    @Column(name = "OperationType")
    private OperationType operationType;


    @Column(name = "Date")
    Date date;

    @Column(name = "Contacts")
   private String contacts;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int orderId;


    @ManyToOne
    @JoinColumn(name = "CurrencyId")
    private Currency currency;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (Double.compare(orders.amount, amount) != 0) return false;
        if (orderId != orders.orderId) return false;
        if (operationType != orders.operationType) return false;
        if (date != null ? !date.equals(orders.date) : orders.date != null) return false;
        if (status != orders.status) return false;
        return currency != null ? currency.equals(orders.currency) : orders.currency == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + orderId;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "amount=" + amount +
                ", operationType=" + operationType +
                ", date=" + date +
                ", status=" + status +
                ", orderId=" + orderId +
                ", currency=" + currency +
                '}';
    }
}
