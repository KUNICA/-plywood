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

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Ef Tech</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/home">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
                    <tiles:insertAttribute name="pay" />
                    <li>
                        <a id="adminRun" href="/admin/run">
                        <span class="glyphicon glyphicon-user"></span>
                        admin
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                </sec:authorize>
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

        </div>
    </div>


</nav>
</body>
</html>
