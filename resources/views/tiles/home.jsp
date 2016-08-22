<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<link href="/css/rules.css" rel="stylesheet" type="text/css" >
<link href="/css/classic.css" rel="stylesheet" type="text/css" >
<link href="/css/shoping.css" rel="stylesheet" type="text/css" >
<link href="/css/compare.css" rel="stylesheet" type="text/css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="/js/jquery.json.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruler.js"></script>
<script type="text/javascript" src="/js/galary.js"></script>
<script type="text/javascript" src="/js/video.js"></script>
<script type="text/javascript" src="/js/shopingcart.js"></script>
<script type="text/javascript" src="/js/compare.js"></script>
<script>

    $(document).ready(function(){
        initPaginationParametrs();
        initPagination('/pagination/countActualObjects');
    });


</script>
        <div style="margin: auto; width: 700px;">
            <div class="paginator" id="paginator2" style=" display: none"></div>
        <div id ="mainData" class="homeCard">
        </div>
            <div class="paginator" id="paginator1"></div>

            <div id="galary" class="modal fade">
                <div class="modal-dialog" style="width: 100%; height: 100%;">
                    <div class="modal-content">
                        <div class="modal-header"><button class="close btn btn-default" type="button" data-dismiss="modal" >Закрыть</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <div class="modal-body">
                            <tiles:insertAttribute name="galary" />
                        </div>
                    </div>
                </div>
            </div>

            <div id="video" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header"><button class="close btn btn-default" type="button" data-dismiss="modal" >Закрыть</button>
                            <h4 class="modal-title">West 17th Townhouse</h4>
                        </div>
                        <div class="modal-body">
                            <tiles:insertAttribute name="video" />
                        </div>
                    </div>
                </div>
            </div>

        </div>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });

</script>

