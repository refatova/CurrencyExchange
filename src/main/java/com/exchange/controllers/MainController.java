package com.exchange.controllers;

import com.exchange.model.Currency;
import com.exchange.model.CurrencyType;
import com.exchange.model.OperationType;
import com.exchange.model.Orders;
import com.exchange.service.CurrencyService;
import com.exchange.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Saniye on 27.08.16.
 */

@Controller
//@RequestMapping("/exchange")
public class MainController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    OrderService orderService;

    @Autowired
    @Qualifier("apiController")
    APIController apiController;


    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    public ModelAndView getMethod(HttpServletRequest req, HttpServletResponse resp) {
        List<Currency> currencyList;
        currencyList = currencyService.getCurrencyByDate(new Date());
        if (currencyList.isEmpty()) {
            try {
                currencyList = apiController.getExchangeRatesFromApi();
                for (Currency cur : currencyList
                        ) {
                    currencyService.addCurrency(cur);
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("date", new Date());
        mv.addObject("list", currencyList);
        return mv;
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public ModelAndView postMethod(@RequestParam("operation") String operationType, @RequestParam("amount") Double amount, @RequestParam("currency") String currencyType, @RequestParam("contacts") String contacts) {
        CurrencyType currency = CurrencyType.valueOf(currencyType.toUpperCase());
        OperationType operation = OperationType.valueOf(operationType.toUpperCase());
        Orders currentOrder = new Orders(amount, operation, contacts);
        orderService.addNewOrder(currentOrder, currency);
        List<Orders> ordersList;
        if (operation.name().equals("BUY"))
            ordersList = orderService.getOrder(currency, OperationType.SELL);
        else ordersList = orderService.getOrder(currency, OperationType.BUY);

//if(ordersList.size()>0)
//        findCloseNumber(ordersList,currentOrder.getAmount());
        ModelAndView mv = new ModelAndView("orders");
        mv.addObject("date", new Date());
        mv.addObject("list", findOrdersSet(ordersList, currentOrder));
        return mv;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseBody
    public String getContact(@RequestParam("id") int id) {
        return orderService.getContactsByOrderId(id);

    }

    private List<Orders> findOrdersSet(List<Orders> list, Orders currentOrder) {
        List<Orders> resultList = new ArrayList<Orders>();
        for (Orders o : list
                ) {
            if (o.getAmount() == currentOrder.getAmount()) {
                resultList.add(o);
                return resultList;
            }
        }
        List<Orders> sorted = sortOrdersList(list);
        if (list.size() > 0)
            resultList.add(findCloseNumber(list, currentOrder.getAmount()));
        return sorted;
    }

    private List<Orders> sortOrdersList(List<Orders> list) {
        Orders bufer;
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0 && list.get(j - 1).getAmount() > list.get(j).getAmount(); j--) {
                bufer = list.get(j - 1);
                list.set(j - 1, list.get(j));
                list.set(j, bufer);
            }
        }
        return list;
    }

    private Orders findCloseNumber(List<Orders> list, double currentAmount) {
        Map<Orders, Double> raznitca = new TreeMap<Orders, Double>(new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                if (o1.getAmount() == o2.getAmount()) return 0;
                else if (o1.getAmount() > o2.getAmount()) return 1;
                else return -1;
            }
        });
        for (Orders o : list
                ) {
            raznitca.put(o, Math.abs(currentAmount - o.getAmount()));
        }
        return (Orders) raznitca.keySet().toArray()[0];
    }


}
