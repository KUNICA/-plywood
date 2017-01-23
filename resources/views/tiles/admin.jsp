<%@ page import="com.ui.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<style>
    .thumb img {
        filter: none; /* IE6-9 */
        -webkit-filter: grayscale(0);
        border-radius:5px;
        background-color: #fff;
        border: 1px solid #ddd;
        padding:5px;
    }
    .thumb img:hover {
        /*  filter: gray; /* IE6-9 */
        -webkit-filter: grayscale(1);
    }
    .thumb {
        padding:5px;
    }
</style>

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

<script type="text/javascript">
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

        $('form input[type=file]').on('change', function() {
            var filePatch = document.getElementById("filePatch");
            var file = document.getElementById("file");
            filePatch.value = file.value;

            var filePatchData = document.getElementById("filePatchData");
            var fileData = document.getElementById("fileData");
            filePatchData.value = fileData.value;

        });

            var scriptRuller = new ScriptAdmin<tiles:insertAttribute name='mainData' />();
            myMapParamSearch[<tiles:insertAttribute name='index' />] = new ParamSearch(<tiles:insertAttribute name='index' />,
                    "mainDataParticleboard",scriptRuller,scriptRuller);
            scriptRuller.initPagination(<tiles:insertAttribute name='index' />);



    });
</script>

<div id="main">
    <!-- #header -->
    <div id="header">
        <!-- #logo -->
        <div id="logo">
            <a href="/admin/run" title="Go to Homepage"><span>Great Admin</span></a>
        </div>
        <!-- /#logo -->
        <!-- #user -->
        <div id="user">
            <h2>${personalData.fullName} <span>(admin)</span></h2>
        </div>
        <!-- /#user -->
    </div>
    <!-- /header -->
    <!-- #content -->
    <div id="content" >
        <div class="breadcrumbs">
            <a id = "componentProduct" href="/admin/<tiles:insertAttribute name='nextProduct'/>"><i class="fa fa-forward"></i> next product <span class="value"></span> </a>
        </div>
        <!-- box -->
        <div class="box" style="width: 1050px">
            <div class="headlines">
                <h2><span><tiles:insertAttribute name='mainData' /></span></h2>
                <a href="#" class="show-filter">show filter</a>
            </div>
            <!-- filter -->
            <div class="filter">
                <input type="text" value="column one" title="column one" class="input" />
                <input type="text" value="column two" title="column two" class="input" />
                <input type="text" value="column three" title="column three" class="input" />
                <input type="submit" value="Use filter" class="submit" />
            </div>
            <!-- /filter -->

            <!-- table -->
            <table class="tab tab-drag" id="main<tiles:insertAttribute name='mainData' />">
            </table>
            <!-- /table -->

            <!-- box-action -->
            <div class="tab-action" style="width: 500px">
                <div class="box-content">
                    <form class="formBox" method="post" action="${fileUploadControllerURL}/<tiles:insertAttribute name='mainData' />" enctype="multipart/form-data">
                        <fieldset>
                            <div class="file">
                                <div class="lab"><label for="file">Upload file</label></div>
                                <div class="con"><input id="filePatchData" disabled = "disabled"  class="file upload-file"><div class="button-upload"><input type="file" name="file" class="upload-file" id="fileData" style="position: relative; height: 29px; width: 300px; display: inline; cursor: pointer; opacity: 0; margin-left: -201px;"></div>
                                </div>
                            </div>
                            <div class="btn-submit"><!-- Submit form -->
                                <input type="submit" value="Submit form" class="button">
                                ${error}
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!-- /box-action -->
            <div style=" margin: 10px 20px 20px 0; text-align: right; overflow: hidden;" >
                <div class="paginator" id="paginator1_<tiles:insertAttribute name='index' />" style=" display: none"></div>
                <div class="paginator"   id="paginator2_<tiles:insertAttribute name='index' />"  style="margin-left: 20px; width: 380px"></div>
            </div>
        </div>
        <!-- /box -->

        <!-- box -->
        <div class="box">
            <div class="headlines">
                <h2><span>Gallery</span></h2>
            </div>
            <div class="box-content">

                <!-- gallery -->
                <!-- gallery -->
                <div class="gallery" id = "galleryId">
                </div>
                <!-- /gallery -->
            </div><!-- /box-content -->
        </div>


        <!-- /box -->
        <div class="box-content">
        <form class="formBox" method="post" action="${fileSaveControllerURL}" enctype="multipart/form-data">
            <fieldset>
                <div class="file">
                    <div class="lab"><label for="file">Upload file</label></div>
                    <div class="con"><input id="filePatch" disabled = "disabled"  class="file upload-file"><div class="button-upload"><input type="file" name="fileImage" class="upload-file" id="file" style="position: relative; height: 29px; width: 300px; display: inline; cursor: pointer; opacity: 0; margin-left: -201px;"></div>
                    </div>
                </div>
                <div class="btn-submit"><!-- Submit form -->
                    <input type="submit" value="Submit form" class="button">
                    ${errorImage}
                </div>
            </fieldset>
        </form>
        </div>
        <!-- /box -->


    </div>
    <!-- /#content -->
    <!-- #sidebar -->
    <tiles:insertAttribute name='dashboard'/>
    <!-- /#sidebar -->
    <!-- #footer -->
    <div id="footer">
        <p>© 2010 Great Admin | <a href="#main">Top</a></p>
    </div>
    <!-- /#footer -->

    <!-- MODAL WINDOW -->
    <div id="modal" class="modal-window">

        <h2>Example modal window</h2>

        <!-- Warning form message -->
        <div class="form-message warning">
            <p>On the page the following error occurred.</p>
        </div>

        <p>Suspendisse et ante vitae turpis vestibulum fermentum nec nec elit. Suspendisse ullamcorper lacus in arcu mollis fringilla porta mi placerat. Ut at elit non diam tristique scelerisque. </p>

    </div>

    <!-- HELP WINDOW -->
    <div id="help" class="modal-window">

        <h2>Example help window</h2>

        <p>Suspendisse et ante vitae turpis vestibulum fermentum nec nec elit. Suspendisse ullamcorper lacus in arcu mollis fringilla porta mi placerat. Ut at elit non diam tristique scelerisque. </p>

        <ul class="list list-square">
            <li><strong>Lorem ipsum</strong>  dolor sit amet</li>
            <li><strong>consectetur adipiscing</strong> elit phasellus et risus</li>
            <li><strong>Maecenas non</strong> nunc proin eleifend viverra sapien</li>
        </ul>

    </div>


</div>