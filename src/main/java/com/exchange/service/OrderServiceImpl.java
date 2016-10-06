package com.exchange.service;

import com.exchange.dao.OrdersDAO;
import com.exchange.model.OperationType;
import com.exchange.model.Orders;

import com.exchange.model.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Saniye on 04.10.16.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private OrdersDAO ordersDAO;


    @Autowired
    public void setCurrencyDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }


    @Override
    @Transactional
    public void addNewOrder(CurrencyType currency, double amount, OperationType operationType,String contacts) {
        ordersDAO.addNewOrder(currency,amount,operationType,contacts);
    }

    @Override
    @Transactional
    public List<Orders> getOrder(CurrencyType currencyType, OperationType operationType) {
        return ordersDAO.getOrder(currencyType,operationType);
    }
}
