<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.08.2016
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/offer.js"></script>

<div>
    <h1>Заказ принят!</h1>
    <p>Благодарим Вас за сделаный заказ! На Ваш email отправлено письмо. В ближайшее время мы свяжемся с Вами.</p>
    <table class="table" style="width:300px">
    <c:forEach items="${listOffer}" var="list_object" varStatus="counter">
        <tr>
            <th>
                ${list_object.nameImage}
            </th>
        </tr>
        <tr>
            <th>
                    ${list_object.price}
            </th>
        </tr>
    </c:forEach>
        <tr>
        <th>The total amount of the order: </th>
        <th>${totalPrice}</th>
    </tr>
        <tr>
            <button type="button" id="removeOffer" class="btn btn-success" style="margin-left: auto; margin-right: auto;">Отказаться</button>
        </tr>
    </table>
</div>