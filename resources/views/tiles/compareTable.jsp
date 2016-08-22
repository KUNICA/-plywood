<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.08.2016
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>

<script>

function remove(index){
    var tr = document.getElementById("tr_" + index);
    tr.style.display = 'none';
}

</script>

<script>
    function setImageName(id,element) {
        jQuery.ajax({
            type: "POST",
            url: "/pagination/imgPatch/" + id,
            async: false
        }).done(function (m_url) {
            element.src = "/images/product/" + m_url.img;
        });
    }

</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th >number</th>
            <th >Photo</th>
            <th>
                Name
            </th>
            <th>
                Adress
            </th>
            <th>
                Persons
            </th>
            <th >
                Bedrooms
            </th>
            <th>
                Bathrooms
            </th>
            <th>
                price
            </th>
            <th>
                remove
            </th>
        </tr>
        </thead>
        <tbody>
        <% int counter = 0; %>
        <c:forEach items="${tableCompare}" var="list_object" varStatus="counter">
            <%--@elvariable id="tableRowAction" type="java.lang.String"--%>
            <%--@elvariable id="tableButtonAction" type="java.lang.String"--%>
            <%--@elvariable id="tableButtonType" type="java.lang.String"--%>
            <tr id="tr_${counter.index}">
                <td style="text-align:center;"><%=counter + 1%></td>
                <td style="text-align:center;"><img width = "100" height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src=""></td>
                <td style="text-align:center;">${list_object.nameImage}</td>
                <td style="text-align:center;">${list_object.adress.remark}</td>
                <td style="text-align:center;">${list_object.slleeps}</td>
                <td style="text-align:center;">${list_object.countRoums}</td>
                <td style="text-align:center;">${list_object.bathrooms}</td>
                <td style="text-align:center;">${list_object.price}</td>
                <td style="text-align:center;"><button class="btn" onclick="remove(${counter.index})">remove</button></td>
            </tr>
            <% counter++;%>
            <script>
                var el = document.getElementById("responsiveImageImage_${list_object.id}");
                setImageName(${list_object.id},el);
            </script>
        </c:forEach>
        </tbody>
    </table>
</div>
