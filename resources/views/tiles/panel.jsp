<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.08.2016
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <button class='btn btn-info buttonCompare' id = "compare_<tiles:insertAttribute name="type" />">compare ></button>
</div>
<div>
    <button class='btn btn-circle btn-info buttonCompare' id = "showHideHeader">
        <i class="material-icons" id="showHideHeaderIcon" data-toggle="tooltip" data-placement="tooltip" title="" data-original-title="show hide header">expand_less</i>
    </button>
</div>
<div>
    <button class='btn btn-circle btn-info' id = "componentProduct" style="float: right; margin-right: 30px; margin-top: 15px;">
        <i class="material-icons" id="componentProductIcon" data-toggle="tooltip" data-placement="tooltip" title="" data-original-title="next product">arrow_forward</i>
    </button>
</div>

<script>
    $(document).ready(function(){

        $("#showHideHeader").click(function () {
            if ($(".header").is(":hidden")) {

                $(".header").show("slow");
                document.getElementById("showHideHeaderIcon").textContent="expand_less";

            } else {

                $(".header").hide("slow");
                document.getElementById("showHideHeaderIcon").textContent="expand_more";

            }
            return false;
        });

        $("#componentProduct").click(function () {
            var url = "/home/<tiles:insertAttribute name="next_index" />";
            $( location ).attr("href", url);
        });
    });
</script>