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

<link href="<%=JspConstants.APP_PATH%>/css/shoping.css" rel="stylesheet" type="text/css" >
<link href="<%=JspConstants.APP_PATH%>/css/compare.css" rel="stylesheet" type="text/css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>

<script type="text/javascript" src="/js/galary.js"></script>


<div id="single-product">
    <div class="container">
        <input type="hidden" id = "idPlywood" value="${listPlywood.id}">
        <div class="no-margin col-xs-12 col-sm-6 col-md-5 gallery-holder">
            <div class="product-item-holder size-big single-product-gallery small-gallery">

                <div id="owl-single-product" class="owl-carousel owl-theme" style="opacity: 1; display: block;">
                    <div class="owl-wrapper-outer gallaryMain" id = "gallaryMain${listPlywood.id}">
                    </div><!-- /.single-product-gallery-item -->
                </div><!-- /.single-product-slider -->


                <div class="single-product-gallery-thumbs gallery-thumbs">

                    <div id="owl-single-product-thumbnails" class="owl-carousel owl-theme" style="opacity: 1; display: block;">
                        <div class="owl-wrapper-outer gallary" id = "gallary${listPlywood.id}"></div>


                    </div><!-- /#owl-single-product-thumbnails -->

                    <div class="nav-holder left hidden-xs">
                        <a class="prev-btn slider-prev" data-target="#owl-single-product-thumbnails" href="#prev"></a>
                    </div><!-- /.nav-holder -->

                    <div class="nav-holder right hidden-xs">
                        <a class="next-btn slider-next" data-target="#owl-single-product-thumbnails" href="#next"></a>
                    </div><!-- /.nav-holder -->

                </div><!-- /.gallery-thumbs -->

            </div><!-- /.single-product-gallery -->
        </div><!-- /.gallery-holder -->
        <div class="no-margin col-xs-12 col-sm-7 body-holder">
            <div class="body">
                <div class="availability"><label>Availability:</label><span class="available">  in stock</span></div>

                <div class="title"><a href="#">${listPlywood.name}</a></div>
                <div class="brand">${listPlywood.type}</div>

                <div class="buttons-holder" style="height: 20px;">
                    <input type="checkbox" style="margin-top: -10px; margin-left: 50px;" class="checkBoxField" id="check_compare_${listPlywood.id}"><label style="argin-top: -15px;" class="labelCompare">add to compare list</label>
                </div>

                <div class="excerpt">
                    <p>${listPlywood.shortDescription}</p>
                </div>

                <div class="prices">
                    <div class="price-current">$${listPlywood.price}</div>
                </div>
                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                    <div class="qnt-holder">
                        <div class="le-quantity" id = "le-quantity${listPlywood.id}" style="float: left; max-width:100px; display: none">
                            <form>
                                <a class="minus minusShoppingCart" id="minus_${listPlywood.id}" href="#reduce"></a>
                                <input id="countCart_${listPlywood.id}" name="quantity" readonly="readonly" type="text" value="0">
                                <a class="plus plusShoppingCart" id="plus_${listPlywood.id}" href="#add"></a>
                            </form>
                        </div>
                        <button id="addto-cart${listPlywood.id}"  class="ShoppingCar addToCart le-button huge">add to cart</button>
                        <a class="title" id="viewShop_${listPlywood.id}" href="/shoping/view">View Cart</a>
                    </div><!-- /.qnt-holder --><!-- /.qnt-holder -->
                </sec:authorize>
            </div><!-- /.body -->

        </div><!-- /.body-holder -->
    </div><!-- /.container -->
</div>

<section id="single-product-tab">
    <div class="container">
        <div class="tab-holder">

            <ul class="nav nav-tabs simple">
                <li class="active"><a href="#description" data-toggle="tab">Description</a></li>
                <li class=""><a href="#additional-info" data-toggle="tab">Additional Information</a></li>
            </ul><!-- /.nav-tabs -->

            <div class="tab-content">
                <div class="tab-pane active" id="description">
                    <p>${listPlywood.shortDescription}</p>
                    <div class="meta-row">
                        <div class="inline">
                            <label>SKU: ${listPlywood.productId}</label>
                        </div><!-- /.inline -->

                        <span class="seperator">/</span>

                        <div class="inline">
                            <label>categories: <a href="#">${listPlywood.type}</a></label>
                        </div><!-- /.inline -->
                        <span class="seperator">/</span>
                    </div><!-- /.meta-row -->
                </div><!-- /.tab-pane #description -->

                <div class="tab-pane" id="additional-info">
                    <ul class="tabled-data">
                        <li>
                            <label>length</label>
                            <div class="value">${listPlywood.length} cm</div>
                        </li>
                        <li>
                            <label>width</label>
                            <div class="value">${listPlywood.width} cm</div>
                        </li>
                        <li>
                            <label>depth</label>
                            <div class="value">${listPlywood.depth} cm</div>
                        </li>
                        <li>
                            <label>color</label>
                            <div class="value">${listPlywood.colorCoating}</div>
                        </li>
                        <li>
                            <label>coating</label>
                            <div class="value">
                                <c:choose>
                                    <c:when test="${listPlywood.coating}">
                                        yes
                                    </c:when>
                                    <c:when test="${!listPlywood.coating}">
                                        no
                                    </c:when>
                                </c:choose>
                            </div>
                        </li>
                        <li>
                            <label>Water resistance (FK or -)</label>
                            <div class="value">
                                <c:choose>
                                    <c:when test="${listPlywood.waterResistance}">
                                        FK
                                    </c:when>
                                    <c:when test="${!listPlywood.waterResistance}">
                                        -
                                    </c:when>
                                </c:choose>
                            </div>
                        </li>
                        <li>
                            <label>Sanded or unsanded</label>
                            <div class="value">
                                <c:choose>
                                    <c:when test="${listPlywood.sanded}">
                                        sanded
                                    </c:when>
                                    <c:when test="${!listPlywood.sanded}">
                                        unsanded
                                    </c:when>
                                </c:choose>
                            </div>
                        </li>
                    </ul><!-- /.tabled-data -->

                    <div class="meta-row">
                        <div class="inline">
                            <label>SKU:</label>
                            <span>${listPlywood.productId}</span>
                        </div><!-- /.inline -->

                        <span class="seperator">/</span>

                        <div class="inline">
                            <label>categories:</label>
                            <span><a href="#">${listPlywood.type}</a></span>
                        </div><!-- /.inline -->
                    </div><!-- /.meta-row -->
                </div><!-- /.tab-pane #additional-info -->

            </div><!-- /.tab-content -->

        </div><!-- /.tab-holder -->
    </div><!-- /.container -->
</section>



<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();

        var urlShoping = '/shoping/cart/';
        var productId = document.getElementById("idPlywood").value;;

        var urlObjects = '/compare/isProduct/' + productId;
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType:'json',
            async: false
        }).done(function(checkProduct) {
            var checkBoxField = document.getElementById("check_compare_" + productId);
            if(checkProduct!= undefined && checkProduct){
                checkBoxField.checked = "checked";
            } else{
                checkBoxField.checked = "";
            }
        });


        var urlObjects = urlShoping + productId;

        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: urlObjects,
            dataType:'json',
            async: false
        }).done(function(product) {
            var quant = $(".le-quantity")[0];
            var viewShop = document.getElementById("viewShop_" + productId);
            var addToCart = $(".ShoppingCar")[0];
            if(product!= undefined && product.check){
                quant.style.display = "block";
                addToCart.textContent = "Remove to cart";
                viewShop.style.display = 'block';
                var countFeatureShoppingCar = document.getElementById("countCart_" + productId);
                countFeatureShoppingCar.value =  product.count;
            } else{
                quant.style.display = "none";
                addToCart.textContent = "Add to cart";
                viewShop.style.display = 'none';
            }
        });
    });

    initGalary();
</script>

<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/css_browser_selector.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/echo.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/jquery.easing-1.3.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/jquery.prettyPhoto.min.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/jquery.customSelect.min.js"></script>