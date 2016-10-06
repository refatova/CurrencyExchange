package com.exchange.dao;

import com.exchange.model.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

/**
 * Created by Saniye on 05.09.16.
 */


@Component
public class CurrencyDAOImpl implements CurrencyDAO {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addCurrency(Currency currency) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(currency);

    }

    @Override
    public List getCurrencyByDate(Date date) {
        return sessionFactory.getCurrentSession().createQuery("from Currency where date=  :date").setDate("date",date).list();
    }

}
