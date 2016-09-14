<%@ page import="com.ui.JspConstants" %>
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
    <script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/jquery.json.js"></script>
    <script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/admin.js"></script>



    <title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<script>
    $(document).ready(function(){
        $(window).scroll(function () {
            if ($(this).scrollTop() > 50) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        // scroll body to 0px on click
        $('#back-to-top').click(function () {
            $('#back-to-top').tooltip('hide');
            $('body,html').animate({
                scrollTop: 0
            }, 800);
            return false;
        });

        $('#back-to-top').tooltip('show');

    });
</script>

<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Up" data-toggle="tooltip" data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>

<body>
<div>
    <tiles:insertAttribute name="header" />
</div>
<div>
    <tiles:insertAttribute name="logout" />
</div>
<div>
    <tiles:insertAttribute name="panel" />
</div>
<div class="wrapper">
<tiles:insertAttribute name="body" />
</div>
<div>
    <tiles:insertAttribute name="footer" />
</div>
</body>

<script src="<%=JspConstants.APP_PATH%>/js/jquery-migrate-1.2.1.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false&amp;language=en"></script>
<script src="<%=JspConstants.APP_PATH%>/js/gmap3.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/bootstrap-hover-dropdown.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/owl.carousel.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/css_browser_selector.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/echo.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/jquery.easing-1.3.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/bootstrap-slider.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/jquery.raty.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/jquery.prettyPhoto.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/jquery.customSelect.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/wow.min.js"></script>
<script src="<%=JspConstants.APP_PATH%>/js/scripts.js"></script>

<!-- For demo purposes – can be removed on production -->

<script src="<%=JspConstants.APP_PATH%>/js/switchstylesheet.js"></script>

<script>
    $(document).ready(function(){
        $(".changecolor").switchstylesheet( { seperator:"color"} );
        $('.show-theme-options').click(function(){
            $(this).parent().toggleClass('open');
            return false;
        });
    });

    $(window).bind("load", function() {
        $('.show-theme-options').delay(2000).trigger('click');
    });
</script>
<!-- For demo purposes – can be removed on production : End -->

<script src="http://w.sharethis.com/button/buttons.js"></script>

</html>