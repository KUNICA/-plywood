<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.08.2016
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="container no-padding">

        <div class="col-xs-12 col-sm-12 col-md-3 logo-holder">
            <!-- ============================================================= LOGO ============================================================= -->
            <div class="logo">
                <a href="/home">
                    <img alt="logo" src="/images/logo.png" width="233" height="54"/>
                    <!--<object id="sp" type="image/svg+xml" data="assets/images/logo.svg" width="233" height="54"></object>-->
                </a>
            </div><!-- /.logo -->
            <!-- ============================================================= LOGO : END ============================================================= -->		</div><!-- /.logo-holder -->

        <div class="col-xs-12 col-sm-12 col-md-6 top-search-holder no-margin">
            <div class="contact-row">
                <div class="phone inline">
                    <i class="fa fa-phone"></i> +375 29 255-88-88
                </div>
                <div class="contact inline">
                    <i class="fa fa-envelope"></i> info@<span class="le-color">plywood-house.com</span>
                </div>
            </div><!-- /.contact-row -->
            <!-- ============================================================= SEARCH AREA ============================================================= -->
            <div class="search-area" style="visibility: hidden">
                <form>
                    <div class="control-group">
                        <input class="search-field" placeholder="Search for item">

                        <ul class="categories-filter animate-dropdown">
                            <li class="dropdown">

                                <a class="dropdown-toggle" data-toggle="dropdown" href="category-grid.html">all categories</a>

                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="category-grid.html">laptops</a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="category-grid.html">tv &amp; audio</a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="category-grid.html">gadgets</a></li>
                                    <li role="presentation"><a role="menuitem" tabindex="-1" href="category-grid.html">cameras</a></li>

                                </ul>
                            </li>
                        </ul>

                        <a class="search-button" href="#"></a>

                    </div>
                </form>
            </div><!-- /.search-area -->
            <!-- ============================================================= SEARCH AREA : END ============================================================= -->		</div><!-- /.top-search-holder -->

        <div class="col-xs-12 col-sm-12 col-md-3 top-cart-row no-margin">
            <div class="top-cart-row-container" style="margin-top: -50px">
                <div class="wishlist-compare-holder">
                    <div class="wishlist ">
                        <a id = "componentProduct" href="#"><i class="fa fa-forward"></i> next product <span class="value"></span> </a>
                    </div>
                    <div class="compare">
                        <a href="#" class = "buttonCompare" id = "compare_<tiles:insertAttribute name="type" />"><i class="fa fa-exchange"></i> compare <span id="compareCount" span class="value"></span> </a>
                        <input type="hidden" id="typeProduct" value="<tiles:insertAttribute name="type" />"/>
                    </div>
                </div>

                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                <!-- ============================================================= SHOPPING CART DROPDOWN ============================================================= -->
                <div class="top-cart-holder dropdown animate-dropdown">

                    <div class="basket">

                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <div class="basket-item-count">
                                <span id = "countShopCart" class="count"></span>
                                <img src="/images/icon-cart.png" alt="">
                            </div>

                            <div class="total-price-basket">
                                <span class="lbl">your cart:</span>
                    <span class="total-price">
                        <span class="sign">$</span><span class="value" id = "totalPriceCart"></span>
                    </span>
                            </div>
                        </a>

                        <ul class="dropdown-menu" id = "shopCartList">
                        </ul>
                    </div><!-- /.basket -->
                </div><!-- /.top-cart-holder -->
            </div><!-- /.top-cart-row-container -->
            <!-- ============================================================= SHOPPING CART DROPDOWN : END ============================================================= -->		</div><!-- /.top-cart-row -->
                    </sec:authorize>
    </div><!-- /.container -->
</header>


<script>
    $(document).ready(function(){

        $("#showHideHeader").click(function () {
            if ($(".header").is(":hidden")) {

                $(".header").show("slow");
                document.getElementById("showHideHeaderIcon").textContent="expand_less";

            } else {

                $(".header").hide("slow");
                document.getElementById("showHideHeaderIcon").textContent="expand_more";

            }
            return false;
        });

        $("#componentProduct").click(function () {
            var url = "/home/<tiles:insertAttribute name="next_index" />";
            $( location ).attr("href", url);
        });

        jQuery.ajax({
            type: "GET",
            url: "/compare/count/<tiles:insertAttribute name="type" />",
            dataType:'json',
            async: true
        }).done(function(count) {
            var compareCount = document.getElementById("compareCount");
            compareCount.textContent = '(' + count + ')';
        });

        var shopCartList = document.getElementById("shopCartList");
        var urlObjects = "/shoping/products";
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType:'json',
            async: false
        }).done(function( products ) {
            shopCartList.textContent = "";

            var countShopCart = document.getElementById("countShopCart");
            countShopCart.textContent = products.length;
            var sum = 0;
            for(var i=0; i<products.length; i++){
                var product = products[i];
                sum+=product.count*product.price;



                shopCartList.innerHTML += '<li>' +
                        '<div class="basket-item">' +
                        '<div class="row">' +
                        '<div class="col-xs-4 col-sm-4 no-margin text-center">' +
                        '<div class="thumb">' +
                        '<img id = "shopCarImg' + product.id + '" alt="" src="">' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-xs-8 col-sm-8 no-margin">' +
                        '<div class="title">' + product.name + '</div>' +
                        '<div class="price">' + '$' + product.price + '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</li>';

                jQuery.ajax({
                    type: "GET",
                    url: "/pagination/imgPatch/" + product.id,
                    async: false
                }).done(function (m_url) {
                    var element = document.getElementById("shopCarImg" + product.id);
                    element.src = "/images/product/" + m_url.img;
                });
            }

            shopCartList.innerHTML += '<li class="checkout">' +
                    ' <div class="basket-item">' +
                    '<div class="row">' +
                    '<div class="col-xs-12 col-sm-6">' +
                    '<a href="/shoping/view" class="le-button inverse">View cart</a>' +
                    '</div>' +
                    '<div class="col-xs-12 col-sm-6" id="contentButton">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</li>';


            jQuery.ajax({
                type: "POST",
                url: "/offer/isOffer",
                dataType:'json',
                async: false
            }).done(function( nocheck ) {
                var contentButton = document.getElementById("contentButton");
                if(nocheck && products!=undefined && products.length>0){
                    contentButton.innerHTML += '<button type="button" id="offer"  class="le-button big">Checkout</a>';
                }else if(!nocheck){
                    contentButton.innerHTML += '<button type="button" id="offerPrint" class="btn btn-info">Offer</button>';
                }
            });


            var totalPriceCart = document.getElementById("totalPriceCart");
            totalPriceCart.innerHTML =  sum;
        });


    });



</script>