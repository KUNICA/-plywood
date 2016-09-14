<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.06.2016
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link href="/css/authorize.css" rel="stylesheet" type="text/css"  media="screen" />

<script>
    $(document).ready(function(){

        $(document).on('click', '#ok', function(e) {

            jQuery.ajax({
                type: "GET",
                url: "/authorize",
                async: true
            });

        });


    });
</script>

<div>


    <main id="authentication" class="inner-bottom-md" style="max-width: 400px;">
        <div class="container">
            <div class="row">

                <div class="col-md-6">
                    <section class="section sign-in inner-right-xs" style="max-width: 400px;">
                        <h2 class="bordered">Sign In</h2>
                        <p>Hello, Welcome to your account</p>
                        <form role="form" class="login-form cf-style-1" name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
                            <div class="field-row">
                                <label for="login">Login</label>
                                <input type="text"  name='j_username' placeholder="Login or email" required value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'>
                            </div><!-- /.field-row -->

                            <div class="field-row">
                                <label for="password">Password</label>
                                <input type="password"  name='j_password' placeholder="Password" required>
                            </div><!-- /.field-row -->

                            <div class="field-row clearfix">
                        	<span class="pull-left">
                        		<label class="content-color"><input type="checkbox" class="le-checbox auto-width inline"> <span class="bold">Remember me</span></label>
                        	</span>
                        	<span class="pull-right">
                        		<a href="#" class="content-color bold">Forgotten Password ?</a>
                        	</span>
                            </div>

                            <div class="buttons-holder">
                                <button type="submit" id="ok" class="le-button huge">Secure Sign In</button>
                            </div><!-- /.buttons-holder -->
                        </form><!-- /.cf-style-1 -->

                    </section><!-- /.sign-in -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container -->
    </main>
    </div>