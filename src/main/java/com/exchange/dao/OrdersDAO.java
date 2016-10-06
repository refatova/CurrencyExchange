package com.exchange.dao;

import com.exchange.model.OperationType;
import com.exchange.model.Orders;
import com.exchange.model.CurrencyType;

import java.util.List;

/**
 * Created by Saniye on 04.10.16.
 */
public interface OrdersDAO {
    public void addNewOrder(CurrencyType currency,double amount, OperationType operationType,String contacts);
    public List<Orders> getOrder(CurrencyType currencyType, OperationType operationType);
}
