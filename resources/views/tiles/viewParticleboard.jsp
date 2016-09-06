<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.09.2016
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/css/classic.css" rel="stylesheet" type="text/css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="/js/galary.js"></script>
<link href="/css/bootstrap-slider.css" rel="stylesheet">
<script type="text/javascript" src="/js/bootstrap-slider.js"></script>

<script>
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

<html>
<head>
    <title>Title</title>
</head>

<table style="margin-left: 50px; margin-right: 50px">
    <tr>
        <td>
            <div class="container" style="float:left; width:300px">
                <h1>${listPlywood.type}</h1>
                <h2>Price: ${listPlywood.price}</h2>
                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Parametrs</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>name</td>
                        <td style="text-align:center;">${listPlywood.name}</td>
                    </tr>
                    <tr>
                        <td>length</td>
                        <td  style="text-align:center;">${listPlywood.length}</td>
                    </tr>
                    <tr>
                        <td>width</td>
                        <td  style="text-align:center;">${listPlywood.width}</td>
                    </tr>
                    <tr>
                        <td>depth</td>
                        <td id="depth" style="text-align:center;">${listPlywood.depth}</td>
                    </tr>
                    <tr>
                        <td>laminated</td>
                        <td  style="text-align:center;">${listPlywood.laminated}</td>
                    </tr>
                    <tr>
                        <td>coating</td>
                        <td  style="text-align:center;">
                            <c:choose>
                                <c:when test="${listPlywood.coating}">
                                    yes
                                </c:when>
                                <c:when test="${!listPlywood.coating}">
                                    no
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>grade</td>
                        <c:if test="${listPlywood.grade!=null}">
                        <td style="text-align:center;">${listPlywood.grade.nameField}</td>
                        </c:if>
                    </tr>

                    </tbody>
                </table>
            </div>
        </td>
        <td>
            <div style="margin-left: 100px;"  class = "homeCardFeature homeCardGalary Galary">
                <img style="height: 300px; cursor: pointer" class="item_img" id ="galary_${listPlywood.id}"   src=""  alt="${listPlywood.name}">
                <script>
                    var el = document.getElementById("galary_${listPlywood.id}");
                    setImageName(${listPlywood.id},el);
                </script>
            </div>
        </td>
    </tr>
    <tr>
        <td  style="text-align:left;">${listPlywood.shortDescription}</td>
    </tr>
</table>
<div id="galary" class="modal fade">
    <div class="modal-dialog" style="width: 100%; height: 100%;">
        <div class="modal-content">
            <div class="modal-header"><button class="close btn btn-default" type="button" data-dismiss="modal" >Close</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <tiles:insertAttribute name="galary" />
            </div>
        </div>
    </div>
</div>
<body>

</body>
</html>
