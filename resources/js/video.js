/**
 * Created by user on 05.08.2016.
 */
$(document).on('click', '.Video', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 6 && CSE.id.substr(0, 6) == 'video_') {
        productId = CSE.id.substr(6, CSE.id.length);
    }

    var mainRow = document.getElementById("video12");
    $('.modal-title').textContent = 'videos room';
    mainRow.textContent = '';

    var urlCount = '/video/videos/' + productId;

    if(productId!=null){
        jQuery.ajax({
            type: "GET",
            url: urlCount,
            dataType:'json',
            async: false
        }).done(function( objects ) {
            $('.modal-title').textContent = 'photos room';

            var iframe = document.createElement("iframe");
            if(objects.length >0){
                iframe.src = "http://www.youtube.com/embed/" + objects[0].link + "?rel=0";
            }
            iframe.allowfullscreen = "";
            iframe.frameborder = "0";
            mainRow.appendChild(iframe);

            var div = document.createElement("div");
            mainRow.appendChild(div);

            for (var i = 0; i < objects.length; i++) {
                var img1 = document.createElement("img");
                img1.src = "http://img.youtube.com/vi/" + objects[i].link + "/1.jpg";
                img1.tabindex = "2";
                div.appendChild(img1);
            }

        });

        var IMG = document.querySelectorAll('#video12 img'),
            IFRAME = document.querySelector('#video12 iframe');
        for (var i = 0; i < IMG.length; i++) {
            IMG[i].onclick = function() {
                var idIMG = this.src.replace(/http...img.youtube.com.vi.([\s\S]*?).1.jpg/g, '$1');
                IFRAME.src = 'http://www.youtube.com/embed/' + idIMG + '?rel=0&autoplay=1';
                if(this.dataset.end) IFRAME.src = IFRAME.src.replace(/([\s\S]*)/g, '$1&end=' + this.dataset.end);
                if(this.dataset.start) IFRAME.src = IFRAME.src.replace(/([\s\S]*)/g, '$1&start=' + this.dataset.start);
                this.style.backgroundColor='#555';
            }
        }

        $('#video').modal();
    }
});

var ScriptVideo = function(){
}

ScriptVideo.prototype.initPagination = function(index) {

    var params = myMapParamSearch[index];
    var paginations = getPaginatorId("paginator2_" + index);
    var categoryId = document.getElementById("categoryId");
    params.initData(index,categoryId.value);
}

ScriptVideo.prototype.initData = function(index,section) {
    var urlCount = '/video/count/' + section;
    var categoryId = document.getElementById("categoryId");

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlCount,
        dataType:'json',
        async: false
    }).done(function( count ) {
        initPaginationPage(count,index);
    });
}

ScriptVideo.prototype.initDataListParams = function(start,end,paginator, params){
    var parentElement = document.getElementById('video_main');
    var  section = document.getElementById("categoryId").value;
    params.getObjects(parentElement,start,end,paginator.count,section);
}

ScriptVideo.prototype.getObjects = function(element,start,end,count,section) {

    var pageSearch = document.getElementById("page-search");
    
    var  data={
        "start": start,
        "end": end,
        "pageSearch" : pageSearch.value
    };

    var urlObjects = '/video/objects/' + section;


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
        // стираем данные
        element.innerHTML = "";
        ScriptVideo.prototype.addData(objects,element,start,end,count);

    });
}

ScriptVideo.prototype.addData = function(objects,element,start,end,count) {
    for(var i=0;i<objects.length;i++) {
        var object = objects[i];

        var date = new Date(object.data);
        var newdate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();

        element.innerHTML += '<div class="post format-video">' +
            '<div class="date-wrapper">' +
            '<div class="date">' +
            '<span class="day">' + newdate + '</span>' +
            '</div>' +
            '</div>' +
            '<div class="format-wrapper">' +
            '<a href="#"><i class="fa fa-film"></i></a>' +
            '</div>' +
            '<div class="post-content">' +
            '<div class="post-media">' +
            '<p style="position: relative; padding-bottom: 56.25%; padding-top: 25px; height: 0;">' +
            '<iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;" width="100%" src="' + object.link + '" frameborder="0" allowfullscreen=""></iframe>' +
            '</p>' +
            '</div>' +
            '<h2 class="post-title">' + object.headline + '</h2>' +
            '</div>' +
            '</div>';
    }
}

$(document).ready(function(){
    var urlObjects = '/video/categories'
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlObjects,
        dataType:'json',
        async: false
    }).done(function( categories ) {
        var elmC = document.getElementById("categories");
        elmC.innerHTML = '<li style="cursor:pointer;"><a  id = "category" style="cursor:pointer;">All</a></li>';
        for(var i=0;i<categories.length;i++){
            var category = categories[i];
            elmC.innerHTML += '<li style="cursor:pointer;"><a style="cursor:pointer;" id = "category">' + category  + '</a></li>';
        }
    });

});

$(document).on('click', '#category', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var category = '';
    if (CSE != null) {
        category  = CSE.textContent;
    }

    var categoryId = document.getElementById("categoryId");
    categoryId.value = category;
    var urlCount = '/video/count/' + category;

    var index = 9;
    var scriptRuller = new ScriptVideo();
    myMapParamSearch[9] = new ParamSearch(9,
        "Video",scriptRuller,scriptRuller);
    scriptRuller.initPagination(9);

});

$(document).on('click', '#searchVidio', function(e) {
    myMapParamSearch[9].initPagination(9);
});




