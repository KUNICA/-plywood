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
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/page-proofs-ruller.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruller-plywood.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/ruller-particleboard.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=JspConstants.APP_PATH%>/js/to-plug-ruller.js"></script>
<script type="text/javascript" src="/js/shopingcart.js"></script>
<script type="text/javascript" src="/js/compare.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        var index = <tiles:insertAttribute name='index' />;
        var scriptRuller = new ScriptParticleboard();
        var scriptRuller = toPlug(index);

        myMapParamSearch[<tiles:insertAttribute name='index' />] = new ParamSearch(<tiles:insertAttribute name='index' />,
                         "<tiles:insertAttribute name='mainData' />",scriptRuller);
        initPaginationParametrs(<tiles:insertAttribute name='index' />,scriptRuller.paramsUrl);
        initPagination(<tiles:insertAttribute name='index' />);

    });


</script>

        <div style="margin-top: 50px; width: 700px;">
                            <table>
                                <tr>
                                <td colspan = "2" style="text-align: center;">
                                        <b style="font-size: 24px; margin-left: 300px;">
                                            <tiles:insertAttribute name="type" />
                                        </b>
                                </td>
                                </tr>
                                <tr>
                                    <td valign="top"><tiles:insertAttribute name="menu" /></td>
                                    <td valign="top">
                                        <div style="margin: auto; width: 800px;">
                                            <div class="paginator" id="paginator1_<tiles:insertAttribute name='index' />" style=" display: none"></div>
                                            <div id ="mainData<tiles:insertAttribute name='mainData' />" class="catalog-item-table-view">
                                            </div>
                                        <div class="paginator" id="paginator2_<tiles:insertAttribute name='index' />"></div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
        </div>


        </div>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });

</script>

