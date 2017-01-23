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
<html>
<head>
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

    </script>
</head>
<body>

<div class="main-content" id="main-content">
    <div class="container">
        <div class="inner-xs">
            <div class="page-header">
                <h2 class="page-title">
                    Product Comparison
                </h2>
            </div>
        </div><!-- /.section-page-title -->

        <div class="table-responsive inner-bottom-xs inner-top-xs">

            <table class="table table-bordered table-striped compare-list">
                <thead>
                <tr>
                    <td>&nbsp;</td>
                    <% int counterImage = 0; %>
                    <c:forEach items="${tableCompare}" var="list_image" varStatus="counterImage">
                    <td class="text-center" id="image<%=counterImage%>">
                        <div class="image-wrap">
                            <div data-product_id="39" class="remove-link" onclick="removeLink(<%=counterImage%>)"><i class="fa fa-times-circle"></i></div>
                            <img width="220" id="responsiveImageImage_${list_image.id}" height="154"  class="attachment-yith-woocompare-image responsiveImageImage" src="">
                        </div>
                        <p><strong>${list_image.name}</strong></p>
                    </td>
                        <script>
                            var el = document.getElementById("responsiveImageImage_${list_image.id}");
                            setImageName(${list_image.id},el);

                            function removeLink(index){
                                document.getElementById("image" + index).style.display = 'none';
                                document.getElementById("description" + index).style.display = 'none';
                                document.getElementById("length" + index).style.display = 'none';
                                document.getElementById("width" + index).style.display = 'none';
                                document.getElementById("depth" + index).style.display = 'none';
                                document.getElementById("sanded" + index).style.display = 'none';
                                document.getElementById("stock" + index).style.display = 'none';
                                document.getElementById("shop" + index).style.display = 'none';
                                document.getElementById("price" + index).style.display = 'none';
                                document.getElementById("price2" + index).style.display = 'none';
                            }


                        </script>
                        <%  counterImage++; %>
                    </c:forEach>
                </tr>
                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                <tr class="tr-add-to-cart">
                    <td>&nbsp;</td>
                    <% int counterShop = 0; %>
                    <c:forEach items="${tableCompare}" var="list_shop" varStatus="counterShop">
                    <td class="text-center" id="shop<%=counterShop%>">
                        <div class="add-cart-button">
                            <a class="le-button add_to_cart_button  product_type_simple" href="/viewproduct/product/${list_shop.id}">Add to cart</a>
                        </div>
                    </td>
                        <%  counterShop++; %>
                    </c:forEach>
                </tr>
                </sec:authorize>
                </thead>
                <tbody>
                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                <tr class="comparison-item price">
                    <th>Price</th>
                        <% int counterPrice = 0; %>
                        <c:forEach items="${tableCompare}" var="list_price" varStatus="counterPrice">
                            <td id="price<%=counterPrice%>" class="comparison-item-cell odd">
                                <span class="amount">$${list_price.price}</span>
                            </td>
                            <%  counterPrice++; %>
                        </c:forEach>
                </tr>
                </sec:authorize>

                <tr class="comparison-item description">
                    <th>Description</th>
                        <% int counterDescription = 0; %>
                        <c:forEach items="${tableCompare}" var="list_description" varStatus="counterDescription">
                            <td id="description<%=counterDescription%>" class="comparison-item-cell odd">
                                <p>${list_description.shortDescription}</p>
                            </td>
                            <%  counterDescription++; %>
                        </c:forEach>
                </tr>

                <tr class="comparison-item stock">
                    <th>Availability</th>
                    <% int counterstock = 0; %>
                    <c:forEach items="${tableCompare}" var="list_stock" varStatus="counterstock">
                    <td class="comparison-item-cell odd" id="stock<%=counterstock%>">
                        <span class="label label-success"><span class="">In stock</span></span>
                    </td>
                        <%  counterstock++; %>
                    </c:forEach>
                </tr>
                <%  int counterPrice = 0; %>
                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                <tr class="price repeated">
                    <th>Price</th>
                        <c:forEach items="${tableCompare}" var="list_price" varStatus="counterPrice">
                            <td id="price2<%=counterPrice%>" class="odd "><span class="amount">$${list_price.price}</span></td>
                            <%  counterPrice++; %>
                        </c:forEach>
                </tr>
                </sec:authorize>
                <tr class="comparison-item">
                        <th>Width</th>
                        <% int counterWidth = 0; %>
                        <c:forEach items="${tableCompare}" var="list_width" varStatus="counterWidth">
                            <td class="odd " id="width<%=counterWidth%>"><span class="amount">${list_width.width}</span></td>
                            <%  counterWidth++; %>
                        </c:forEach>
                </tr>
                <tr class="comparison-item">
                        <th>Length</th>
                        <% int counterLength = 0; %>
                        <c:forEach items="${tableCompare}" var="list_length" varStatus="counterLength">
                            <td class="odd " id="length<%=counterLength%>"><span class="amount">${list_length.length}</span></td>
                            <%  counterLength++; %>
                        </c:forEach>
                </tr>
                <tr class="comparison-item">
                        <th>Thickness</th>
                        <% int counterDepth = 0; %>
                        <c:forEach items="${tableCompare}" var="list_depth" varStatus="counterDepth">
                            <td class="odd " id="depth<%=counterDepth%>"><span class="amount">${list_depth.depth}</span></td>
                            <%  counterDepth++; %>
                        </c:forEach>
                </tr>
                <tr class="comparison-item">
                        <th>Sanded</th>
                        <% int counterSanded = 0; %>
                        <c:forEach items="${tableCompare}" var="list_sanded" varStatus="counterSanded">
                            <td class="odd " id="sanded<%=counterSanded%>"><span class="amount">
                                                            <c:choose>
                                                                <c:when test="${list_sanded.sanded}">
                                                                    sanded
                                                                </c:when>
                                                                <c:when test="${!list_coating.sanded}">
                                                                    unsanded
                                                                </c:when>
                                                            </c:choose>
                            </span></td>
                            <%  counterSanded++; %>
                        </c:forEach>
                </tr>

                        </tbody>
            </table>
        </div><!-- /.table-responsive -->
    </div><!-- /.container -->
</div>

</body>
</html>