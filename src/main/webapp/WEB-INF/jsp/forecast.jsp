<%--
  Created by IntelliJ IDEA.
  User: Saniye
  Date: 26.08.16
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather for today</title></head>
<body>

<h4> Добро пожаловать! </h4>
Поздравляем, вы <b>${visitorCount}</b> посетитель и можете выиграть поездку в столицу Java - Джакарту <br/>

<form method="post"><select multiple="multiple" size="10" name="select-country">
    <option value="2643743">London</option>
    <option value="5059248">Tokio</option>
    <option value="2950159">Berlin</option>
    <option value="2988507">Paris</option>
    <option value="696050">Kyiv</option>
    <option value="1248991">Colombo</option>
    <option value="2553604">Casablanka</option>
    <option value="6619707">Mumbai</option>
    <option value="2530335">Tangier</option>
</select>
    <input type="submit" value="Submit" name="submit">
</form>
<form method="post"><input type="number" size="10" value="" name="latitude">
    <input type="number" size="10" value="" name="longitude">
    <input type="submit" value="Submit" name="submit_coordinates">
</form>


</body>

</html>