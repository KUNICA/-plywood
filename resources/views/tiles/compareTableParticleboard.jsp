<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.08.2016
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery.json.js"></script>
    <link href="/css/compare.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript">

        function remove(index){
            document.getElementById("remove" + index).style.display = 'none';
            document.getElementById("name" + index).style.display = 'none';
            document.getElementById("image" + index).style.display = 'none';
            document.getElementById("price" + index).style.display = 'none';
            document.getElementById("description" + index).style.display = 'none';
            document.getElementById("length" + index).style.display = 'none';
            document.getElementById("width" + index).style.display = 'none';
            document.getElementById("depth" + index).style.display = 'none';
            document.getElementById("laminated" + index).style.display = 'none';
            document.getElementById("coating" + index).style.display = 'none';
            document.getElementById("grade" + index).style.display = 'none';

        }

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
</head>
<body>
    <table class="table table-bordered tableCompare" >
            <tr>
                <td>remove</td>
                <% int counterRemove = 0; %>
            <c:forEach items="${tableCompare}" var="list_remove" >
                <td id="remove<%=counterRemove%>" style="text-align:center;"><button class="btn" onclick="remove(<%=counterRemove%>)">remove</button></td>
                <%  counterRemove++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>name</td>
                <% int counterName = 0; %>
                <c:forEach items="${tableCompare}" var="list_name" varStatus="counterName">
                    <td id="name<%=counterName%>" style="text-align:center;">${list_name.name}</td>
                    <%  counterName++; %>
                </c:forEach>
            </tr>
            <tr>
                <td>photo</td>
                <% int counterImage = 0; %>
            <c:forEach items="${tableCompare}" var="list_image" varStatus="counterImage">
                <td id="image<%=counterImage%>" style="text-align:center;"><img width = "100" height="80" id="responsiveImageImage_${list_image.id}" class="responsiveImageImage" src=""></td>
                <script>
                    var el = document.getElementById("responsiveImageImage_${list_image.id}");
                    setImageName(${list_image.id},el);
                </script>
                <%  counterImage++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>price</td>
                <% int counterPrice = 0; %>
            <c:forEach items="${tableCompare}" var="list_price" varStatus="counterPrice">
                    <td id="price<%=counterPrice%>" style="text-align:center;">${list_price.price}</td>
                <%  counterPrice++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>description</td>
                <% int counterDescription = 0; %>
                <c:forEach items="${tableCompare}" var="list_description" varStatus="counterDescription">
                    <td id="description<%=counterDescription%>" style="text-align:center;">${list_description.shortDescription}</td>
                    <%  counterDescription++; %>
                </c:forEach>
            </tr>
            <tr>
                <td>length</td>
                <% int counterLength = 0; %>
             <c:forEach items="${tableCompare}" var="list_length" varStatus="counterLength">
                <td id="length<%=counterLength%>" style="text-align:center;">${list_length.length}</td>
                 <%  counterLength++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>width</td>
                <% int counterWidth = 0; %>
            <c:forEach items="${tableCompare}" var="list_width" varStatus="counterWidth">
                    <td id="width<%=counterWidth%>" style="text-align:center;">${list_width.width}</td>
                <%  counterWidth++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>depth</td>
                <% int counterDepth = 0; %>
            <c:forEach items="${tableCompare}" var="list_depth" varStatus="counterDepth">
                    <td id="depth<%=counterDepth%>" style="text-align:center;">${list_depth.depth}</td>
                <%  counterDepth++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>laminated</td>
                <% int counterLaminated = 0; %>
            <c:forEach items="${tableCompare}" var="list_laminated" varStatus="counterLaminated">
                    <td id="laminated<%=counterLaminated%>" style="text-align:center;">${list_laminated.laminated}</td>
                <%  counterLaminated++; %>
            </c:forEach>
            </tr>
            <tr>
                <td>coating</td>
                <% int counterCoating = 0; %>
            <c:forEach items="${tableCompare}" var="list_coating" varStatus="counterCoating">
                    <td id="coating<%=counterCoating%>" style="text-align:center;">${list_coating.coating}</td>
                <%  counterCoating++; %>
            </c:forEach>
            </tr>
        <tr>
            <td>grade</td>
            <% int counterGrade = 0; %>
            <c:forEach items="${tableCompare}" var="list_grade" varStatus="counterGrade">
                <td id="grade<%=counterGrade%>" style="text-align:center;">${list_grade.grade.nameField}</td>
                <%  counterGrade++; %>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
