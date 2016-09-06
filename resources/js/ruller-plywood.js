/**
 * Created by user on 26.08.2016.
 */

var ScriptPlywood = function(){
    this.paramsUrl = '/pagination/parametrsPlywoods';
}


ScriptPlywood.prototype.initParam = function(innerHtml,parametrs){
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
    var urlCount = '/pagination/countActualPlywood';


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

ScriptPlywood.prototype.getObjects = function(parentElement,element,start,end,minPrice,maxPrice,minLength,maxLength,minWidth,maxWidth,minDepth,maxDepth) {
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
        ScriptPlywood.prototype.addData(objects,parentElement);

    });
}

ScriptPlywood.prototype.addData = function(objects,parentElement) {
    for (var i = 0; i < objects.length; i++) {

        var ref = document.createElement("div");
        ref.classList.add( "homeCardLink", "searchItemsItem" );
        ref.href = "#";
        ref.id = "idRef" + objects[i].id;
        parentElement.appendChild(ref);

        var homeCardImage = document.createElement("div");
        homeCardImage.classList.add( "homeCardImage" );
        ref.appendChild(homeCardImage);



        var homeCardInfo = document.createElement("div");
        homeCardInfo.classList.add("catalog-item-table-view");
        ref.appendChild(homeCardInfo);

        addDataMainPage(homeCardInfo,objects[i]);
        ScriptPlywood.prototype.addDataCompare(homeCardInfo,objects[i]);
        ScriptPlywood.prototype.addDataShoping(homeCardInfo,objects[i]);

        
    }

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
