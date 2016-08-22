<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.08.2016
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/admin.js"></script>

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
                Short description
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
        <c:forEach items="${tableInformation}" var="list_object" varStatus="counter">
            <%--@elvariable id="tableRowAction" type="java.lang.String"--%>
            <%--@elvariable id="tableButtonAction" type="java.lang.String"--%>
            <%--@elvariable id="tableButtonType" type="java.lang.String"--%>
            <tr id="tr_${list_object.id}">
                <td style="text-align:center;">${list_object.id}</td>
                <td style="text-align:center;"><img width = "100" height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src="/pagination/imgPatch/${list_object.id}"></td>
                <td style="text-align:center;">${list_object.nameImage}</td>
                <td style="text-align:center;">${list_object.shortDescription}</td>
                <td style="text-align:center;">${list_object.adress.remark}</td>
                <td style="text-align:center;">${list_object.slleeps}</td>
                <td style="text-align:center;">${list_object.countRoums}</td>
                <td style="text-align:center;">${list_object.bathrooms}</td>
                <td style="text-align:center;">${list_object.price}</td>
                <td style="text-align:center;"><button id = "remove_${list_object.id}" class="btn RemoveButton">remove</button></td>
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
<div>
    <form action="${fileUploadControllerURL}" method="post"
          enctype="multipart/form-data">
        <table>
            <tr>
                <td><b>File:</b></td>
                <td><input type="file" name="file"></td>
                <td><input type="submit" value="add products"></td>
                <td style = "color:red">${error}</td>
            </tr>
        </table>
    </form>
</div>

<div>
    <form action="${fileSaveControllerURL}" method="post"
          enctype="multipart/form-data">
        <table>
            <tr>
                <td><b>File:</b></td>
                <td><input type="file" name="fileImage"></td>
                <td><input type="submit" value="add image"></td>
                <td style = "color:red">${errorImage}</td>
            </tr>
        </table>
    </form>
</div>

