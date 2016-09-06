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
        type: "GET",
        url: "/pagination/imgPatch/" + id,
        async: false
    }).done(function (m_url) {
        element.src = "/images/product/" + m_url.img;
    });
}

</script>


<div class="tabbable" style="width:100%;"> <!-- Only required for left/right tabs -->
    <ul class="nav nav-tabs">
        <li class="active"><a href="#tab1" data-toggle="tab">Particleboard</a></li>
        <li><a href="#tab2" data-toggle="tab">Plywood</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab1">
            <div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th >Product ID</th>
                        <th >Photo</th>
                        <th>
                            Coating
                        </th>
                        <th>
                            Grade
                        </th>
                        <th>
                            Laminated
                        </th>
                        <th>
                            Thickness
                        </th>
                        <th>
                            Length
                        </th>
                        <th>
                            Weight
                        </th>
                        <th>
                            Price
                        </th>
                        <th>
                            Description
                        </th>
                        <th>
                            remove
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <% int counter = 0; %>
                    <c:forEach items="${tableParticleboard}" var="list_object" varStatus="counter">
                        <%--@elvariable id="tableRowAction" type="java.lang.String"--%>
                        <%--@elvariable id="tableButtonAction" type="java.lang.String"--%>
                        <%--@elvariable id="tableButtonType" type="java.lang.String"--%>
                        <tr id="tr_${list_object.id}">
                            <td style="text-align:center;">${list_object.productId}</td>
                            <td style="text-align:center;"><img width = "100" height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src="/pagination/imgPatch/${list_object.id}"></td>
                            <td style="text-align:center;">${list_object.coating}</td>
                            <c:if test="${list_object.grade!=null}">
                                <td style="text-align:center;">${list_object.grade.nameField}</td>
                            </c:if>
                            <c:if test="${list_object.grade==null}">
                                <td style="text-align:center;"></td>
                            </c:if>
                            <td style="text-align:center;">${list_object.laminated}</td>
                            <td style="text-align:center;">${list_object.depth}</td>
                            <td style="text-align:center;">${list_object.length}</td>
                            <td style="text-align:center;">${list_object.width}</td>
                            <td style="text-align:center;">${list_object.price}</td>
                            <td style="text-align:center;">${list_object.shortDescription}</td>
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
                <form action="${fileUploadControllerURL}/particleboard" method="post"
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
        </div>
        <div class="tab-pane" id="tab2">


            <div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th >Product ID</th>
                        <th >Photo</th>
                        <th>
                            coating
                        </th>
                        <th>
                            Color coating
                        </th>
                        <th>
                            Water resistance (FK or -)
                        </th>
                        <th>
                            Sanded or unsanded
                        </th>
                        <th>
                            Thickness
                        </th>
                        <th>
                            Length
                        </th>
                        <th>
                            Weight
                        </th>
                        <th>
                            Price
                        </th>
                        <th>
                            Description
                        </th>
                        <th>
                            remove
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tablePlywood}" var="list_object" varStatus="counter">
                        <%--@elvariable id="tableRowAction" type="java.lang.String"--%>
                        <%--@elvariable id="tableButtonAction" type="java.lang.String"--%>
                        <%--@elvariable id="tableButtonType" type="java.lang.String"--%>
                        <tr id="tr_${list_object.id}">
                            <td style="text-align:center;">${list_object.productId}</td>
                            <td style="text-align:center;"><img width = "100" height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src="/pagination/imgPatch/${list_object.id}"></td>
                            <td style="text-align:center;">${list_object.coating}</td>
                            <td style="text-align:center;">${list_object.colorCoating}</td>
                            <c:choose>
                            <c:when test="${list_object.waterResistance}">
                                <td style="text-align:center;">FK</td>
                            </c:when>
                            <c:when test="${!list_object.waterResistance}">
                                    <td style="text-align:center;">-</td>
                            </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${list_object.sanded}">
                                    <td style="text-align:center;">sanded</td>
                                </c:when>
                                <c:when test="${!list_object.sanded}">
                                    <td style="text-align:center;">unsanded</td>
                                </c:when>
                            </c:choose>
                            <td style="text-align:center;">${list_object.depth}</td>
                            <td style="text-align:center;">${list_object.length}</td>
                            <td style="text-align:center;">${list_object.width}</td>
                            <td style="text-align:center;">${list_object.price}</td>
                            <td style="text-align:center;">${list_object.shortDescription}</td>
                            <td style="text-align:center;"><button id = "remove_${list_object.id}" class="btn RemoveButton">remove</button></td>
                        </tr>
                        <script>
                            var el = document.getElementById("responsiveImageImage_${list_object.id}");
                            setImageName(${list_object.id},el);
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <form action="${fileUploadControllerURL}/plywood" method="post"
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


        </div>
    </div>
</div>

