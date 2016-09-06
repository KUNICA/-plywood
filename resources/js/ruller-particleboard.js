/**
 * Created by user on 27.08.2016.
 */

var ScriptParticleboard = function(){
    this.paramsUrl = '/pagination/parametrsParticleboards';
}

ScriptParticleboard.prototype.initParam = function(innerHtml,parametrs){
    innerHtml +=
        '<div class="panelElement">' +
        '<b>Laminated </b>' +
        '<b id="minLaminated">'  + parametrs.minLaminated + '</b>' +
        '<input  id="' + 'laminatedParticleboard' + '" type="text" class="span2 exSlider" data-slider-id="' + 'laminatedParticleboard' + 'Slider" value="" data-slider-min="' + parametrs.minLaminated +'" data-slider-max="' + parametrs.maxLaminated + '" data-slider-step="5" data-slider-value="[' + parametrs.minLaminated + ',' + parametrs.maxLaminated +']" title=""/>' +
        '<b id="maxLaminated">'  + parametrs.maxLaminated + '</b>' +
        '</div>' +

        '<div class="panelElement">' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioTheCoatingParticleboard"  name="coatingParticleboard"><b>is the coating</b></label>' +
        '</div>' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioNoCoatingParticleboard" name="coatingParticleboard"><b>no coating<b/></label>' +
        '</div>' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioAllCoatingParticleboard" name="coatingParticleboard" checked><b>all<b/></label>' +
        '</div>' +
        '</div>';

    return innerHtml;
}

ScriptParticleboard.prototype.eventParam = function(){
    $(".radio").on('change',function()   {
        initPagination(1);
    });

    $('#laminatedParticleboard').slider({
        formatter: function(value) {
            return 'Current value: ' + value;
        }
    });
}

ScriptParticleboard.prototype.initData = function(index,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth){

    var coating = null;
    if(document.getElementById('radioTheCoatingParticleboard').checked == true){
        coating = true;
    } else if(document.getElementById('radioNoCoatingParticleboard').checked){
        coating = false;
    }  else if(document.getElementById('radioAllCoatingParticleboard').checked){
        coating = null;
    }

    var laminatedParticleboard = document.getElementById('laminatedParticleboard');
    var laminatedParticleboardList = laminatedParticleboard.value.split(",");
    var minLaminated = laminatedParticleboardList[0];
    var maxLaminated = laminatedParticleboardList[1];


        var urlCount = '/pagination/countActualParticleboard';
    var  data={
        "start": 0,
        "end": 0,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "minLength": minLength,
        "maxLength": maxLength,
        "minWidth":minWidth,
        "maxWidth":maxWidth,
        "minDepth":minDepth,
        "maxDepth":maxDepth,
        "coating":coating,
        "minLaminated":minLaminated,
        "maxLaminated":maxLaminated

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
        initPaginationPage(count,index);
    });
}

ScriptParticleboard.prototype.getObjects = function(parentElement,element,start,end,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth) {
    var  data={
        "start": start,
        "end": end,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "minLength": minLength,
        "maxLength": maxLength,
        "minWidth":minWidth,
        "maxWidth":maxWidth,
        "minDepth":minDepth,
        "maxDepth":maxDepth
    };

    var urlObjects = '/pagination/particleboards';
    
    
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
        parentElement.textContent = "";
        ScriptParticleboard.prototype.addData(objects,parentElement);

    });
}

ScriptParticleboard.prototype.addData = function(objects,parentElement) {
    for (var i = 0; i < objects.length; i++) {
        
        var homeCardInfo = document.createElement("div");
        homeCardInfo.classList.add("catalog-item-table-view");
        parentElement.appendChild(homeCardInfo);

        addDataMainPage(homeCardInfo,objects[i]);
        ScriptParticleboard.prototype.addDataCompare(homeCardInfo,objects[i]);
        ScriptParticleboard.prototype.addDataShoping(homeCardInfo,objects[i]);

    }

}

ScriptParticleboard.prototype.addDataShoping = function(parentElement,object){

    var urlShoping = '/shoping/cart/';
    
    urlObjects = urlShoping + object.id;

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
        addDataShopingPage(parentElement,object,product)
    });


}

ScriptParticleboard.prototype.addDataCompare = function(homeCardInfo,object){
    var urlCompare = '/compare/isProduct/';
    var urlObjects = urlCompare + object.id;
    jQuery.ajax({
        type: "GET",
        url: urlObjects,
        dataType:'json',
        async: false
    }).done(function(checkProduct) {
        addDataComparePage(homeCardInfo,object,checkProduct);
    });

}
