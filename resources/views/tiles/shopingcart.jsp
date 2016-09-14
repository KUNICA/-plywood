<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.09.2016
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="MediaCenter, Template, eCommerce">
<meta name="robots" content="all">

<!-- Bootstrap Core CSS -->

<!-- Customizable CSS -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/main.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/green.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/owl.carousel.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/owl.transitions.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/animate.min.css">

<!-- Demo Purpose Only. Should be removed in production -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/config.css">

<link href="<%=JspConstants.APP_PATH%>/css/green.css" rel="alternate stylesheet" title="Green color">
<link href="<%=JspConstants.APP_PATH%>/css/blue.css" rel="alternate stylesheet" title="Blue color">
<link href="<%=JspConstants.APP_PATH%>/css/red.css" rel="alternate stylesheet" title="Red color">
<link href="<%=JspConstants.APP_PATH%>/css/orange.css" rel="alternate stylesheet" title="Orange color">
<link href="<%=JspConstants.APP_PATH%>/css/navy.css" rel="alternate stylesheet" title="Navy color">
<link href="<%=JspConstants.APP_PATH%>/css/dark-green.css" rel="alternate stylesheet" title="Darkgreen color">
<link href="<%=JspConstants.APP_PATH%>/less/sidebar.less" rel="alternate stylesheet">
<!-- Demo Purpose Only. Should be removed in production : END -->

<!-- Fonts -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>

<!-- Icons/Glyphs -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/font-awesome.min.css">

<!-- HTML5 elements and media queries Support for IE8 : HTML5 shim and Respond.js -->
<!--[if lt IE 9]>
<script src="/js/html5shiv.js"></script>
<script src="/js/respond.min.js"></script>
<![endif]-->

<link href="<%=JspConstants.APP_PATH%>/css/rules.css" rel="stylesheet" type="text/css" >
<link href="<%=JspConstants.APP_PATH%>/css/shoping.css" rel="stylesheet" type="text/css" >
<link href="<%=JspConstants.APP_PATH%>/css/compare.css" rel="stylesheet" type="text/css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- JavaScripts placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>
<script type="text/javascript">

    function setImageName(id,element) {
        jQuery.ajax({
            type: "GET",
            url: "/pagination/imgPatch/" + id,
            async: false
        }).done(function (m_url) {
            element.src = "/images/product/" + m_url.img;
        });
    }

    function plusTotalPrice(id){
        var myproduct_price = document.getElementById("myproduct_price" + id);
        var total1 = document.getElementById("totalPrice1");
        var total2 = document.getElementById("totalPrice2");
        var total = document.getElementById("total");
        total.value = parseFloat(total.value) + parseFloat(myproduct_price.value);
        total1.textContent = '$' + total.value;
        total2.textContent = '$' + total.value;
    }

    function minusTotalPrice(id){
        var count = document.getElementById("countCart_" + id);
        var myproduct_price = document.getElementById("myproduct_price" + id);
        var total1 = document.getElementById("totalPrice1");
        var total2 = document.getElementById("totalPrice2");
        var total = document.getElementById("total");
        if(count.value>0) {
            total.value = parseFloat(total.value) - parseFloat(myproduct_price.value);
            total1.textContent = '$' + total.value;
            total2.textContent = '$' + total.value;
        }
    }

    function removeShop(id){
        var count = document.getElementById("countCart_" + id);
        var myproduct_price = document.getElementById("myproduct_price" + id);
        var total1 = document.getElementById("totalPrice1");
        var total2 = document.getElementById("totalPrice2");
        var total = document.getElementById("total");
        if(count.value>0) {
            total.value = parseFloat(total.value) - (parseFloat(count.value) * parseFloat(myproduct_price.value));
            total1.textContent = '$' + total.value;
            total2.textContent = '$' + total.value;
        }
        var element = document.getElementById("countShop" + id);
        element.style.display = "none";
    }

</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section id="cart-page">
    <div class="container">
        <!-- ========================================= CONTENT ========================================= -->
<% int counterProduct = 0; %>
<c:forEach items="${listCart}" var="product" varStatus="counterProduct">
    <input type="hidden" id ="myproduct_price${product.id}" value="${product.price}">
        <div class="col-xs-12 col-md-9 items-holder no-margin" id="countShop${product.id}">

            <div class="row no-margin cart-item">
                <div class="col-xs-12 col-sm-2 no-margin">
                    <a href="/viewproduct/product/${product.id}" class="thumb-holder">
                        <img id="responsiveImageImage_${product.id}" class="lazy" alt="" src="">
                    </a>
                    <script>
                        var el = document.getElementById("responsiveImageImage_${product.id}");
                        setImageName(${product.id},el);
                    </script>
                </div>

                <div class="col-xs-12 col-sm-5 ">
                    <div class="title">
                        <a href="/viewproduct/product/${product.id}">${product.name}</a>
                    </div>
                    <div class="brand">${product.type}</div>
                </div>

                <div class="col-xs-12 col-sm-3 no-margin">
                    <div class="quantity">
                        <div class="le-quantity" id = "le-quantity${product.id}">
                            <form>
                                <a class="minus minusShoppingCart" id="minus_${product.id}" onclick="minusTotalPrice(${product.id});" href="#reduce"></a>
                                <input id="countCart_${product.id}" name="quantity" readonly="readonly" type="text" value="${product.count}">
                                <a class="plus plusShoppingCart" id="plus_${product.id}" onclick="plusTotalPrice(${product.id});" href="#add"></a>
                            </form>

                        </div>
                    </div>
                </div>

                <div class="col-xs-12 col-sm-2 no-margin">
                    <div class="price">
                        $${product.price}
                    </div>
                    <button id="addto-cart${product.id}" onclick="removeShop(${product.id})" class="close-btn ShoppingCar"></button>
                    <input type="hidden" id="viewShop_${product.id}" />
                </div>
            </div><!-- /.cart-item -->
            <%  counterProduct++; %>
        </div>
        </c:forEach>
        <!-- ========================================= CONTENT : END ========================================= -->

        <!-- ========================================= SIDEBAR ========================================= -->

        <div class="col-xs-12 col-md-3 no-margin sidebar ">
            <div class="widget cart-summary">
                <h1 class="border">shopping cart</h1>
                <div class="body">
                    <ul class="tabled-data no-border inverse-bold">
                        <li>
                            <label>cart subtotal</label>
                            <div id = "totalPrice1" class="value pull-right">$${totalPrice}</div>
                            <input type="hidden" id = "total" value = "${totalPrice}">
                        </li>
                        <li>
                            <label>shipping</label>
                            <div class="value pull-right">free shipping</div>
                        </li>
                    </ul>
                    <ul id="total-price" class="tabled-data inverse-bold no-border">
                        <li>
                            <label>order total</label>
                            <div id = "totalPrice2" class="value pull-right">$${totalPrice}</div>
                        </li>
                    </ul>
                    <div class="buttons-holder">
                        <button type="button" id="offer"  class="le-button big">checkout</button>
                        <button type="button" id="offerPrint" class="btn btn-info" title = "Do you already have an order. Go and click complete button">Offer</button>
                        <button type="button" id="print" class="btn btn-info">Print</button>
                        <a class="simple-link block" href="/home">continue shopping</a>
                    </div>
                </div>
            </div><!-- /.widget -->

        </div><!-- /.sidebar -->

        <!-- ========================================= SIDEBAR : END ========================================= -->
    </div>
</section>
</body>
</html>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();


        var buttonOffer = document.getElementById("offer");
        var buttonPrint = document.getElementById("print");
        var offerPrint = document.getElementById("offerPrint");

        var urlObjects = "/shoping/products";
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType:'json',
            async: false
        }).done(function( products ) {
        jQuery.ajax({
            type: "POST",
            url: "/offer/isOffer",
            dataType:'json',
            async: false
        }).done(function( nocheck ) {
            if(nocheck && products!=undefined && products.length>0){
                 buttonOffer.style.display = "block";
                buttonPrint.style.display = "block";
                offerPrint.style.display = "none";
            }else if(!nocheck){
                offerPrint.style.display = "block";
                buttonOffer.style.display = "none";
                buttonPrint.style.display = "none";
            }
        });

        });
    });

</script>
