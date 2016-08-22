<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.08.2016
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript">
    $(function() {
        $("#exit").click(function(){
            jQuery.ajax({
                type: "GET",
                url: "/killSession",
                dataType:'html',
                async: false
            }).done(function( data ) {
                var url = "/login";
                $( location ).attr("href", url);

            });
        });
    });
</script>

<div>
    <sec:authorize access="isAuthenticated()">
<table class = "table" style="width:300px">
    <tr>
        <th>Имя: ${personalData.firstName}</th>
    </tr>
    <tr>
        <th>Фамилия: ${personalData.lastName}</th>
    </tr>
    <tr>
        <th>Отчество: ${personalData.midleName}</th>
    </tr>
    <tr>
        <th>email: ${personalData.email}</th>
    </tr>
    <tr>
        <th><button type="button" id="exit" class="btn btn-success" style="margin-left:auto; margin-right:auto;">Выход</button></th>
    </tr>
</table>
    </sec:authorize>
</div>
