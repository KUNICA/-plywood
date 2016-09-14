/**
 * Created by user on 26.08.2016.
 */

var ScriptPlywood = function(){
    this.paramsUrl = '/pagination/parametrsPlywoods';
}


ScriptPlywood.prototype.initParam = function(innerHtml,parametrs){


    innerHtml +=  '<div class="price-filter">' +
        '<hr>' +
        '<h2>Color</h2>' +
        '<hr>' +
        '<input  id="' + 'coatingPlywood' + '" type="text" class="span2 exSlider" data-slider-id="' + 'coatingPlywoodSlider' + 'Slider" value="' + 0 + ',' + 225 +'" data-slider-min="' + 0 +'" data-slider-max="' + 225 + '" data-slider-step="1" data-slider-value="[' + 0 + ',' + 225 +']" title=""/>' +
        '</div>' +
        '<span class="min-max">' +
        'Color: ' + 0 +  '- ' + 225 +
        '</span>' +

        '<hr>' +
        '<div class="category-filter">' +
        '<h2>Coating</h2>' +
        '<hr>' +
        '<ul>' +
        '<li><input checked="checked" class="le-checkbox" type="radio" id ="radioAllColor" name="colorPlywood"><i class="fake-box"></i> <label>All</label> <span class="pull-right">(choose all)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioNoColor" name="colorPlywood"><i class="fake-box"></i> <label>no</label> <span class="pull-right">(no color)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioTheColor" name="colorPlywood" ><i class="fake-box"></i> <label>yes</label> <span class="pull-right">(is the color)</span></li>' +
        '</ul>' +
        '</div>'  +

        '<hr>' +
        '<div class="category-filter">' +
        '<h2>Sanded</h2>' +
        '<hr>' +
        '<ul>' +
        '<li><input checked="checked" class="le-checkbox" type="radio" id ="radioAll" name="optradio"><i class="fake-box"></i> <label>All</label> <span class="pull-right">(sanded all)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioUnsanded" name="optradio"><i class="fake-box"></i> <label>no</label> <span class="pull-right">(unsanded)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioSanded" name="optradio" ><i class="fake-box"></i> <label>yes</label> <span class="pull-right">(sanded)</span></li>' +
        '</ul>' +
        '</div>' +

        '<hr>' +
        '<div class="category-filter">' +
        '<h2>water resistance</h2>' +
        '<hr>' +
        '<ul>' +
        '<li><input checked="checked" class="le-checkbox" type="radio" id ="radioAllResistance" name="radioRes"><i class="fake-box"></i> <label>All</label> <span class="pull-right">(all)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioUnresistance" name="radioRes"><i class="fake-box"></i> <label>no</label> <span class="pull-right">(no water resistance)</span></li>' +
        '<li><input class="le-checkbox" type="radio" id ="radioResistance" name="radioRes" ><i class="fake-box"></i> <label>yes</label> <span class="pull-right">(water resistance)</span></li>' +
        '</ul>' +
        '</div>' ;


    /*
    innerHtml +=
        '<div class="panelElement">' +
        '<b>Color </b>' +
        '<b id="minCoatingPlywood">' + 0 + '</b>' +
        '<input style="width: 100px;" id="coatingPlywood" class="span2 exSlider" data-slider-id="coatingPlywoodSlider" type="text" data-slider-min="' + 0  + '" data-slider-max="' + 225  + '" data-slider-step="1" data-slider-value="[' + 0 + ',' + 225  + ']" title=""/>' +
        '<b id="maxCoatingPlywood">' + 225 + '</b>' +
        '</div>' +

        '<div class="panelElement">' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioTheColor"  name="colorPlywood"><b>is the coating</b></label>' +
        '</div>' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioNoColor" name="colorPlywood"><b>no coating<b/></label>' +
        '</div>' +
        '<div class="radio">' +
        '<label><input type="radio" id ="radioAllColor" name="colorPlywood" checked><b>all<b/></label>' +
        '</div>' +
        '</div>' +
  

        '<div class="panelElement">' + 
        '<div class="radio">' + 
        '<label><input type="radio" id ="radioSanded"  name="optradio"><b>sanded</b></label>' +
        '</div>' + 
        '<div class="radio">' + 
        '<label><input type="radio" id ="radioUnsanded" name="optradio"><b>unsanded<b/></label>' +
        '</div>' + 
        '<div class="radio">' +
        '<label><input type="radio" id ="radioAll" name="optradio" checked><b>all<b/></label>' +
        '</div>' + 
        '</div>' +

    '<div class="panelElement">' +
    '<div class="radio">' +
    '<label><input type="radio" id ="radioResistance"  name="radioRes"><b>water resistance</b></label>' +
    '</div>' +
    '<div class="radio">' +
    '<label><input type="radio" id ="radioUnresistance" name="radioRes"><b>no water resistance<b/></label>' +
    '</div>' +
    '<div class="radio">' +
    '<label><input type="radio" id ="radioAllResistance" name="radioRes" checked><b>all<b/></label>' +
    '</div>' +
    '</div>';
    */


    return innerHtml;
}

ScriptPlywood.prototype.eventParam =  function(){
    $(".radio").on('change',function()   {
        initPagination(2);
    });
    $('#coatingPlywood').slider({
        formatter: function(value) {
            return 'Current value: ' + value;
        }
    });
}


ScriptPlywood.prototype.initData = function(index,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth){

    var sanded = null;
    if(document.getElementById('radioSanded').checked){
        sanded = true;
    } else if(document.getElementById('radioUnsanded').checked){
        sanded = false;
    }  else if(document.getElementById('radioAll').checked){
        sanded = null;
    }

    var resistance = null;
    var radioResistance = document.getElementById('radioResistance');
    if(document.getElementById('radioResistance').checked == true){
        resistance = true;
    } else if(document.getElementById('radioUnresistance').checked){
        resistance = false;
    }  else if(document.getElementById('radioAllResistance').checked){
        resistance = null;
    }

    var coating = null;
    if(document.getElementById('radioTheColor').checked == true){
        coating = true;
    } else if(document.getElementById('radioNoColor').checked){
        coating = false;
    }  else if(document.getElementById('radioAllColor').checked){
        coating = null;
    }

    var coatingPlywood = document.getElementById('coatingPlywood');
    var coatingPlywoodList = coatingPlywood.value.split(",");
    var minCoating = coatingPlywoodList[0];
    var maxCoating = coatingPlywoodList[1];

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
        "minCoating":minCoating,
        "maxCoating":maxCoating,
        "sanded":sanded,
        "resistance":resistance,
        "coating":coating
    };
    var urlCount = '/pagination/countAllPlywood';


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

ScriptPlywood.prototype.getObjects = function(parentElement,parentListElement,element,start,end,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth,count) {
    
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
        "maxDepth":maxDepth,
    };

    var urlObjects = '/pagination/plywoods';



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
        ScriptPlywood.prototype.addData(objects,parentElement,parentListElement,start,end,count);

    });
}

ScriptPlywood.prototype.addData = function(objects,parentElement,parentListElement,start,end,count) {
    for (var i = 0; i < objects.length; i++) {

        var homeCardInfo = document.createElement("div");
        homeCardInfo.classList.add("catalog-item-table-view");
        parentElement.appendChild(homeCardInfo);

        var homeCardInfoList = document.createElement("div");
        parentListElement.appendChild(homeCardInfoList);

        addDataMainPage(homeCardInfo,homeCardInfoList,objects[i]);

        ScriptPlywood.prototype.addDataCompare(homeCardInfo,objects[i]);
        ScriptPlywood.prototype.addDataShoping(homeCardInfo,objects[i]);

        
    }
    var resultCounter = document.getElementById("result-counter" + 2);
    end = (start+end >= count) ? count:start+end;
    start = start + 1;
    resultCounter.innerHTML = 'Showing <span>' + start  + '-' + end + '</span> of <span>' + count +  '</span> results';

}

ScriptPlywood.prototype.addDataShoping = function(parentElement,object){

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

ScriptPlywood.prototype.addDataCompare = function(homeCardInfo,object){
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
