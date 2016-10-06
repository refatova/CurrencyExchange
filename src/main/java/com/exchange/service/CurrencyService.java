package com.exchange.service;

import com.exchange.model.Currency;

import java.util.Date;
import java.util.List;

/**
 * Created by Saniye on 05.09.16.
 */
public interface CurrencyService {

    public void addCurrency(Currency currency);
    public List<Currency> getCurrencyByDate(Date date);
}
