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
<script type="text/javascript" src="/js/offer.js"></script>

<div>
    <h1>Order is accepted!</h1>
    <p>Thank you for your made to order! In a letter sent to your email address. In the near future we will contact you.</p>
    <table class="table" style="width:300px">
    <c:forEach items="${listOffer}" var="list_object" varStatus="counter">
        <tr>
            <th>
                ${list_object.product.name}
            </th>
        </tr>
        <tr>
            <th>
        ${list_object.count} х ${list_object.product.price}
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