<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.09.2016
  Time: 10:28
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
<%@ page import="com.entity.Grade" %>

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

<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/page-proofs-ruller.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/home.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>


<div id="top-banner-and-menu">
    <div class="container">

        <div class="col-xs-12 col-sm-4 col-md-3 sidemenu-holder">
            <!-- ================================== TOP NAVIGATION ================================== -->
            <div class="side-menu animate-dropdown">
                <div class="head"><i class="fa fa-list"></i> all departments</div>
                <nav class="yamm megamenu-horizontal" role="navigation">
                    <ul class="nav">

                        <!--
                        <li class="dropdown menu-item">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Pages</a>
                            <ul class="dropdown-menu mega-menu">
                                <li class="yamm-content">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <ul class="list-unstyled">
                                                <li><a href="/home">Home</a></li>
                                                <li><a href="/video/section">Video</a></li>
                                                <li><a href="/contacts/page">Contacts</a></li>
                                                <li><a href="/home/1">Plywood - Grid/List</a></li>
                                                <li><a href="/home/0">Particleboard - Grid/List</a></li>
                                                <li><a href="/home/2">Particleboard laminated - Grid/List</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-unstyled">
                                                <li><a href="/registration/form">Register</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>

                            </ul>
                        </li><!-- /.menu-item -->

                        <li class="dropdown menu-item">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Products</a>
                            <ul class="dropdown-menu mega-menu">
                                <li class="yamm-content">
                                    <!-- ================================== MEGAMENU VERTICAL ================================== -->
                                    <div class="row">
                                        <div class="col-xs-12 col-lg-4">
                                            <ul>
                                                <li><a href="/home/1">Plywood</a></li>
                                                <li><a href="/home/0">Particleboard</a></li>
                                                <li><a href="/home/2">Particleboard laminated</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- ================================== MEGAMENU VERTICAL ================================== -->
                                </li>
                            </ul>
                        </li><!-- /.menu-item -->
                        <!--
                        <li><a href="/video/section">Video</a></li>
                        <li><a href="/contacts/page">Contacts</a></li>
                        -->
                    </ul><!-- /.nav -->
                </nav><!-- /.megamenu-horizontal -->
            </div><!-- /.side-menu -->
            <!-- ================================== TOP NAVIGATION : END ================================== -->		</div><!-- /.sidemenu-holder -->

        <div class="col-xs-12 col-sm-8 col-md-9 homebanner-holder">
            <!-- ========================================== SECTION – HERO ========================================= -->

            <div id="hero">
                <div id="owl-main" class="owl-carousel owl-inner-nav owl-ui-sm owl-theme" style="opacity: 1; display: block;">

                    <div class="owl-wrapper-outer">
                        <div id = "lastTreeProducts" class="owl-wrapper owl-origin" style="width: 1840px; left: 0px; display: block; transition: all 0ms ease; transform: translate3d(0px, 0px, 0px); transform-origin: 230px center 0px; perspective-origin: 230px center;">
                        </div>
                    </div>

                    <!-- /.item -->

                    <div class="owl-controls clickable">
                        <div class="owl-pagination">
                            <div class="owl-page active">
                                <span class=""></span>
                            </div>
                            <div class="owl-page">
                                <span class="">

                                </span>
                            </div>
                        </div>
                        <div class="owl-buttons">
                            <div class="owl-prev">
                                <i class="fa fa-chevron-left"></i>
                            </div>
                            <div class="owl-next">
                                <i class="fa fa-chevron-right"></i>
                            </div>
                        </div></div>
                </div><!-- /.owl-carousel -->
            </div>

            <!-- ========================================= SECTION – HERO : END ========================================= -->
        </div><!-- /.homebanner-holder -->

    </div><!-- /.container -->
</div>

<div id="products-tab" class="wow fadeInUp animated" style="visibility: visible; animation-name: fadeInUp;">
    <div class="container">
        <div class="tab-holder">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#featured" data-toggle="tab">featured</a></li>
                <li><a href="#new-arrivals" data-toggle="tab">new arrivals</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="featured">
                    <div class="product-grid-holder" id="newViews">
                    </div>
                </div>
                <div class="tab-pane" id="new-arrivals">
                    <div class="product-grid-holder" id = "newArrivals">
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>







