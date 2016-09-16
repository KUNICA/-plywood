<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
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
<script src="<%=JspConstants.APP_PATH%>/js/registration.js"></script>

</head>
<body>
<div class="animate-dropdown"><!-- ========================================= BREADCRUMB ========================================= -->
    <div id="top-mega-nav">
        <div class="container">
            <nav>
                <ul class="inline">
                    <li class="breadcrumb-nav-holder">
                        <ul>
                            <li class="breadcrumb-item current gray">
                                <a href="#">Checkout Process</a>
                            </li>
                        </ul>
                    </li><!-- /.breadcrumb-nav-holder -->
                </ul>
            </nav>
        </div><!-- /.container -->
    </div><!-- /#top-mega-nav -->
    <!-- ========================================= BREADCRUMB : END ========================================= --></div>
<section id="checkout-page">
    <div class="container">
        <div class="col-xs-12 no-margin">
            <form:form method="POST" action="/registration/set" commandName="registration">
            <div class="billing-address">
                <h2 class="border h1">billing address</h2>
                    <div class="row field-row">
                        <div class="col-xs-12 col-sm-6">
                            <label for="fullNameInput" id ="labelFullName">full name*</label>
                            <form:input path="fullName" name='fullName' id = "fullNameInput" class="le-input"/>
                            <form:errors path="fullName" cssclass="error"/>
                            <br/>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <label for="lastNameInput" id ="labelLastName">user name*</label>
                            <form:input  path="lastName" name='lastName' id = "lastNameInput" class="le-input"/>
                            <form:errors path="lastName" cssclass="error"/>
                        </div>
                    </div><!-- /.field-row -->

                    <div class="row field-row">
                        <div class="col-xs-12">
                            <label for="companyNameInput" id ="labelCompanyName">company name</label>
                            <form:input path="companyName" name='companyName' id = "companyNameInput" class="le-input"/>
                            <form:errors path="companyName" cssclass="error"/>
                        </div>
                    </div><!-- /.field-row -->

                    <div class="row field-row">
                        <div class="col-xs-12 col-sm-6">
                            <label for="addressInput" id ="labelAdress">address*</label>
                            <form:input path="address" name='address' id = "addressInput" class="le-input placeholder" data-placeholder="street address"/>
                            <form:errors path="address" cssclass="error"/>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <label for="townInput" id ="labelTown">&nbsp;</label>
                            <form:input  path="town" name='town' id = "townInput" class="le-input placeholder" data-placeholder="town"/>
                            <form:errors path="town" cssclass="error"/>
                        </div>
                    </div><!-- /.field-row -->

                    <div class="row field-row">
                        <div class="col-xs-12 col-sm-4">
                            <label for="zipInput" id ="labelZip">postcode / Zip*</label>
                            <form:input path="zip" name='zip' id = "zipInput" class="le-input"/>
                            <form:errors path="zip" cssclass="error"/>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <label for="emailInput" id ="labelEmail">email address*</label>
                            <form:input path="email" name='email' id = "emailInput" class="le-input"/>
                            <form:errors path="email" cssclass="error"/>
                        </div>

                        <div class="col-xs-12 col-sm-4">
                            <label for="phoneInput" id ="labelPhone">phone number*</label>
                            <form:input path="phone" name='phone' id = "phoneInput" class="le-input"/>
                            <form:errors path="phone" cssclass="error"/>
                        </div>
                    </div><!-- /.field-row -->

                    <div class="place-order-button">
                        <input type="submit" id="reg" class="le-button big" value="registration"/>
                    </div>
            </div><!-- /.billing-address -->
            </form:form>

        </div><!-- /.col -->
    </div><!-- /.container -->
</section>
</body>
</html>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();

    });

</script>
