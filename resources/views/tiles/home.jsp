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

<!-- JavaScripts placed at the end of the document so the pages load faster -->
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruler.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/page-proofs-ruller.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruller-plywood.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruller-particleboard.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/to-plug-ruller.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/shopingcart.js"></script>
<script type="text/javascript" src="<%=JspConstants.APP_PATH%>/js/compare.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        var index = <tiles:insertAttribute name='index' />;
        var scriptRuller = new ScriptParticleboard();
       scriptRuller = toPlug(index);

        myMapParamSearch[<tiles:insertAttribute name='index' />] = new ParamSearch(<tiles:insertAttribute name='index' />,
                "<tiles:insertAttribute name='mainData' />",scriptRuller);
        initPaginationParametrs(<tiles:insertAttribute name='index' />,scriptRuller.paramsUrl);
        initPagination(<tiles:insertAttribute name='index' />);

    });


</script>

        <div style="left: 100px; width: 100%;">

             <div style="float: left;">
                      <div style="float: left ">
                              <tiles:insertAttribute name="menu" />
                      </div>

                          <div class="col-xs-12 col-sm-9 no-margin wide sidebar" style=" clear:right; margin-left: 20px; width: 800px">
                              <section id="gaming">
                                  <div class="grid-list-products">
                                      <h2 class="section-title">Gaming</h2>

                                      <div class="control-bar">
                                              <b style="font-size: 24px; margin-left: 100px;">
                                                  <tiles:insertAttribute name="type" />
                                              </b>

                                         <div id="item-count" class="le-select" style="visibility: hidden">

                                             <select class="hasCustomSelect" style="-webkit-appearance: menulist-button; width: 89px; position: absolute; opacity: 0; height: 18px; font-size: 13px;">
                                                 <option value="1">24 per page</option>
                                                 <option value="2">48 per page</option>
                                                 <option value="3">32 per page</option>
                                             </select><span class="le-select-in" style="display: inline-block;"><span class="le-select-inInner" style="width: 89px; display: inline-block;">24 per page</span></span>

                                         </div>

                                          <div class="grid-list-buttons">
                                              <ul>
                                                  <li class="grid-list-button-item active"><a data-toggle="tab" href="#grid-view"><i class="fa fa-th-large"></i> Grid</a></li>
                                                  <li class="grid-list-button-item "><a data-toggle="tab" href="#list-view"><i class="fa fa-th-list"></i> List</a></li>
                                              </ul>
                                          </div>
                                      </div><!-- /.control-bar -->

                                      <div class="tab-content" >
                                          <div id="grid-view" class="products-grid fade tab-pane in active">

                                              <div class="product-grid-holder">
                                                  <div class="products-list row no-margin" id ="mainData<tiles:insertAttribute name='mainData' />">

                                                  </div><!-- /.products-list -->

                                              </div><!-- /.products-grid #list-view -->

                                          </div><!-- /.tab-content -->
                                          <div id="list-view" class="products-grid fade tab-pane">
                                                  <div class="products-list" id ="mainDataList<tiles:insertAttribute name='mainData' />">

                                                  </div><!-- /.products-list -->
                                          </div><!-- /.tab-content -->
                                          <div class="pagination-holder">
                                              <div class="row">
                                                  <div class="col-xs-12 text-left" style="width: 400px">
                                                      <div class="paginator" id="paginator1_<tiles:insertAttribute name='index' />" style=" display: none"></div>
                                                      <div class="paginator"  id="paginator2_<tiles:insertAttribute name='index'  />"></div>
                                                  </div>
                                                  <div class="col-xs-12 col-sm-6">
                                                      <div class="result-counter" id = "result-counter<tiles:insertAttribute name='index' />">
                                                          Showing <span>1-9</span> of <span>11</span> results
                                                      </div><!-- /.result-counter -->
                                                  </div>
                                              </div><!-- /.row -->
                                          </div><!-- /.pagination-holder -->
                                      </div><!-- /.grid-list-products -->
                                  </div>
                              </section><!-- /#gaming -->
                          </div>

              </div>
             <div style="clear: both;"></div>

        </div>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });

</script>


