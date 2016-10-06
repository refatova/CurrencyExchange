<html>
<head>
    <meta charset="UTF-8">
    <title>Currency exchange</title>
    <style>
        td,th{
            padding: 5px;
            padding-right: 5px;
            border: 1px solid black;
        }
    </style>
</head>
<body>



<table style="width: 50%"><caption>Currency exchange for today</caption>
    <tr><th>Currency</th>
        <th>Buy</th>
        <th>Sale</th>

    </tr>
</table>

<br><br>
<h2>Currency exchange calculate</h2>
<form method="post" >
    <input type="number" size="5" name="amount">
    <select  size="1" name="currency">
        <option value="UAH">UAH</option>
        <option value="USD">USD</option>
        <option value="EUR">EUR</option>
        <option value="RUR">RUR</option>
    </select>
    <input type="submit" value="Submit" name="submit">
</form>

</body>
</html>