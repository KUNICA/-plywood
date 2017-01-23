/**
 * Created by user on 27.08.2016.
 */

var ScriptParticleboard = function(){
    this.paramsUrl = '/pagination/parametrsParticleboards';
};

ScriptParticleboard.prototype.initParam = function(innerHtml,parametrs){


    innerHtml +=
        '<hr>' +
        '<div class="category-filter">' +
        '<h2>Sanded</h2>' +
        '<hr>' +
        '<ul>' +
        '<li><input checked="checked" class="le-checkbox" type="radio" id ="radioAll" name="optradio" ><i class="fake-box"></i> <label>All</label> <span class="pull-right">(sanded all)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioUnsanded" name="optradio"><i class="fake-box"></i> <label>no</label> <span class="pull-right">(unsanded)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioSanded" name="optradio" ><i class="fake-box"></i> <label>yes</label> <span class="pull-right">(sanded)</span></li>' +
        '</ul>' +
        '</div>';
        
        /*
        '<hr>' +
        '<div class="price-filter">' +
                  '<hr>' +
                  '<h2>Depth</h2>' +
                  '<hr>' +
                  '<input  id="' + 'laminatedParticleboard' + '" type="text" class="span2 exSlider" data-slider-id="' + 'laminatedParticleboard' + 'Slider" value="' + parametrs.minLaminated + ',' + parametrs.maxLaminated +'" data-slider-min="' + parametrs.minLaminated +'" data-slider-max="' + parametrs.maxLaminated + '" data-slider-step="5" data-slider-value="[' + parametrs.minLaminated + ',' + parametrs.maxLaminated +']" title=""/>' +
                  '</div>' +
                  '<span class="min-max">' +
                  'Laminated: ' + parametrs.minLaminated +  '- ' + parametrs.maxLaminated +
        '</span>';
        */

    /*
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
        */

    return innerHtml;
};


ScriptParticleboard.prototype.eventParam = function(){
 /*
    $('input:radio[name=optradio]').change(function () {
        myMapParamSearch[1].initPagination(1);
    });
 */
};



ScriptParticleboard.prototype.initData = function(index,minPrice,maxPrice,length,lengthAll,width,widthAll,depth,depthAll){
    
/*
    var laminatedParticleboard = document.getElementById('laminatedParticleboard');
    var laminatedParticleboardList = laminatedParticleboard.value.split(",");
    var minLaminated = laminatedParticleboardList[0];
    var maxLaminated = laminatedParticleboardList[1];
*/

    var sanded = null;
    if(document.getElementById('radioSanded').checked){
        sanded = true;
    } else if(document.getElementById('radioUnsanded').checked){
        sanded = false;
    }  else if(document.getElementById('radioAll').checked){
        sanded = null;
    }

        var urlCount = '/pagination/countActualParticleboard';
    var  data={
        "start": 0,
        "end": 0,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "length": length,
        "lengthAll": lengthAll,
        "width": width,
        "widthAll": widthAll,
        "depth": depth,
        "depthAll": depthAll,
        "sanded":sanded
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

ScriptParticleboard.prototype.getObjects = function(parentElement,parentListElement,element,start,end,minPrice,maxPrice,length,lengthAll,width,widthAll,depth,depthAll,count) {


    var sanded = null;
    if(document.getElementById('radioSanded').checked){
        sanded = true;
    } else if(document.getElementById('radioUnsanded').checked){
        sanded = false;
    }  else if(document.getElementById('radioAll').checked){
        sanded = null;
    }

    var  data={
        "start": start,
        "end": end,
        "minPrice": minPrice,
        "maxPrice": maxPrice,
        "length": length,
        "lengthAll": lengthAll,
        "width": width,
        "widthAll": widthAll,
        "depth": depth,
        "depthAll": depthAll,
        "sanded":sanded
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
        parentListElement.textContent = "";
        ScriptParticleboard.prototype.addData(objects,parentElement,parentListElement,start,end,count);

    });
}

ScriptParticleboard.prototype.addData = function(objects,parentElement,parentListElement,start,end,count) {
    for (var i = 0; i < objects.length; i++) {
        
        var homeCardInfo = document.createElement("div");
        homeCardInfo.classList.add("catalog-item-table-view");
        parentElement.appendChild(homeCardInfo);

        var homeCardInfoList = document.createElement("div");
        parentListElement.appendChild(homeCardInfoList);

        addDataMainPage(homeCardInfo,homeCardInfoList,objects[i]);

       ScriptParticleboard.prototype.addDataCompare(homeCardInfo,objects[i]);
        ScriptParticleboard.prototype.addDataShoping(homeCardInfo,objects[i]);

    }
    var resultCounter = document.getElementById("result-counter" + 1);
    end = (start+end >= count)? count :start+end;
    start = start + 1;
    resultCounter.innerHTML = 'Showing <span>' + start + '-' + end + '</span> of <span>' + count + '</span> results';

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
