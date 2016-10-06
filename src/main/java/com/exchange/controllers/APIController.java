package com.exchange.controllers;

import com.exchange.model.Currency;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.transaction.Transactional;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Saniye on 03.09.16.
 */

@Component
public class APIController {
    int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }


    @Transactional
    private String getFromAPI() throws IOException {
        URL apiRequest = new URL("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=3");
        HttpURLConnection http = (HttpURLConnection) apiRequest.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(http.getInputStream()));
        String inputLine;

        StringBuffer weatherAPIresponse = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            weatherAPIresponse.append(inputLine);
        }
        in.close();
        return weatherAPIresponse.toString();

    }

    public  List<Currency> getExchangeRatesFromApi() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLParser xml_parser = new XMLParser();
        String apiResponse = getFromAPI();
        parser.parse(new InputSource(new StringReader(apiResponse)), xml_parser);
        List<Currency> lis = xml_parser.getResult();
        return lis;
    }
}
