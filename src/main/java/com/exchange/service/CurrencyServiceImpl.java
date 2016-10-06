package com.exchange.service;

import com.exchange.dao.CurrencyDAO;
import com.exchange.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Saniye on 05.09.16.
 */

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyDAO currencyDAO;

    @Autowired
    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }


    @Override
    @Transactional
    public void addCurrency(Currency currency) {
        this.currencyDAO.addCurrency(currency);
    }

    @Override
    @Transactional
    public List<Currency> getCurrencyByDate(Date date) {
        return this.currencyDAO.getCurrencyByDate(date);
    }
}
