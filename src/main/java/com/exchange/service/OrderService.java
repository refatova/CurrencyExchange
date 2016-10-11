package com.exchange.service;

import com.exchange.model.OperationType;
import com.exchange.model.Orders;
import com.exchange.model.CurrencyType;

import java.util.List;

/**
 * Created by Saniye on 04.10.16.
 */
public interface OrderService {
    public void addNewOrder(Orders orders, CurrencyType currency);

    public List<Orders> getOrder(CurrencyType currencyType, OperationType operationType);

    public String getContactsByOrderId(int id);
}
