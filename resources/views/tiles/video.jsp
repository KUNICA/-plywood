<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<!-- JavaScripts placed at the end of the document so the pages load faster -->
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruler.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/video.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var index = <tiles:insertAttribute name='index' />;
        var scriptRuller = new ScriptVideo();
        myMapParamSearch[<tiles:insertAttribute name='index' />] = new ParamSearch(<tiles:insertAttribute name='index' />,
                "<tiles:insertAttribute name='mainData' />",scriptRuller,scriptRuller);
        scriptRuller.initPagination(<tiles:insertAttribute name='index' />);

        $('[data-toggle="tooltip"]').tooltip();

});
</script>

<div class="animate-dropdown"><!-- ========================================= BREADCRUMB ========================================= -->


    <!-- ============================================================= HEADER : END ============================================================= --><div class="animate-dropdown"><!-- ========================================= BREADCRUMB ========================================= -->
        <div id="top-mega-nav">
            <div class="container">
                <nav>
                    <ul class="inline">

                        <li class="breadcrumb-nav-holder">
                            <ul>
                                <li class="breadcrumb-item">
                                    <a href="/home">Home</a>
                                </li>
                                <li class="breadcrumb-item current gray">
                                    <a href="/video/section">Video - all</a>
                                </li>
                            </ul>
                        </li><!-- /.breadcrumb-nav-holder -->
                    </ul>
                </nav>
            </div><!-- /.container -->
        </div><!-- /#top-mega-nav -->
        <!-- ========================================= BREADCRUMB : END ========================================= --></div>     <!-- ========================================= MAIN ========================================= -->
    <main id="blog" class="inner-bottom-xs">
        <div class="container">

            <div class="row">
                <div class="col-md-9">

                    <div class="posts sidemeta" id = "video_main">


                    </div><!-- /.posts -->

                    <hr>

                    <div class="paginator" id="paginator1_<tiles:insertAttribute name='index' />" style=" display: none"></div>
                    <div class="paginator"  id="paginator2_<tiles:insertAttribute name='index'  />"></div>
                    <!-- /.pagination -->
                </div><!-- /.col -->

                <div class="col-md-3">

                    <aside class="sidebar blog-sidebar">

                        <div class="widget clearfix">
                            <div class="body">
                                <div  class="search-form">
                                    <div class="form-group">
                                        <label class="sr-only" for="page-search">Type your search here</label>
                                        <input id="page-search" class="search-input form-control" type="search" name="word" placeholder="Search in video...">
                                    </div>
                                    <button id = "searchVidio" class="page-search-button">
                <span class="fa fa-search">
                    <span class="sr-only">Search</span>
                </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="widget">
                            <h4>Categories</h4>
                            <div class="body">
                                <input type="hidden" id = 'categoryId' value="All">
                                <ul class="le-links" id = "categories">
                                    <!--<li><a href="video-news">News</a></li>-->
                                    <li><a href="/video/all">All</a></li>
                                </ul><!-- /.le-links -->
                            </div>
                        </div><!-- /.widget -->
                    </aside><!-- /.sidebar .blog-sidebar -->
                </div>
            </div><!-- /.row -->

        </div><!-- /.container -->
    </main><!-- /.inner-bottom-xs -->
    <!-- ========================================= MAIN : MAIN ========================================= -->        <!-- ============================================================= FOOTER ============================================================= -->
</div>

    <!-- ============================================================= FOOTER : END ============================================================= -->   </div>
