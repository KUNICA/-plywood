/**
 * Created by user on 04.08.2016.
 */


var current = null;
var countP = null
var mselect = 9;

var myMapPaginator = {};
var myMapParamSearch = {};
var ParamSearch = function(index,name,scriptPlywood){
    this.ex1 = 'ex1_' + index;
    this.ex2 = 'ex2_' + index;
    this.ex3 = 'ex3_' + index;
    this.ex4 = 'ex4_' + index;
    this.nameSlide = 'PanelSlide';
    this.PanelSlide = this.nameSlide + name;

    this.parent_element_id = 'mainData' + name;
    this.parent_elementList_id = 'mainDataList' + name;
    //методы
    this.initData = scriptPlywood.initData;
    this.getObjects = scriptPlywood.getObjects;

    this.initParam = scriptPlywood.initParam;
    this.eventParam = scriptPlywood.eventParam;
    
}

var Paginator = function(index,paginationHolderId, pagesTotal, pagesSpan, pageCurrent, baseUrl,count){
    if(!document.getElementById(paginationHolderId) || !pagesTotal || !pagesSpan) return false;
    countP = pagesTotal;
    this.inputData = {
        paginatorHolderId: paginationHolderId,
        pagesTotal: pagesTotal,
        pagesSpan: pagesSpan < pagesTotal ? pagesSpan : pagesTotal,
        pageCurrent: pageCurrent,
        baseUrl: baseUrl ? baseUrl : '/pages/'
        
    };

    this.index = index;
    this.count = count;

    this.paginator1 =  "paginator1_" + this.index;
    this.cellCurrent = 'cellCurrent_' + this.index;
    this.paginator2 =  "paginator2_" + this.index;
    this.idSearchList = "idSearchList_" + this.index;
    this.s_all = 's-all' + this.index;
    this.countPage1 = 'countPage1_' + this.index;
    this.countPage2 = 'countPage2_' + this.index;
    this.s_col1 = 's-col1_' + this.index;
    this.s_col2 = 's-col2_' + this.index;
    this.s_content = 's-content_' + this.index;
    this.scroll_bar1 = 'scroll_bar1_' + this.index;
    this.scroll_bar2 = 'scroll_bar2_' + this.index;

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

function getParent(el, className) {
    var obj = el;
    while (obj.className !== className) {
        obj = obj.parentNode;
    }
    return obj;
}

function getPaginatorId(pId){
    return myMapPaginator[pId];
}

function getPaginatorParrentIndex(element){
    var paginator = getParent(element,"paginator");
    return paginator.id.substr(11, paginator.id.length);
}

function getPaginatorParrent(element){
    var paginatorId = getPaginatorParrentIndex(element)
    return getPaginatorId("paginator2_" + paginatorId);
}

function chekClick(e) {
    e?evt=e:evt=event;
    var element = evt.target?evt.target:evt.srcElement;
    var paginator = getPaginatorParrent(element);

    if(element.id!=null && element.id.indexOf(paginator.cellCurrent)+1){
        current = element.textContent;
        nextPage(element);
    }
    if(element.id!=null && element.id == 'arrowleft' + paginator.paginator1){
        clickArrowLeft(e);
    }
    if(element.id!=null && element.id == 'arrowright' + paginator.paginator1){
        clickArrowRight(e);
    }
    if(element.id!=null && element.id == 'arrowleft' + paginator.paginator2){
        clickArrowLeft2(e);
    }
    if(element.id!=null && element.id == 'arrowright' + paginator.paginator2){
        clickArrowRight2(e);
    }
}

function getPosition(clientX,clientY) {
    var posX = clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
    var posY = clientY + document.body.scrollTop + document.documentElement.scrollTop;
    return {x:posX, y:posY}
}
function clickArrowLeft(e) {
    var paginator = getPaginatorParrent(e);
    var pag1 = document.getElementById(paginator.paginator1);
    var tabl = document.getElementById("tableId" + paginator.paginator1);
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    if(pag1!=null && matchClass(pag1.paginatorBox, 'fullsize')) return;
    scrollthumb.xPos -=  scrollmark.offsetWidth*10;
    scrollthumb.style.left = scrollthumb.xPos + "px";
    mselect = document.getElementById(paginator.idSearchList);
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
    var trPages = tabl.getElementsByTagName('ul')[0];
    var tdsPages = trPages.getElementsByTagName('li');


    for(var i=0; i<tdsPages.length; i++){
        var cellCurrentValue = cellFirstValue + i;
        if(cellCurrentValue == current){
            html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen;    cursor:pointer;'>" + cellCurrentValue  + "</span>";
        } else {
            html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
        }
        tdsPages[i].innerHTML = html;
    }
   // this.srollDisabled();
}
function clickArrowRight(e) {
    var paginator = getPaginatorParrent(e);
    var pag1 = document.getElementById(paginator.paginator1);
    var tabl = document.getElementById("tableId" + paginator.paginator1);
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    var trPages = tabl.getElementsByTagName('ul')[0];
    var tdsPages = trPages.getElementsByTagName('li');

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
                html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
            } else {
                html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
            }
            tdsPages[i].innerHTML = html;
        }

    }

    //this.srollDisabled();
}
function clickArrowLeft2(e) {
    var paginator = getPaginatorParrent(e);
    var pag1 = document.getElementById(paginator.paginator2);
    var tabl = document.getElementById("tableId" + paginator.paginator2);
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
    var trPages = tabl.getElementsByTagName('ul')[0];
    var tdsPages = trPages.getElementsByTagName('li');


    for(var i=0; i<tdsPages.length; i++){
        var cellCurrentValue = cellFirstValue + i;
        if(cellCurrentValue == current){
            html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
        } else {
            html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
        }
        tdsPages[i].innerHTML = html;
    }
    this.srollDisabled();
}
function clickArrowRight2(e) {
    var paginator = getPaginatorParrent(e);
    var pag1 = document.getElementById(paginator.paginator2);
    var tabl = document.getElementById("tableId" + paginator.paginator2);
    var scrollbar = getElementsByClassName(tabl, 'div', 'scroll_bar')[0];
    var scrollthumb = getElementsByClassName(tabl, 'div', 'scroll_thumb')[0];
    var scrollmark = getElementsByClassName(tabl, 'div', 'current_page_mark')[0];
    var trPages = tabl.getElementsByTagName('ul')[0];
    var tdsPages = trPages.getElementsByTagName('li');

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
                html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: yellowgreen; font-weight: bold;   cursor:pointer;'>" + cellCurrentValue  + "</span>";
            } else {
                html = "<span id='" + paginator.cellCurrent + pag1.id + cellCurrentValue + "'" + "style='color: black; cursor:pointer;'>" + cellCurrentValue  + "</span>";
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

    this.html.table = this.html.holder.getElementsByTagName('div')[0];

    var trPages = this.html.table.getElementsByTagName('ul')[0];
    this.html.tdsPages = trPages.getElementsByTagName('li');

    this.html.scrollBar = getElementsByClassName(this.html.table, 'div', 'scroll_bar')[0];
    this.html.scrollThumb = getElementsByClassName(this.html.table, 'div', 'scroll_thumb')[0];
    this.html.pageCurrentMark = getElementsByClassName(this.html.table, 'div', 'current_page_mark')[0];
    this.srollDisabled();
}

Paginator.prototype.srollDisabled = function(){
    if(this.inputData.pagesTotal<=this.inputData.pagesSpan){
        var scroll1 = document.getElementById(this.scroll_bar1);
        if(scroll1 != undefined){scroll1.style.display = 'none';}
        var scroll2 = document.getElementById(this.scroll_bar2);
        if(scroll2 != undefined){scroll2.style.display = 'none';}
    } 
}

/*
 Make html for pages (table)
 */
Paginator.prototype.makePagesTableHtml = function(){
    var tdWidth = (100 / this.inputData.pagesSpan) + '%';
    var htmlPols =
        '<div class="" id = "tableId' + this.inputData.paginatorHolderId +  '">' + '<ul class="pagination ">'  ;

    for (var i=1; i<=this.inputData.pagesSpan; i++){
        htmlPols += '<li class="title"></li>';
    }

    var html = null;
    if(this.inputData.paginatorHolderId == this.paginator1){

        htmlPols += ''  +
            '</ul>' +
            '<ul>' +
            '<li class="current" colspan="' + this.inputData.pagesSpan + '">' +
            '<div class="scroll_bar" id = "' + this.scroll_bar1 + '">' +
            '<div class="scroll_trough"></div>' +
            '<div style="display:block;"  class="scroll_thumb">' +
            '<div class="slider-handle round"></div>' +
            '</div>' +
            '<div class="current_page_mark"></div>' +
            '</div>' +
            '</li>' +
            '</ul>' +
            '</div>';

        html =  '' +  '<div class = "s-all" id="' + this.s_all + '"' + '>' +
            '<div id=' + this.countPage1 + '></div>' +
            '<div style="clear:both;">'   +
            '<div class = "s-content" id="' + this.s_content + '">' + htmlPols + '</div>' +
           // '<div class = "s-col1" id="' + this.s_col1 + '"><ul class="pagination"> <li class="current title"><a class="arrow_left" id="arrowleft' + this.inputData.paginatorHolderId + '">Back</a></li></ul></div>' +
          //  '<div class = "s-col2" id="' + this.s_col2 + '"><ul class="pagination"> <li class="current title"><a class="arrow_right"  id="arrowright' + this.inputData.paginatorHolderId + '">Next</a></li></ul></div>' +
            '<div style="clear:both;">' +
            //  '<div id="footer">дно</div>'+
            '</div> ';
    }
    else{
        htmlPols += ''  +
            '</ul>' +
            '<ul>' +
            '<li colspan="' + this.inputData.pagesSpan + '">' +
            '<div class="scroll_bar" id = "' + this.scroll_bar2 + '">' +
            '<div class="scroll_trough"></div>' +
            '<div style="display:block;"  class="scroll_thumb">' +
            '<div class="slider-handle round" ></div>' +
            '</div>' +
            '<div class="current_page_mark"></div>' +
            '</div>' +
            '</li>' +
            '</ul>' +
            '</div>';

        html =  '' +  '<div class = "s-all" id="' + this.s_all + '">' +
            // '<div id="s-header">шапка</div>' +
            '<div style="clear:both;">'   +
            '<div class = "s-content" id="' + this.s_content + '">' + htmlPols + '</div>' +
           // '<div class = "s-col1" id="' + this.s_col1 + '"><ul class="pagination"> <li class="current title"><a  class="arrow_left" id="arrowleft' + this.inputData.paginatorHolderId + '">Back</a></li></ul></div>' +
           // '<div class = "s-col2" id="' + this.s_col2 + '"><ul class="pagination"> <li class="current title"><a class="arrow_right"  id="arrowright' + this.inputData.paginatorHolderId + '">Next</a></li></ul></div>' +
            '<div style="clear:both;">' +
            '<div style="display: none" id="' + this.countPage2 + '"></div>'+
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
            html = "<a id='" + this.cellCurrent + this.inputData.paginatorHolderId + cellCurrentValue + "'" + "style='cursor:pointer;'>" + cellCurrentValue  + "</a>";
        } else {
            html = "<a id='" + this.cellCurrent + this.inputData.paginatorHolderId + cellCurrentValue + "'" + "style=' cursor:pointer;'>" + cellCurrentValue  + "</a>";
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

function initPaginationPage(count,index){

    
    var params = myMapParamSearch[index];
    var p1 = document.getElementById("paginator1_" + index);
    var p2 = document.getElementById("paginator2_" + index);
    if(p1!=undefined)destroyChildren(p1);
    if(p2!=undefined)destroyChildren(p2);
    //var mselect = 10;
    var coutnPage;
    if (mselect != null && mselect != '' && mselect != '0') {
        coutnPage = count / mselect;
    }
    if (Math.ceil(coutnPage) > 0) {
        myMapPaginator["paginator1_" + index] = new Paginator(index,"paginator1_" + index, Math.ceil(coutnPage), 5, 1, "",count);
        myMapPaginator["paginator2_" + index] = new Paginator(index,"paginator2_" + index, Math.ceil(coutnPage), 5, 1, "",count);
        var pag1 = myMapPaginator["paginator1_" + index];
        var pag2 = myMapPaginator["paginator2_" + index];

        var pCount1 = document.getElementById(pag1.countPage1);
        var pCount2 = document.getElementById(pag1.countPage2);
        // if (pCount1 != null) {
        //     pCount1.textContent = "pages:" + Math.ceil(coutnPage);
        // }
        if (pCount2 != null) {
            pCount2.textContent = "pages:" + Math.ceil(coutnPage);
        }
    }
    //pag1.srollDisabled();
    myMapPaginator["paginator2_" + index].srollDisabled();
var element = document.getElementById(myMapPaginator["paginator2_" + index].cellCurrent + myMapPaginator["paginator2_" + index].paginator2 + 1);
if (element != undefined) {
    nextPage(element);
}else{
    var parentElement = document.getElementById(params.parent_element_id);
    parentElement.textContent = '';
     parentElementList = document.getElementById(params.parent_elementList_id);
    parentElementList.textContent = '';
}
}

function initPagination(index) {
    
    var params = myMapParamSearch[index];
    var paginations = getPaginatorId("paginator2_" + index);

    
    var elementPrice = document.getElementById(params.ex2);
    var priceList = elementPrice.value.split(",");
    var minPrice = priceList[0];
    var maxPrice = priceList[1];

    var elementLength = document.getElementById(params.ex1);
    var lengthList = elementLength.value.split(",");
    var minLength = lengthList[0];
    var maxLength = lengthList[1];

    var elementWidth = document.getElementById(params.ex3);
    var widthList = elementWidth.value.split(",");
    var minWidth  = widthList[0];
    var maxWidth  = widthList[1];

    var elementDepth = document.getElementById(params.ex4);
    var depthList = elementDepth.value.split(",");
    var minDepth  = depthList[0];
    var maxDepth  = depthList[1];

    params.initData(index,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth);
}

    function nextPage(cellCurrentValue) {
        var cellFirstValue = 0;
        var paginator = getPaginatorParrent(cellCurrentValue);
        var index =getPaginatorParrentIndex(cellCurrentValue);
        var params = myMapParamSearch[index];

        var pag = document.getElementById(paginator.paginator1);
        for (var i = 1; i <= countP; i++) {
            var el1 = document.getElementById(paginator.cellCurrent + paginator.paginator1 + i);
            if (el1 != null && el1.style != null) {
                el1.style.background =  '#f3edeb';
                el1.style.color = 'black';
            }
        }
        var pag2 = document.getElementById(paginator.paginator2);
        for (var j = 1; j <= countP; j++) {
            var el = document.getElementById(paginator.cellCurrent + paginator.paginator2 + j);
            if (el != null && el.style != null) {
                el.style.background = '#f3edeb';
                el.style.color = 'black';
            }
        }
        if(cellCurrentValue.id != params.parent_element_id && cellCurrentValue.id != params.parent_elementList_id  ) {
            var id1 = cellCurrentValue.id.substr(25, cellCurrentValue.id.length - 1);
            var mel1 = document.getElementById(paginator.cellCurrent + paginator.paginator1 + id1);
            var mel2 = document.getElementById(paginator.cellCurrent + paginator.paginator2 + id1);
            if (mel1 != null) {
                mel1.style.color = 'yellowgreen';
            }
            if (mel2 != null) {
                mel2.style.color = 'yellowgreen';
            }
        }


        // методы добавления, очистки и получения данных
        
        var dataList = null;
        var start = id1 * mselect - mselect;
        var end = mselect;
        var elementPrice = document.getElementById(params.ex2);
        var priceList = elementPrice.value.split(",");
        var minPrice = priceList[0];
        var maxPrice = priceList[1];

        var elementLength = document.getElementById(params.ex1);
        var lengthList = elementLength.value.split(",");
        var minLength = lengthList[0];
        var maxLength = lengthList[1];

        var elementWidth = document.getElementById(params.ex3);
        var widthList = elementWidth.value.split(",");
        var minWidth  = widthList[0];
        var maxWidth  = widthList[1];

        var elementDepth = document.getElementById(params.ex4);
        var depthList = elementDepth.value.split(",");
        var minDepth  = depthList[0];
        var maxDepth  = depthList[1];

        var parentElement = document.getElementById(params.parent_element_id);
        var parentListElement = document.getElementById(params.parent_elementList_id);

        params.getObjects(parentElement,parentListElement,$(".homeCard"),start,end,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth,paginator.count);

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

function initPaginationParametrs(index,url){
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: url,
        dataType:'json',
        async: false
    }).done(function( parametrs ) {
        
        var params = myMapParamSearch[index];

        var panelSlide = document.getElementById(params.PanelSlide);

        panelSlide.innerHTML = 
                                            '<div class="price-filter">' +
                                                  '<h2>Price</h2>' +
                                                  '<hr>' +
                                                  '<input  id="' + params.ex2 + '" type="text" class="span2 exSlider" data-slider-id="' + params.ex2 + 'Slider" value="' + parametrs.minPrice + ',' + parametrs.maxPrice +'" data-slider-min="' + parametrs.minPrice +'" data-slider-max="' + parametrs.maxPrice + '" data-slider-step="5" data-slider-value="[' + parametrs.minPrice + ',' + parametrs.maxPrice +']" title=""/>' +
                                            '</div>' +
                                            '<span class="min-max">' +
                                                    'Price: $' + parametrs.minPrice +  '- $' + parametrs.maxPrice +
                                            '</span>' +


                                            '<div class="price-filter">' +
                                            '<hr>' +
                                            '<h2>Length</h2>' +
                                            '<hr>' +
                                            '<input  id="' + params.ex1 + '" type="text" class="span2 exSlider" data-slider-id="' + params.ex1 + 'Slider" value="' + parametrs.minLength + ',' + parametrs.maxLength +'" data-slider-min="' + parametrs.minLength +'" data-slider-max="' + parametrs.maxLength + '" data-slider-step="5" data-slider-value="[' + parametrs.minLength + ',' + parametrs.maxLength +']" title=""/>' +
                                            '</div>' +
                                            '<span class="min-max">' +
                                            'Length: ' + parametrs.minLength +  '- ' + parametrs.maxLength +
                                            '</span>' +

                                            '<div class="price-filter">' +
                                            '<hr>' +
                                            '<h2>Width</h2>' +
                                            '<hr>' +
                                            '<input  id="' + params.ex3 + '" type="text" class="span2 exSlider" data-slider-id="' + params.ex3 + 'Slider" value="' + parametrs.minWidth + ',' + parametrs.maxWidth +'" data-slider-min="' + parametrs.minWidth +'" data-slider-max="' + parametrs.maxWidth + '" data-slider-step="5" data-slider-value="[' + parametrs.minWidth + ',' + parametrs.maxWidth +']" title=""/>' +
                                            '</div>' +
                                            '<span class="min-max">' +
                                            'Width: ' + parametrs.minWidth +  '- ' + parametrs.maxWidth +
                                            '</span>' +

                                            '<div class="price-filter">' +
                                            '<hr>' +
                                            '<h2>Depth</h2>' +
                                            '<hr>' +
                                            '<input  id="' + params.ex4 + '" type="text" class="span2 exSlider" data-slider-id="' + params.ex4 + 'Slider" value="' + parametrs.minDepth + ',' + parametrs.maxDepth +'" data-slider-min="' + parametrs.minDepth +'" data-slider-max="' + parametrs.maxDepth + '" data-slider-step="5" data-slider-value="[' + parametrs.minDepth + ',' + parametrs.maxDepth +']" title=""/>' +
                                            '</div>' +
                                            '<span class="min-max">' +
                                            'Depth: ' + parametrs.minDepth +  '- ' + parametrs.maxDepth +
                                            '</span>';

        /*
        panelSlide.innerHTML =
            '<div class="panelPrice">' +
            '<b>Price </b>' +
            '<b id="minPrice">' + '$' + parametrs.minPrice + '</b>' +
            '<input  id="' + params.ex2 + '" type="text" class="span2 exSlider" data-slider-id="' + params.ex2 + 'Slider" value="" data-slider-min="' + parametrs.minPrice +'" data-slider-max="' + parametrs.maxPrice + '" data-slider-step="5" data-slider-value="[' + parametrs.minPrice + ',' + parametrs.maxPrice +']" title=""/>' +
            '<b id="maxPrice">' + '$' +parametrs.maxPrice + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<b>Length </b>' +
            '<b id="minLength">' + parametrs.minLength + '</b>' +
            '<input style="width: 100px;" id="' + params.ex1 + '" class="span2 exSlider" data-slider-id="' + params.ex1 + 'Slider" data-slider-min="' + parametrs.minLength + '" data-slider-max="' + parametrs.maxLength + '" data-slider-step="1" data-slider-value="[' + parametrs.minLength + ',' + parametrs.maxLength + ']" title=""/>' +
            '<b id="maxLength">' + parametrs.maxLength + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<b>Width </b>' +
            '<b id="minWidth">' + parametrs.minWidth + '</b>' +
            '<input style="width: 100px;" id="' + params.ex3 + '" class="span2 exSlider" data-slider-id="' + params.ex3 + 'Slider" type="text" data-slider-min="' + parametrs.minWidth + '" data-slider-max="' + parametrs.maxWidth + '" data-slider-step="1" data-slider-value="[' + parametrs.minWidth + ',' + parametrs.maxWidth + ']" title=""/>' +
            '<b id="maxWidth">' + parametrs.maxWidth + '</b>' +
            '</div>' +
            '<div class="panelElement">' +
            '<b>Depth </b>' +
            '<b id="minDepth">' + parametrs.minDepth + '</b>' +
            '<input style="width: 100px;" id="' + params.ex4 + '" class="span2 exSlider" data-slider-id="ex4' + params.ex4 + 'Slider" type="text" data-slider-min="' + parametrs.minDepth  + '" data-slider-max="' + parametrs.maxDepth  + '" data-slider-step="1" data-slider-value="[' + parametrs.minDepth + ',' + parametrs.maxDepth  + ']" title=""/>' +
            '<b id="maxDepth">' + parametrs.maxDepth + '</b>' +
            '</div>';
            */

        panelSlide.innerHTML = params.initParam(panelSlide.innerHTML,parametrs);

        $("#" + params.ex2).slider({
            formatter: function(value) {
                return 'Current value: ' + value;
            }});

        $('#' + params.ex1).slider({
            formatter: function(value) {
                return 'Current value: ' + value;
            }
        });
        $('#' + params.ex3).slider({
            formatter: function(value) {
                return 'Current value: ' + value;
            }
        });
        $('#' + params.ex4).slider({
            formatter: function(value) {
                return 'Current value: ' + value;
            }
        });


        params.eventParam();

        $(".slider-handle").mouseup(function()   {
            initPagination(index);
        });

    });
}