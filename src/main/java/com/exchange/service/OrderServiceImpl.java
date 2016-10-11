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
    public void addNewOrder(Orders orders, CurrencyType currency) {
        ordersDAO.addNewOrder(orders, currency);
    }

    @Override
    @Transactional
    public List<Orders> getOrder(CurrencyType currencyType, OperationType operationType) {
        return ordersDAO.getOrder(currencyType, operationType);
    }

    @Override
    @Transactional
    public String getContactsByOrderId(int id) {
        return ordersDAO.getContactsByOrderId(id);
    }
}
