<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="jquery-1.12.4.min.js"></script>
    <title>Orders</title>
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

    <script>
        $
    </script>
</head>
<body>
<table>
    <caption style="margin-bottom: 10px;font-size: 150%; font-family: Arial, Helvetica, sans-serif; ">Open orders
        <c:set var="today" value="${date}"/>
        <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/></td> </caption>
    <tr>
        <th>Date</th>
        <th>Currency</th>
        <th>Amount</th>
        <th>Operation</th>
        <th>Contacts</th>

    </tr>
    <c:forEach items="${list}" var="orders">
        <tr>
            <td>
                <c:set var="date" value="${orders.getDate()}"/>
                <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${orders.getCurrency().getCurrencyType()}"></c:out></td>

            <td><fmt:setLocale value="en_US"/>
                <c:set var="amount" value="${orders.getAmount()}"/>
                <fmt:formatNumber value="${amount}" maxFractionDigits="3"/></td>
            <td>
                <c:out value="${orders.getOperationType()}"></c:out>
            </td>
            <td>
                <input type="button" value="Show" name="submit" onclick='document.getElementById("div").style.display="block";
document.getElementsByName("submit").style.visibility = "hidden";'>
                <div style='display:none' id="div"><c:out value="${orders.getContacts()}"></c:out></div>
            </td>
                <%--<td>--%>
                <%--<c:set var="sell" value="${currency.getSell()}"/>--%>
                <%--<fmt:formatNumber value="${sell}" maxFractionDigits="3"/></td>--%>
        </tr>
    </c:forEach>

</table>

</body>
</html>
