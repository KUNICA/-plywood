<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery.json.js"></script>
    <script type="text/javascript" src="/js/admin.js"></script>

    <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div>
    <tiles:insertAttribute name="header" />
</div>
<div>
    <tiles:insertAttribute name="logout" />
</div>
<div>
    <table>
        <tr>
            <td valign="top"><tiles:insertAttribute name="menu" /></td>
            <td valign="top"><tiles:insertAttribute name="body" /></td>
        </tr>
    </table>
</div>
<div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>