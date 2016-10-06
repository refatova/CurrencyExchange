package com.exchange.dao;

import com.exchange.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Saniye on 04.10.16.
 */
@Component
public class OrdersDAOImpl implements OrdersDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addNewOrder(CurrencyType currency, double amount, OperationType operationType, String contacts) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders orders = new Orders(amount, operationType);
        orders.setStatus(Status.OPEN);
        orders.setDate(new Date());
        orders.setContacts(contacts);
        Currency currency1 = (Currency) sessionFactory.getCurrentSession().createQuery("from Currency where date=  :date and currency= :cur").setDate("date", new Date()).setString("cur", currency.name()).list().get(0);
        orders.setCurrency(currency1);
        session.persist(orders);
    }

    @Override
    public List<Orders> getOrder(CurrencyType currencyType, OperationType operationType) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("select new Orders(orders.amount, orders.currency, orders.operationType, orders.date, orders.status, orders.orderId, orders.contacts) from Orders orders,Currency currency where orders.currency=currency.id and orders.date= ? and orders.operationType= ? and currency.currencyType= ?");
        query.setDate(0, new Date());
        query.setString(1, operationType.name());
        query.setString(2, currencyType.name());
        List list = query.list();
        return list;
    }
}
