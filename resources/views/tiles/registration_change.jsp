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
<div class="col-md-6">
    <section class="section register inner-left-xs">
        <h2 class="bordered">Create New Password</h2>
        <p>Change your password in the Media Center</p>

        <form:form method="POST" role="form" action="/registration/password" commandName="registration" class="register-form cf-style-1">
            <div class="field-row">
                <label for="passwordInput" id ="labelPasswordInput">Password*</label>
                <form:input path="password" name='password' id = "passwordInput" class="le-input"/>
                <form:errors path="password" cssclass="error"/>
            </div><!-- /.field-row -->

            <div class="buttons-holder">
                <button type="submit" class="le-button huge">Sign Up</button>
            </div><!-- /.buttons-holder -->
        </form:form>

    </section><!-- /.register -->

</div>


</body>
</html>
