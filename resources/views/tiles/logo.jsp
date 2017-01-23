<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.08.2016
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
<head>
    <title>Bootstrap Case</title>
</head>
<body>


<nav class="top-bar animate-dropdown">
    <div class="container">
        <div class="col-xs-12 col-sm-6 no-margin">
            <ul>
                <li><a href="/home">Home</a></li>
                <li><a href="#">Ef Tech</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#change-colors">Change Colors</a>
                    <ul class="dropdown-menu" role="menu">
                        <li role="presentation"><a role="menuitem" class="changecolor green-text" tabindex="-1" href="#" title="Green color">Green</a></li>
                        <li role="presentation"><a role="menuitem" class="changecolor blue-text" tabindex="-1" href="#" title="Blue color">Blue</a></li>
                        <li role="presentation"><a role="menuitem" class="changecolor red-text" tabindex="-1" href="#" title="Red color">Red</a></li>
                        <li role="presentation"><a role="menuitem" class="changecolor orange-text" tabindex="-1" href="#" title="Orange color">Orange</a></li>
                        <li role="presentation"><a role="menuitem" class="changecolor navy-text" tabindex="-1" href="#" title="Navy color">Navy</a></li>
                        <li role="presentation"><a role="menuitem" class="changecolor dark-green-text" tabindex="-1" href="#" title="Darkgreen color">Dark Green</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Product
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/home/0">Particleboard</a></li>
                        <li><a href="/home/1">Plywood</a></li>
                        <li><a href="/home/2">Laminated particleboard </a></li>
                    </ul>
                </li>
                <li class="dropdown">
                <li><a href="/video/section">Video</a></li>
                </li>
                <li><a href="/contacts/page">Contacts</a></li>

            </ul>
        </div><!-- /.col -->

        <div class="col-xs-12 col-sm-6 no-margin">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <li>
                        <a id="adminRun" href="/admin/run">
                            <span class="glyphicon glyphicon-user"></span>
                            admin
                        </a>
                    </li>
                </sec:authorize>
                <!--
                <sec:authorize access="isAnonymous()">
                    <li><a href="/registration/form"><span class="glyphicon glyphicon-user"></span>Register</a></li>
                </sec:authorize>
                -->
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.username" />
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        Login
                    </sec:authorize>
                    <b class="caret"></b>
                </a>
                    <ul class="dropdown-menu">
                        <sec:authorize access="isAuthenticated()">
                            <tiles:insertAttribute name="info" />
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <tiles:insertAttribute name="body" />
                        </sec:authorize>
                    </ul>
                </li>
            </ul>
        </div><!-- /.col -->
    </div><!-- /.container -->
</nav>
</body>
</html>
