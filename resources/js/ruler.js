/**
 * Created by user on 04.08.2016.
 */



var current = null;
var countP = null
var mselect = 10;
var parent_element_id = 'mainData';

var Paginator = function(paginatorHolderId, pagesTotal, pagesSpan, pageCurrent, baseUrl){
    if(!document.getElementById(paginatorHolderId) || !pagesTotal || !pagesSpan) return false;
    countP = pagesTotal;
    this.inputData = {
        paginatorHolderId: paginatorHolderId,
        pagesTotal: pagesTotal,
        pagesSpan: pagesSpan < pagesTotal ? pagesSpan : pagesTotal,
        pageCurrent: pageCurrent,
        baseUrl: baseUrl ? baseUrl : '/pages/'
    };

    current = pageCurrent;

    this.html = {
        holder: null,

        table: null,
        trPages: null,
        trScrollBar: null,
        tdsPages: null,

        scrollBar: null,
        scrollThumb: null,

        pageCurrentMark: null
    };


    this.prepareHtml();

    this.initScrollThumb();
    this.initPageCurrentMark();
    this.initEvents();

    this.scrollToPageCurrent();

    document.all?document.attachEvent('onclick',chekClick):document.addEventListener('click',chekClick,false);
}

function chekClick(e) {
    e?evt=e:evt=event;
    var element = evt.target?evt.target:evt.srcElement;
    if(element.id!=null && element.id.indexOf("cellCurrent")+1){
        current = element.textContent;
        nextPage(element);
    }
    if(element.id!=null && element.id == 'arrowleftpaginator1'){
        clickArrowLeft(e);
    }
    if(element.id!=null && element.id == 'arrowrightpaginator1'){
        clickArrowRight(e);
    }
    if(element.id!=null && element.id == 'arrowleftpaginator2'){
        clickArrowLeft2(e);
    }
    if(element.id!=null && element.id == 'arrowrightpaginator2'){
        clickArrowRight2(e);
    }
}

function getPosition(clientX,clientY) {
    var posX = clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
    var posY = clientY + document.body.scrollTop + document.documentElement.scrollTop;
    return {x:posX, y:posY}
}
function clickArrowLeft(e) {
    var pag1 = document.getElementById("paginator1");
    var tabl = document.getElementById("tableIdpaginator1");
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    if(pag1!=null && matchClass(pag1.paginatorBox, 'fullsize')) return;
    scrollthumb.xPos -=  scrollmark.offsetWidth*10;
    scrollthumb.style.left = scrollthumb.xPos + "px";
    mselect = document.getElementById("idSearchList");
    var percentFromLeft = scrollthumb.xPos/(tabl.offsetWidth);
    var cellFirstValue = Math.round(percentFromLeft * countP);




    var html = "";
    // drawing pages control the position of the scrollThumb on the edges!
    if(cellFirstValue < 1){
        cellFirstValue = 1;
        scrollthumb.xPos = 0;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    } else if(cellFirstValue >= countP - 10) {
        cellFirstValue = countP - 10 + 1;
        scrollthumb.xPos = tabl.offsetWidth - scrollthumb.offsetWidth;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    }
    var trPages = tabl.getElementsByTagName('tr')[0];
    var tdsPages = trPages.getElementsByTagName('td');


    for(var i=0; i<tdsPages.length; i++){
        var cellCurrentValue = cellFirstValue + i;
        if(cellCurrentValue == current){
            html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen;    cursor:pointer;'>" + cellCurrentValue  + "</span>";
        } else {
            html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
        }
        tdsPages[i].innerHTML = html;
    }
    this.srollDisabled();
}
function clickArrowRight(e) {
    var pag1 = document.getElementById("paginator1");
    var tabl = document.getElementById("tableIdpaginator1");
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    var trPages = tabl.getElementsByTagName('tr')[0];
    var tdsPages = trPages.getElementsByTagName('td');

    if(pag1!=null && matchClass(pag1.paginatorBox, 'fullsize')) return;
    scrollthumb.xPos +=  scrollmark.offsetWidth*10;
    scrollthumb.style.left = scrollthumb.xPos + "px";
    //mselect = document.getElementById("idSearchList");
    var percentFromLeft = scrollthumb.xPos/(tabl.offsetWidth);
    var cellFirstValue = Math.round(percentFromLeft * countP);
    if(cellFirstValue>=countP){scrollthumb.xPos -=  scrollmark.offsetWidth*10; scrollthumb.style.left = scrollthumb.xPos + "px"; return;}


    var html = "";
    // drawing pages control the position of the scrollThumb on the edges!
    if(cellFirstValue < 1){
        cellFirstValue = 1;
        scrollthumb.xPos = 0;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    } else if(cellFirstValue >= countP - 10 && countP - 10>0) {
        cellFirstValue = countP - 10 + 1;
        scrollthumb.xPos = tabl.offsetWidth - scrollthumb.offsetWidth;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    }


    if(cellFirstValue<countP){
        for(var i=0; i<tdsPages.length; i++){
            var cellCurrentValue = cellFirstValue + i;
            if(cellCurrentValue == current){
                html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
            } else {
                html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
            }
            tdsPages[i].innerHTML = html;
        }

    }

    this.srollDisabled();
}
function clickArrowLeft2(e) {
    var pag1 = document.getElementById("paginator2");
    var tabl = document.getElementById("tableIdpaginator2");
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    if(pag1!=null && matchClass(pag1.paginatorBox, 'fullsize')) return;
    scrollthumb.xPos -=  scrollmark.offsetWidth*10;
    scrollthumb.style.left = scrollthumb.xPos + "px";
    //mselect = document.getElementById("idSearchList");
    var percentFromLeft = scrollthumb.xPos/(tabl.offsetWidth);
    var cellFirstValue = Math.round(percentFromLeft * countP);



    var html = "";
    // drawing pages control the position of the scrollThumb on the edges!
    if(cellFirstValue < 1){
        cellFirstValue = 1;
        scrollthumb.xPos = 0;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    } else if(cellFirstValue >= countP - 10) {
        cellFirstValue = countP - 10 + 1;
        scrollthumb.xPos = tabl.offsetWidth - scrollthumb.offsetWidth;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    }
    var trPages = tabl.getElementsByTagName('tr')[0];
    var tdsPages = trPages.getElementsByTagName('td');


    for(var i=0; i<tdsPages.length; i++){
        var cellCurrentValue = cellFirstValue + i;
        if(cellCurrentValue == current){
            html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
        } else {
            html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
        }
        tdsPages[i].innerHTML = html;
    }
    this.srollDisabled();
}
function clickArrowRight2(e) {
    var pag1 = document.getElementById("paginator2");
    var tabl = document.getElementById("tableIdpaginator2");
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    var trPages = tabl.getElementsByTagName('tr')[0];
    var tdsPages = trPages.getElementsByTagName('td');

    if(pag1!=null && matchClass(pag1.paginatorBox, 'fullsize')) return;
    scrollthumb.xPos +=  scrollmark.offsetWidth*10;
    scrollthumb.style.left = scrollthumb.xPos + "px";
    //mselect = document.getElementById("idSearchList");
    var percentFromLeft = scrollthumb.xPos/(tabl.offsetWidth);
    var cellFirstValue = Math.round(percentFromLeft * countP);
    if(cellFirstValue>=countP){scrollthumb.xPos -=  scrollmark.offsetWidth*10; scrollthumb.style.left = scrollthumb.xPos + "px"; return;}


    var html = "";
    // drawing pages control the position of the scrollThumb on the edges!
    if(cellFirstValue < 1){
        cellFirstValue = 1;
        scrollthumb.xPos = 0;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    } else if(cellFirstValue >= countP - 10 && countP - 10>0) {
        cellFirstValue = countP - 10 + 1;
        scrollthumb.xPos = tabl.offsetWidth - scrollthumb.offsetWidth;
        scrollthumb.style.left = scrollthumb.xPos + "px";
    }


    if(cellFirstValue<countP){
        for(var i=0; i<tdsPages.length; i++){
            var cellCurrentValue = cellFirstValue + i;
            if(cellCurrentValue == current){
                html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
            } else {
                html = "<span id=" + "'cellCurrent" + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
            }
            tdsPages[i].innerHTML = html;
        }

    }
    this.srollDisabled();
}
/*
 Set all .html properties (links to dom objects)
 */
Paginator.prototype.prepareHtml = function(){

    this.html.holder = document.getElementById(this.inputData.paginatorHolderId);
    this.html.holder.innerHTML = this.makePagesTableHtml();

    this.html.table = this.html.holder.getElementsByTagName('table')[0];

    var trPages = this.html.table.getElementsByTagName('tr')[0];
    this.html.tdsPages = trPages.getElementsByTagName('td');

    this.html.scrollBar = getElementsByClassName(this.html.table, 'div', 'scroll_bar')[0];
    this.html.scrollThumb = getElementsByClassName(this.html.table, 'div', 'scroll_thumb')[0];
    this.html.pageCurrentMark = getElementsByClassName(this.html.table, 'div', 'current_page_mark')[0];
    this.srollDisabled();
}

Paginator.prototype.srollDisabled = function(){
    if(this.inputData.pagesTotal<=this.inputData.pagesSpan){
        var scroll = document.getElementsByClassName("scroll_bar")[0];
        var arrowRight = document.getElementsByClassName("arrow_right")[0];
        var arrowLeft = document.getElementsByClassName("arrow_left")[0];
        scroll.style.display = 'none';
        arrowRight.style.display = 'none';
        arrowLeft.style.display = 'none';
    } 
}

/*
 Make html for pages (table)
 */
Paginator.prototype.makePagesTableHtml = function(){
    var tdWidth = (100 / this.inputData.pagesSpan) + '%';
    var htmlPols =
        '<table id = "tableId' + this.inputData.paginatorHolderId +  '"style="float:left"; width="100%">' + '<tr>'  ;

    for (var i=1; i<=this.inputData.pagesSpan; i++){
        htmlPols += '<td width="' + tdWidth + '"></td>';
    }
    htmlPols += ''  +
        '</tr>' +
        '<tr>' +
        '<td colspan="' + this.inputData.pagesSpan + '">' +
        '<div class="scroll_bar">' +
        '<div class="scroll_trough"></div>' +
        '<div style="display:block;"  class="scroll_thumb">' +
        '<div class="scroll_knob"></div>' +
        '</div>' +
        '<div class="current_page_mark"></div>' +
        '</div>' +
        '</td>' +
        '</tr>' +
        '</table>';
    var html = null;
    if(this.inputData.paginatorHolderId == 'paginator1'){
        html =  '' +  '<div id="s-all">' +
            '<div id="countPage1"></div>' +
            '<div style="clear:both;">'   +
            '<div id="s-col1"><div class="arrow_left" style="height:20px; width:20px; margin-top: 25px;margin-left: 42px;" id="arrowleft' + this.inputData.paginatorHolderId + '"></div></div>' +
            '<div id="s-col2"><div class="arrow_right" style="height:20px; width:20px; margin-top: 25px;" id="arrowright' + this.inputData.paginatorHolderId + '"></div></div>' +
            '<div id="s-content">' + htmlPols + '</div>' +
            '<div style="clear:both;">' +
            //  '<div id="footer">дно</div>'+
            '</div> ';
    }
    else{
        html =  '' +  '<div id="s-all">' +
            // '<div id="s-header">шапка</div>' +
            '<div style="clear:both;">'   +
            '<div id="s-col1"><div class="arrow_left" style="height:20px; width:20px; margin-top: 25px; margin-left: 42px;" id="arrowleft' + this.inputData.paginatorHolderId + '"></div></div>' +
            '<div id="s-col2"><div class="arrow_right" style="height:20px; width:20px; margin-top: 25px;" id="arrowright' + this.inputData.paginatorHolderId + '"></div></div>' +
            '<div id="s-content">' + htmlPols + '</div>' +
            '<div style="clear:both;">' +
            '<div id="countPage2"></div>'+
            '</div> ';
    }
    return html;
}

/*
 Set all needed properties for scrollThumb and it's width
 */
Paginator.prototype.initScrollThumb = function(){
    this.html.scrollThumb.widthMin = '8'; // minimum width of the scrollThumb (px)
    this.html.scrollThumb.widthPercent = this.inputData.pagesSpan/this.inputData.pagesTotal * 100;

    this.html.scrollThumb.xPosPageCurrent = (this.inputData.pageCurrent - Math.round(this.inputData.pagesSpan/2))/this.inputData.pagesTotal * this.html.table.offsetWidth;
    this.html.scrollThumb.xPos = this.html.scrollThumb.xPosPageCurrent;

    this.html.scrollThumb.xPosMin = 0;
    this.html.scrollThumb.xPosMax;

    this.html.scrollThumb.widthActual;

    this.setScrollThumbWidth();

}

Paginator.prototype.setScrollThumbWidth = function(){
    // Try to set width in percents
    this.html.scrollThumb.style.width = this.html.scrollThumb.widthPercent + "%";

    // Fix the actual width in px
    this.html.scrollThumb.widthActual = this.html.scrollThumb.offsetWidth;

    // If actual width less then minimum which we set
    if(this.html.scrollThumb.widthActual < this.html.scrollThumb.widthMin){
        this.html.scrollThumb.style.width = this.html.scrollThumb.widthMin + 'px';
    }

    this.html.scrollThumb.xPosMax = this.html.table.offsetWidth - this.html.scrollThumb.widthActual;
}

Paginator.prototype.moveScrollThumb = function(){
    this.html.scrollThumb.style.left = this.html.scrollThumb.xPos + "px";
}


/*
 Set all needed properties for pageCurrentMark, it's width and move it
 */
Paginator.prototype.initPageCurrentMark = function(){
    this.html.pageCurrentMark.widthMin = '3';
    this.html.pageCurrentMark.widthPercent = 100 / this.inputData.pagesTotal;
    this.html.pageCurrentMark.widthActual;

    this.setPageCurrentPointWidth();
    this.movePageCurrentPoint();
}

Paginator.prototype.setPageCurrentPointWidth = function(){
    // Try to set width in percents
    this.html.pageCurrentMark.style.width = this.html.pageCurrentMark.widthPercent + '%';

    // Fix the actual width in px
    this.html.pageCurrentMark.widthActual = this.html.pageCurrentMark.offsetWidth;

    // If actual width less then minimum which we set
    if(this.html.pageCurrentMark.widthActual < this.html.pageCurrentMark.widthMin){
        this.html.pageCurrentMark.style.width = this.html.pageCurrentMark.widthMin + 'px';
    }
}

Paginator.prototype.movePageCurrentPoint = function(){
    if(this.html.pageCurrentMark.widthActual < this.html.pageCurrentMark.offsetWidth){
        this.html.pageCurrentMark.style.left = (this.inputData.pageCurrent - 1)/this.inputData.pagesTotal * this.html.table.offsetWidth - this.html.pageCurrentMark.offsetWidth/2 + "px";
    } else {
        this.html.pageCurrentMark.style.left = (this.inputData.pageCurrent - 1)/this.inputData.pagesTotal * this.html.table.offsetWidth + "px";
    }
}



/*
 Drag, click and resize events
 */
Paginator.prototype.initEvents = function(){
    var _this = this;

    this.html.scrollThumb.onmousedown = function(e){
        if (!e) var e = window.event;
        e.cancelBubble = true;
        if (e.stopPropagation) e.stopPropagation();

        var dx = getMousePosition(e).x - this.xPos;
        document.onmousemove = function(e){
            if (!e) var e = window.event;
            _this.html.scrollThumb.xPos = getMousePosition(e).x - dx;

            // the first: draw pages, the second: move scrollThumb (it was logically but ie sucks!)
            _this.moveScrollThumb();
            _this.drawPages();


        }
        document.onmouseup = function(){
            document.onmousemove = null;
            _this.enableSelection();
        }
        _this.disableSelection();
    }

    this.html.scrollBar.onmousedown = function(e){
        if (!e) var e = window.event;
        if(matchClass(_this.paginatorBox, 'fullsize')) return;

        _this.html.scrollThumb.xPos = getMousePosition(e).x - getPageX(_this.html.scrollBar) - _this.html.scrollThumb.offsetWidth/2;

        _this.moveScrollThumb();
        _this.drawPages();


    }

    // Comment the row beneath if you set paginator width fixed
    addEvent(window, 'resize', function(){Paginator.resizePaginator(_this)});
}

/*
 Redraw current span of pages
 */
Paginator.prototype.drawPages = function(){
    var percentFromLeft = this.html.scrollThumb.xPos/(this.html.table.offsetWidth);
    var cellFirstValue = Math.round(percentFromLeft * this.inputData.pagesTotal);

    var html = "";
    // drawing pages control the position of the scrollThumb on the edges!
    if(cellFirstValue < 1){
        cellFirstValue = 1;
        this.html.scrollThumb.xPos = 0;
        this.moveScrollThumb();
    } else if(cellFirstValue >= this.inputData.pagesTotal - this.inputData.pagesSpan) {
        cellFirstValue = this.inputData.pagesTotal - this.inputData.pagesSpan + 1;
        this.html.scrollThumb.xPos = this.html.table.offsetWidth - this.html.scrollThumb.offsetWidth;
        this.moveScrollThumb();
    }



    for(var i=0; i<this.html.tdsPages.length; i++){
        var cellCurrentValue = cellFirstValue + i;
        if(cellCurrentValue == current){
            html = "<span id=" + "'cellCurrent" + this.inputData.paginatorHolderId + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;  cursor:pointer;'>" + cellCurrentValue  + "</span>";
        } else {
            html = "<span id=" + "'cellCurrent" + this.inputData.paginatorHolderId + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
        }
        this.html.tdsPages[i].innerHTML = html;
    }

}

/*
 Scroll to current page
 */
Paginator.prototype.scrollToPageCurrent = function(){
    this.html.scrollThumb.xPosPageCurrent = (this.inputData.pageCurrent - Math.round(this.inputData.pagesSpan/2))/this.inputData.pagesTotal * this.html.table.offsetWidth;
    this.html.scrollThumb.xPos = this.html.scrollThumb.xPosPageCurrent;

    this.moveScrollThumb();
    this.drawPages();

}



Paginator.prototype.disableSelection = function(){
    document.onselectstart = function(){
        return false;
    }
    this.html.scrollThumb.focus();
}

Paginator.prototype.enableSelection = function(){
    document.onselectstart = function(){
        return true;
    }
}

/*
 Function is used when paginator was resized (window.onresize fires it automatically)
 Use it when you change paginator with DHTML
 Do not use it if you set fixed width of paginator
 */
Paginator.resizePaginator = function (paginatorObj){

    paginatorObj.setPageCurrentPointWidth();
    paginatorObj.movePageCurrentPoint();

    paginatorObj.setScrollThumbWidth();
    paginatorObj.scrollToPageCurrent();
}




/*
 Global functions which are used
 */
function getElementsByClassName(objParentNode, strNodeName, strClassName){
    var nodes = objParentNode.getElementsByTagName(strNodeName);
    if(!strClassName){
        return nodes;
    }
    var nodesWithClassName = [];
    for(var i=0; i<nodes.length; i++){
        if(matchClass( nodes[i], strClassName )){
            nodesWithClassName[nodesWithClassName.length] = nodes[i];
        }
    }
    return nodesWithClassName;
}


function addClass( objNode, strNewClass ) {
    replaceClass( objNode, strNewClass, '' );
}

function removeClass( objNode, strCurrClass ) {
    replaceClass( objNode, '', strCurrClass );
}

function replaceClass( objNode, strNewClass, strCurrClass ) {
    var strOldClass = strNewClass;
    if ( strCurrClass && strCurrClass.length ){
        strCurrClass = strCurrClass.replace( /\s+(\S)/g, '|$1' );
        if ( strOldClass.length ) strOldClass += '|';
        strOldClass += strCurrClass;
    }
    objNode.className = objNode.className.replace( new RegExp('(^|\\s+)(' + strOldClass + ')($|\\s+)', 'g'), '$1' );
    objNode.className += ( (objNode.className.length)? ' ' : '' ) + strNewClass;
}

function matchClass( objNode, strCurrClass ) {
    return ( objNode && objNode.className.length && objNode.className.match( new RegExp('(^|\\s+)(' + strCurrClass + ')($|\\s+)') ) );
}


function addEvent(objElement, strEventType, ptrEventFunc) {
    if (objElement.addEventListener)
        objElement.addEventListener(strEventType, ptrEventFunc, false);
    else if (objElement.attachEvent)
        objElement.attachEvent('on' + strEventType, ptrEventFunc);
}
function removeEvent(objElement, strEventType, ptrEventFunc) {
    if (objElement.removeEventListener) objElement.removeEventListener(strEventType, ptrEventFunc, false);
    else if (objElement.detachEvent) objElement.detachEvent('on' + strEventType, ptrEventFunc);
}


function getPageY( oElement ) {
    var iPosY = oElement.offsetTop;
    while ( oElement.offsetParent != null ) {
        oElement = oElement.offsetParent;
        iPosY += oElement.offsetTop;
        if (oElement.tagName == 'BODY') break;
    }
    return iPosY;
}

function getPageX( oElement ) {
    var iPosX = oElement.offsetLeft;
    while ( oElement.offsetParent != null ) {
        oElement = oElement.offsetParent;
        iPosX += oElement.offsetLeft;
        if (oElement.tagName == 'BODY') break;
    }
    return iPosX;
}

function getMousePosition(e) {
    if (e.pageX || e.pageY){
        var posX = e.pageX;
        var posY = e.pageY;
    }else if (e.clientX || e.clientY) 	{
        var posX = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
        var posY = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
    }
    return {x:posX, y:posY}
}

function destroyChildren(node)
{
    while (node.firstChild)
        node.removeChild(node.firstChild);
}

function initPagination(urlCount) {

    var elementPrice = document.getElementById("ex2");
    var priceList = elementPrice.value.split(",");
    var minPrice = priceList[0];
    var maxPrice = priceList[1];
    var elementPersons = document.getElementById("ex1");
    var persons =  elementPersons.value;
    var elementBadrooms = document.getElementById("ex3");
    var badrooms =  elementBadrooms.value;
    var elementBathrooms = document.getElementById("ex4");
    var bathrooms =  elementBathrooms.value;

    var  data={
        "start": 0,
        "end": 0,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "persons": persons,
        "badrooms": badrooms,
        "bathrooms":bathrooms
    };

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlCount,
        dataType:'json',
        data:  $.toJSON(data),
        async: false
    }).done(function( count ) {

                var element = document.getElementById(parent_element_id);

                var p1 = document.getElementById("paginator1");
                var p2 = document.getElementById("paginator2");
                destroyChildren(p1);
                destroyChildren(p2);
                //var mselect = 10;
                var coutnPage;
                if (mselect != null && mselect != '' && mselect != '0') {
                    coutnPage = count / mselect;
                }
                if (Math.ceil(coutnPage) > 0) {
                    pag1 = new Paginator("paginator1", Math.ceil(coutnPage), 5, 1, "");
                    pag2 = new Paginator("paginator2", Math.ceil(coutnPage), 5, 1, "");
                    var pCount1 = document.getElementById("countPage1");
                    var pCount2 = document.getElementById("countPage2");
                   // if (pCount1 != null) {
                   //     pCount1.textContent = "pages:" + Math.ceil(coutnPage);
                   // }
                    if (pCount2 != null) {
                        pCount2.textContent = "pages:" + Math.ceil(coutnPage);
                    }
                }
                pag1.srollDisabled();
                pag2.srollDisabled();
                if (element != undefined) {
                    nextPage(element);
                }
            });
}

function getObjects(urlObjects,element,start,end,minPrice,maxPrice,persons,badrooms,bathrooms) {
    var  data={
        "start": start,
        "end": end,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "persons": persons,
        "badrooms": badrooms,
        "bathrooms":bathrooms
    };

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlObjects,
        dataType:'json',
        data:  $.toJSON(data),
        async: false
    }).done(function( objects ) {
        var parentElement = document.getElementById(parent_element_id);
        // стираем данные

        parentElement.textContent = "";
        addData(objects,parentElement);

    });
}

    function nextPage(cellCurrentValue) {
        var cellFirstValue = 0;

        var pag = document.getElementById("paginator1");
        for (var i = 1; i <= countP; i++) {
            var el1 = document.getElementById("cellCurrent" + "paginator1" + i);
            if (el1 != null && el1.style != null) {
                el1.style.background = '#ffffff';
                el1.style.color = 'black';
            }
        }
        var pag2 = document.getElementById("paginator2");
        for (var j = 1; j <= countP; j++) {
            var el = document.getElementById("cellCurrent" + "paginator2" + j);
            if (el != null && el.style != null) {
                el.style.background = '#ffffff';
                el.style.color = 'black';
            }
        }
        var id1 = cellCurrentValue.id.substr(21, cellCurrentValue.id.length - 1);
        var mel1 = document.getElementById("cellCurrent" + "paginator1" + id1);
        var mel2 = document.getElementById("cellCurrent" + "paginator2" + id1);
        if (mel1 != null) {
            mel1.style.color  = 'yellowgreen';
        }
        if (mel2 != null) {
            mel2.style.color  = 'yellowgreen';
        }


        // методы добавления, очистки и получения данных

        var dataList = null;
        var start = id1 * mselect - mselect;
        var end = mselect;
        var elementPrice = document.getElementById("ex2");
        var priceList = elementPrice.value.split(",");
        var minPrice = priceList[0];
        var maxPrice = priceList[1];
        var elementPersons = document.getElementById("ex1");
        var persons =  elementPersons.value;
        var elementBadrooms = document.getElementById("ex3");
        var badrooms =  elementBadrooms.value;
        var elementBathrooms = document.getElementById("ex4");
        var bathrooms =  elementBathrooms.value;

        getObjects("/pagination/objects",$(".homeCard"),start,end,minPrice,maxPrice,persons,badrooms,bathrooms);

}

function makePreview(image, a) {
    var img = image,
        w = img.width, h = img.height,
        s = w / h;

    if(w > a && h > a) {
        if(img.width > img.height) {
            img.width = a;
            img.height = a / s;
        } else {
            img.height = a;
            img.width = a * s;
        }
    }

    return img;
}


function addData(objects,parentElement) {
    for (var i = 0; i < objects.length; i++) {

        var ref = document.createElement("div");
        ref.classList.add( "homeCardLink", "searchItemsItem" );
        ref.href = "#";
        ref.id = "idRef" + objects[i].id;
        parentElement.appendChild(ref);

        var homeCardImage = document.createElement("div"); 
            homeCardImage.classList.add( "homeCardImage" );
        ref.appendChild(homeCardImage);


        jQuery.ajax({
            type: "POST",
            url: "/pagination/imgPatch/" + objects[i].id,
            async: false
        }).done(function( m_url ) {
            var responsiveImageImage = document.createElement("img");
            responsiveImageImage.classList.add( "responsiveImageImage" );
            //responsiveImageImage.src = "/pagination/img/" + objects[i].id;
            responsiveImageImage.src = "/images/product/" + m_url.img;
            var imgPreview = makePreview(responsiveImageImage, 512);
            homeCardImage.appendChild(imgPreview);
        });


        var homeCardInfo = document.createElement("div");
        homeCardInfo.classList.add("homeCardInfo");
        ref.appendChild(homeCardInfo);

        var homeCardTitle = document.createElement("h3");
        homeCardTitle.classList.add( "homeCardTitle" );
        homeCardTitle.textContent = objects[i].nameImage;
        homeCardInfo.appendChild(homeCardTitle);

        var homeCardSubtitle = document.createElement("h5");
        homeCardSubtitle.classList.add( "homeCardSubtitle" );
        homeCardSubtitle.textContent = objects[i].adress.remark;
        homeCardInfo.appendChild(homeCardSubtitle);

        var homeCardFeatures = document.createElement("div");
        homeCardFeatures.classList.add( "homeCardFeatures" );
        homeCardInfo.appendChild(homeCardFeatures);

        var homeCardFeaturePersons = document.createElement("div");
        homeCardFeaturePersons.classList.add( "homeCardFeature" );
        homeCardFeatures.appendChild(homeCardFeaturePersons);

        var persons = document.createElement("i");
        persons.classList.add( "material-icons");
        persons.textContent = "group";
        persons.setAttribute('data-toggle', 'tooltip');
        persons.setAttribute('data-placement', 'tooltip');
        persons.setAttribute('title', 'Persons');
        homeCardFeaturePersons.appendChild(persons);
        var personsSpan = document.createElement("span");
        personsSpan.textContent = objects[i].slleeps;
        homeCardFeaturePersons.appendChild(personsSpan);


        var homeCardFeatureBedrooms = document.createElement("div");
        homeCardFeatureBedrooms.classList.add( "homeCardFeature" );
        homeCardFeatures.appendChild(homeCardFeatureBedrooms);

        var bedrooms = document.createElement("i");
        bedrooms.classList.add( "material-icons");
        bedrooms.textContent = "airline_seat_individual_suite";
        bedrooms.setAttribute('data-toggle', 'tooltip');
        bedrooms.setAttribute('data-placement', 'tooltip');
        bedrooms.setAttribute('title', 'Bedrooms');
        homeCardFeatureBedrooms.appendChild(bedrooms);
        var bedroomsSpan = document.createElement("span");
        bedroomsSpan.textContent = objects[i].countRoums;
        homeCardFeatureBedrooms.appendChild(bedroomsSpan);

        var homeCardFeatureBathrooms = document.createElement("div");
        homeCardFeatureBathrooms.classList.add( "homeCardFeature" );
        homeCardFeatures.appendChild(homeCardFeatureBathrooms);

        var bathrooms = document.createElement("i");
        bathrooms.classList.add( "material-icons");
        bathrooms.textContent = "hot_tub";
        bathrooms.setAttribute('data-toggle', 'tooltip');
        bathrooms.setAttribute('data-placement', 'tooltip');
        bathrooms.setAttribute('title', 'Bathrooms');
        homeCardFeatureBathrooms.appendChild(bathrooms);
        var bathroomsSpan = document.createElement("span");
        bathroomsSpan.textContent = objects[i].bathrooms;
        homeCardFeatureBathrooms.appendChild(bathroomsSpan);

        var homeCardPrice = document.createElement("div");
        homeCardPrice.classList.add( "homeCardPrice");
        homeCardInfo.appendChild(homeCardPrice);

        var span = document.createElement("span");
        homeCardPrice.appendChild(span);

        var span2 = document.createElement("span");
        span2.textContent = "from";
        span.appendChild(span2);

        var span3 = document.createElement("span");
        span.appendChild(span3);

        var homeCardPriceValue = document.createElement("span");
        homeCardPriceValue.classList.add( "homeCardPriceValue");
        homeCardPriceValue.textContent = objects[i].price + "$ ";
        span.appendChild(homeCardPriceValue);

        var span4 = document.createElement("span");
        homeCardPrice.appendChild(span4);

        var span5 = document.createElement("span");
        span5.textContent = "/night";
        homeCardPrice.appendChild(span5);

        addDataCompare(homeCardInfo,objects[i]);

        var homeCardFeatureGalary = document.createElement("div");
        homeCardFeatureGalary.classList.add( "homeCardFeature", "homeCardGalary","Galary");
        homeCardInfo.appendChild(homeCardFeatureGalary);

        var homeCardGalary = document.createElement("i");
        homeCardGalary.classList.add( "material-icons");
        homeCardGalary.textContent = "camera_alt";
        homeCardGalary.setAttribute('data-toggle', 'tooltip');
        homeCardGalary.setAttribute('data-placement', 'tooltip');
        homeCardGalary.setAttribute('title', 'galary');
        homeCardGalary.id = "galary_" + objects[i].id;
        homeCardFeatureGalary.appendChild(homeCardGalary);

        var homeCardFeatureVideo= document.createElement("div");
        homeCardFeatureVideo.classList.add( "homeCardFeature", "homeCardGalary","Video");
        homeCardInfo.appendChild(homeCardFeatureVideo);

        var homeCardVideo = document.createElement("i");
        homeCardVideo.classList.add( "material-icons");
        homeCardVideo.textContent = "local_movies";
        homeCardVideo.setAttribute('data-toggle', 'tooltip');
        homeCardVideo.setAttribute('data-placement', 'tooltip');
        homeCardVideo.setAttribute('title', 'video');
        homeCardVideo.id = "video_" + objects[i].id;
        homeCardFeatureVideo.appendChild(homeCardVideo);

        addDataShoping(homeCardInfo,objects[i]);

    }

}


function addDataShoping(parentElement,object){

    urlObjects = "/shoping/cart/" + object.id;

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlObjects,
        dataType:'json',
        async: false
    }).done(function(product) {
        var homeCardFeatureShoppingCar= document.createElement("div");
        homeCardFeatureShoppingCar.classList.add( "homeCardFeature", "homeCardGalary","ShoppingCar");
        parentElement.appendChild(homeCardFeatureShoppingCar);

        var homeCardShoppingCar = document.createElement("i");
        if(product!= undefined && product.check){
            homeCardShoppingCar.classList.add("ShoppingCartCheck");
            homeCardShoppingCar.textContent = "out_shopping_cart";
        }
        else{
            homeCardShoppingCar.textContent = "add_shopping_cart";
        }
        homeCardShoppingCar.classList.add( "material-icons");
        homeCardShoppingCar.setAttribute('data-toggle', 'tooltip');
        homeCardShoppingCar.setAttribute('data-placement', 'tooltip');
        homeCardShoppingCar.setAttribute('title', 'to order');
        homeCardShoppingCar.id = "order_" + object.id;
        homeCardFeatureShoppingCar.appendChild(homeCardShoppingCar);
    });


}

function addDataCompare(homeCardInfo,object){

    var urlObjects = "/compare/isproduct/" + object.id;
    jQuery.ajax({
        type: "GET",
        url: urlObjects,
        dataType:'json',
        async: false
    }).done(function(checkProduct) {
        var checkBoxCompare = document.createElement("div");
        checkBoxCompare.classList.add( "checkbox", "checkBoxCompare");
        homeCardInfo.appendChild(checkBoxCompare);

        var divCompr = document.createElement("div");
        checkBoxCompare.appendChild(divCompr);

        var inputCheckBoxCompare = document.createElement("input");
        inputCheckBoxCompare.type = "checkbox";
        inputCheckBoxCompare.classList.add( "checkBoxField");
        if(checkProduct!= undefined && checkProduct){
            inputCheckBoxCompare.checked = "checked";
        } else{
            inputCheckBoxCompare.checked = "";
        }
        inputCheckBoxCompare.id = "check_compare_" + object.id;

        divCompr.appendChild(inputCheckBoxCompare);

        var labelCompare = document.createElement("label");
        labelCompare.classList.add( "labelCompare");
        labelCompare.textContent = "select";
        divCompr.appendChild(labelCompare);


        var buttonCompare = document.createElement("button");
        buttonCompare.id = "compare_" + object.id;
        buttonCompare.classList.add( "btn","btn-info","buttonCompare");
        buttonCompare.textContent = "compare";
        divCompr.appendChild(buttonCompare);
    });

}

function initPaginationParametrs(){
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "/pagination/parametrs",
        dataType:'json',
        async: false
    }).done(function( parametrs ) {

        var panelSlide = document.getElementById("PanelSlide");

        panelSlide.innerHTML =
            '<div class="panelPrice">' +
            '<b>price </b>' +
            '<b id="minPrice">' + '$' + parametrs.minPrice + '</b>' +
            '<input  id="ex2" type="text" class="span2" data-slider-id="ex2Slider" value="" data-slider-min="' + parametrs.minPrice +'" data-slider-max="' + parametrs.maxPrice + '" data-slider-step="5" data-slider-value="[' + parametrs.minPrice + ',' + parametrs.maxPrice +']" title=""/>' +
            '<b id="maxPrice">' + '$' +parametrs.maxPrice + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<i data-toggle="tooltip" data-placement="tooltip" title="" class="material-icons" data-original-title="Persons">group</i>' +
            '<b id="minPersons">' + parametrs.minPersons + '</b>' +
            '<input style="width: 100px;" id="ex1" data-slider-id="ex1Slider"   type="text" data-slider-min="' + parametrs.minPersons + '" data-slider-max="' + parametrs.maxPersons + '" data-slider-step="1" data-slider-value="' + parametrs.maxPersons + '"/>' +
            '<b id="maxPersons">' + parametrs.maxPersons + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<i data-toggle="tooltip" data-placement="tooltip" title="" class="material-icons" data-original-title="Badrooms">airline_seat_individual_suite</i>' +
            '<b id="minBadrooms">' + parametrs.minBadrooms + '</b>' +
            '<input style="width: 100px;" id="ex3" data-slider-id="ex3Slider" type="text" data-slider-min="' + parametrs.minBadrooms + '" data-slider-max="' + parametrs.maxBadrooms + '" data-slider-step="1" data-slider-value="' + parametrs.maxBadrooms + '"/>' +
            '<b id="maxBadrooms">' + parametrs.maxBadrooms + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<i data-toggle="tooltip" data-placement="tooltip" title="" class="material-icons" data-original-title="Bathrooms">hot_tub</i>' +
            '<b id="minBathrooms">' + parametrs.minBathrooms + '</b>' +
            '<input style="width: 100px;" id="ex4" data-slider-id="ex4Slider" type="text" data-slider-min="' + parametrs.minBathrooms  + '" data-slider-max="' + parametrs.maxBathrooms  + '" data-slider-step="1" data-slider-value="' + parametrs.maxBathrooms  + '"/>' +
            '<b id="maxBathrooms">' + parametrs.maxBathrooms + '</b>' +
            '</div>';

        $("#ex2").slider({
            formatter: function(value) {
                return 'Current value: ' + value;
            }});
        $('#ex1').slider({
            formatter: function(value) {
                return 'Persons: ' + value;
            }
        });
        $('#ex3').slider({
            formatter: function(value) {
                return 'Badrooms: ' + value;
            }
        });
        $('#ex4').slider({
            formatter: function(value) {
                return 'Bathrooms: ' + value;
            }
        });

        $(".slider-handle").mouseup(function()   {
            initPagination('/pagination/countActualObjects');
        });


    });
}