<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
                <input type="button" value="Show" name="${orders.getOrderId()}" class="contacts"/>
                <div class="contactField">
                    <%--<c:out value="${orders.getContacts()}"></c:out>--%>
                </div>
            </td>
        </tr>
    </c:forEach>

</table>
<script>


$(".contacts").click(function() {
    var id=$(this).attr("name");
    var button=$(this)

    $.ajax({
        type:'get',
        url: '/api/contacts',
        data:{'id':id},
        success: function (data) {
            console.log(button);
            button.next().html(data);
            $(button).hide();
        }});
});


</script>
</body>
</html>
