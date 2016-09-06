<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.08.2016
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
                <div class="tabbable" style="width:100%;"> <!-- Only required for left/right tabs -->
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1" data-toggle="tab">Particleboard</a></li>
                        <li><a href="#tab2" data-toggle="tab">Plywood</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <table>
                                <tr>
                                    <td valign="top"><tiles:insertAttribute name="menuParticleboard" /></td>
                                    <td valign="top"><tiles:insertAttribute name="particleboard" /></td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <table>
                                <tr>
                                    <td valign="top"><tiles:insertAttribute name="menuPlywood" /></td>
                                    <td valign="top"><tiles:insertAttribute name="plywood" /></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>

