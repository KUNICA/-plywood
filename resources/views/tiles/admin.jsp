<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="MediaCenter, Template, eCommerce">
<meta name="robots" content="all">

<!-- Bootstrap Core CSS -->

<!-- Customizable CSS -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/main.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/green.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/owl.carousel.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/owl.transitions.css">
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/animate.min.css">

<!-- Demo Purpose Only. Should be removed in production -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/config.css">

<link href="<%=JspConstants.APP_PATH%>/css/green.css" rel="alternate stylesheet" title="Green color">
<link href="<%=JspConstants.APP_PATH%>/css/blue.css" rel="alternate stylesheet" title="Blue color">
<link href="<%=JspConstants.APP_PATH%>/css/red.css" rel="alternate stylesheet" title="Red color">
<link href="<%=JspConstants.APP_PATH%>/css/orange.css" rel="alternate stylesheet" title="Orange color">
<link href="<%=JspConstants.APP_PATH%>/css/navy.css" rel="alternate stylesheet" title="Navy color">
<link href="<%=JspConstants.APP_PATH%>/css/dark-green.css" rel="alternate stylesheet" title="Darkgreen color">
<link href="<%=JspConstants.APP_PATH%>/less/sidebar.less" rel="alternate stylesheet">
<!-- Demo Purpose Only. Should be removed in production : END -->

<!-- Fonts -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>

<!-- Icons/Glyphs -->
<link rel="stylesheet" href="<%=JspConstants.APP_PATH%>/css/font-awesome.min.css">

<!-- HTML5 elements and media queries Support for IE8 : HTML5 shim and Respond.js -->
<!--[if lt IE 9]>
<script src="/js/html5shiv.js"></script>
<script src="/js/respond.min.js"></script>
<![endif]-->

<link href="<%=JspConstants.APP_PATH%>/css/rules.css" rel="stylesheet" type="text/css" >
<link href="<%=JspConstants.APP_PATH%>/css/shoping.css" rel="stylesheet" type="text/css" >
<link href="<%=JspConstants.APP_PATH%>/css/compare.css" rel="stylesheet" type="text/css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
                            <td style="text-align:center;"><img width = "100" style="max-width: 100px;" height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src="/pagination/imgPatch/${list_object.id}"></td>
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
                            <td style="text-align:center;"><img width = "100" style="max-width: 100px;"  height="80" id="responsiveImageImage_${list_object.id}" class="responsiveImageImage" src="/pagination/imgPatch/${list_object.id}"></td>
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

