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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Saniye on 27.08.16.
 */

@Controller
@RequestMapping("/exchange")
public class MainController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    OrderService orderService;

    @Autowired
    @Qualifier("apiController")
    APIController apiController;


    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postMethod(@RequestParam("operation") String operationType, @RequestParam("amount") Double amount, @RequestParam("currency") String currencyType, @RequestParam("contacts") String contacts) {
        CurrencyType currency = CurrencyType.valueOf(currencyType.toUpperCase());
        OperationType operation = OperationType.valueOf(operationType.toUpperCase());
        orderService.addNewOrder(currency, amount, operation,contacts);
        List<Orders> ordersList;
       if(operation.name().equals("BUY"))
        ordersList = orderService.getOrder(currency, OperationType.SELL);
        else  ordersList = orderService.getOrder(currency, OperationType.BUY);
        ModelAndView mv = new ModelAndView("orders");
        mv.addObject("date", new Date());
        mv.addObject("list", ordersList);
        return mv;
    }


}
