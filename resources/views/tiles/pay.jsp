<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.08.2016
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<li>
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <i data-toggle="tooltip" data-placement="tooltip" title="pay" id="pay" class="material-icons">shopping_cart</i>
    </a>
</li>
<div id="shoppingCart" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close btn btn-default" type="button" data-dismiss="modal" >Close</button>
                <h4 class="modal-title">Shopping cart</h4>
            </div>
            <div class="modal-body">
                <div>
                    <table>
                        <tr>
                            <td>
                <table class = "table" id="tableSumPay" style="width:300px">
                    <tr>
                        <th>Total amount: </th>
                        <th id="sumPay">1000</th>
                    </tr>
                    <tr>
                        <th><button type="button" id="offer" class="btn btn-success" style="margin-left:auto; margin-right:auto;">To order</button></th>
                    </tr>
                </table>
                        </td>
                        <td>
                            <div class="shopingIcon">
                            </div>
                        </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
