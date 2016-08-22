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
<table style="width: 100%;height: 100%; text-align:center;">
    <tr>
        <td align="center">
            <form class="form-4" name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
                <h1>Форма входа</h1>
                <p>
                    <label for="login">Логин</label>
                    <input type="text"  name='j_username' placeholder="Логин или email" required value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'>
                </p>
                <p>
                    <label for="password">Пароль</label>
                    <input type="password"  name='j_password' placeholder="Пароль" required>
                </p>
                <p>
                    <input id="ok" type="submit" name="submit" value="Продолжить">
                </p>
            </form>
        </td>
    </tr>
</table>
    </div>