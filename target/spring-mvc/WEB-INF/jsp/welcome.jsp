<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <title>CurrencyRate</title>
    <style type="text/css">
        body {
            background-image: url('http://crunchify.com/bg.png');
        }

        td, th {
            padding: 5px;
            padding-right: 5px;
            border: 1px solid black;
        }

        table {
            width: 100%
        }
    </style>
</head>
<body>
<table>
    <caption style="margin-bottom: 10px;font-size: 150%; font-family: Arial, Helvetica, sans-serif; ">Currency rate for
        <c:set var="today" value="${date}"/>
        <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></td> </caption>
    <tr>
        <th>Date</th>
        <th>Currency</th>
        <th>Buy</th>
        <th>Sale</th>
    </tr>
    <c:forEach items="${list}" var="currency">
        <tr>
            <td>
                <c:set var="date" value="${currency.getDate()}"/>
                <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
            <td>
                <c:out value="${currency.getCurrencyType()}"></c:out></td>
            <td><fmt:setLocale value="en_US"/>
                <c:set var="buy" value="${currency.getBuy()}"/>
                <fmt:formatNumber value="${buy}" maxFractionDigits="3" /></td>
            <td>
                <c:set var="sell" value="${currency.getSell()}"/>
                <fmt:formatNumber value="${sell}" maxFractionDigits="3"/></td>
        </tr>
    </c:forEach>

</table>

<form method="post">
    <select  size="1" name="operation" >
        <option value="Buy">Buy</option>
        <option value="Sell">Sell</option>
    </select>

    <input type="number" size="10" value="" name="amount">
    <select  size="1" name="currency">
        <option value="EUR">EUR</option>
        <option value="USD">USD</option>
        <option value="RUR">RUR</option>
        <option value="BTC">BTC</option>
    </select>
    <input type="text" size="14" value="" name="contacts">
    <input type="submit" value="Submit" name="submit">
</form>

</body>
</html>