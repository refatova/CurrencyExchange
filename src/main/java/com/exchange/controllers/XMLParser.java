package com.exchange.controllers;

import com.exchange.model.Currency;
import com.exchange.model.CurrencyType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.tags.form.SelectTag;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * Created by Saniye on 03.09.16.
 */


@Component
public class XMLParser extends DefaultHandler {

    List<Currency> cur = new ArrayList();
    String thisElement = "";

    public List<Currency> getResult() {
        return cur;
    }


    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("exchangerate")) {
            Currency currency = new Currency();
            currency.setDate(new Date());
            for (int i = 0; i < atts.getLength(); i++) {
                String atribute = atts.getLocalName(i);
                if (atribute.equals("ccy")) currency.setCurrencyType(CurrencyType.valueOf(atts.getValue(i)));
                else if (atribute.equals("buy")) currency.setBuy(Double.parseDouble(atts.getValue(i)));
                else if (atribute.equals("sale")) currency.setSell(Double.parseDouble(atts.getValue(i)));
            }
            cur.add(currency);
        }

    }


    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";

    }

}

