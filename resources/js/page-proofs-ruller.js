/**
 * Created by user on 26.08.2016.
 */


var ScriptRuller = function(index){
    
}


function addDataMainPage(homeCardInfo,object){


    var urlImg = "/images/product/";

    homeCardInfo.innerHTML =
                    '<div class="catalog-item-card">' +
                            '<div class="catalog-item-info">' +
                                    '<div class="item-all-title" id = "compare' + object.id + '">' +
                                    '</div>' +
                                    '<div class="item-image-cont">' +
                                            '<div class="item-image">' +
                                                    '<a href="/viewproduct/product/' + object.id + '">' +
                                                            '<img class="item_img" id ="item_img' + object.id + '"  src="" width="178" height="166" alt="' + object.name + '">' +
                                                    '</a>' +
                                            '</div>' +
                                    '</div>' +
                                    '<div class="item-all-title" id = "params' + object.id + '">' +
                                    '</div>' +
                                    '<div class="item-all-title">' +
                                            '<a class="item-title" href="/viewproduct/product/' + object.id + '" title="' + object.name  + '">' +
                                                        '<span>' + object.name + '</span>' +
                                            '</a>' +
                                    '</div>' +
                    '<div class="item-all-title" id = "shop' + object.id + '">' +
                    '</div>' +
                    '</div>' +
                                    '<div class="item-price-cont reference" itemscope="">' +
                                            '<div class="item-price">' +
                                                        '<span class="catalog-item-price">' + object.price +
                                                                        '<span class="unit">' + '$' +
                                                                                    '<span>for one</span>' +
                                                                        '</span>' +
                                                        '</span>' +
                                             '</div>' + 
                                    '</div>' +
                    '</div>';

    var urlImgPatch = '/pagination/imgPatch/';
    var urlImg = "/images/product/";
    jQuery.ajax({
        type: "GET",
        url: urlImgPatch + object.id,
        async: false
    }).done(function( m_url ){
        var img = document.getElementById("item_img" + object.id);
        img.src = urlImg + m_url.img;
    });


    var params = document.getElementById('params' + object.id);

    var length = document.createElement("i");
    length.classList.add( "material-icons");
    length.textContent = "L";
    length.style.cursor =  "pointer";
    length.setAttribute('data-toggle', 'tooltip');
    length.setAttribute('data-placement', 'tooltip');
    length.setAttribute('title', 'Length');
    params.appendChild(length);
    var lengthSpan = document.createElement("span");
    lengthSpan.textContent = object.length;
    params.appendChild(lengthSpan);

    var cross1 = document.createElement("i");
    cross1.classList.add( "material-icons");
    cross1.textContent = " ";
    cross1.style.cursor =  "pointer";
    cross1.setAttribute('data-toggle', 'tooltip');
    cross1.setAttribute('data-placement', 'tooltip');
    cross1.setAttribute('title', 'Width');
    params.appendChild(cross1);


    var width = document.createElement("i");
    width.classList.add( "material-icons");
    width.textContent = "W";
    width.setAttribute('data-toggle', 'tooltip');
    width.setAttribute('data-placement', 'tooltip');
    width.setAttribute('title', 'Width');
    width.style.cursor =  "pointer";
    params.appendChild(width);
    var widthSpan = document.createElement("span");
    widthSpan.textContent = object.width;
    params.appendChild(widthSpan);

    var cross2 = document.createElement("i");
    cross2.classList.add( "material-icons");
    cross2.textContent = " ";
    cross2.style.cursor =  "pointer";
    cross2.setAttribute('data-toggle', 'tooltip');
    cross2.setAttribute('data-placement', 'tooltip');
    cross2.setAttribute('title', 'Width');
    params.appendChild(cross2);

    var depth = document.createElement("i");
    depth.classList.add( "material-icons");
    depth.textContent = "D";
    depth.style.cursor =  "pointer";
    depth.setAttribute('data-toggle', 'tooltip');
    depth.setAttribute('data-placement', 'tooltip');
    depth.setAttribute('title', 'Depth');
    params.appendChild(depth);
    var depthSpan = document.createElement("span");
    depthSpan.textContent = object.depth;
    params.appendChild(depthSpan);
    
}

function addDataComparePage(homeCardInfo,object,checkProduct){
    var parentCheck = document.getElementById('compare' + object.id);
    var checkBoxCompare = document.createElement("div");
    checkBoxCompare.classList.add( "checkbox", "checkBoxCompare");
    parentCheck.appendChild(checkBoxCompare);

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
    labelCompare.textContent = "compare";
    divCompr.appendChild(labelCompare);

/*
    var buttonCompare = document.createElement("button");
    buttonCompare.id = "compare_" + object.id;
    buttonCompare.classList.add( "btn","btn-info","buttonCompare");
    buttonCompare.textContent = "compare";
    divCompr.appendChild(buttonCompare);
    */
}


function addDataShopingPage(parentElement,object,product){



    var parentShop = document.getElementById('shop' + object.id);
    var homeCardFeatureShoppingCar= document.createElement("div");
    homeCardFeatureShoppingCar.classList.add( "homeCardFeature", "homeCardGalary","ShoppingCar");
    parentShop.appendChild(homeCardFeatureShoppingCar);

    var homeCardShoppingCar = document.createElement("i");
    homeCardShoppingCar.style.cursor =  "pointer";
    var plusShoppingCar = document.createElement("i");
    plusShoppingCar.style.cursor =  "pointer";
    var countFeatureShoppingCar= document.createElement("b");
    countFeatureShoppingCar.style.cursor =  "pointer";
    var minusShoppingCar = document.createElement("i");
    minusShoppingCar.style.cursor =  "pointer";
    plusShoppingCar.classList.add("plusShoppingCart");
    minusShoppingCar.classList.add("minusShoppingCart");
    if(product!= undefined && product.check){
        homeCardShoppingCar.classList.add("ShoppingCartCheck");
        homeCardShoppingCar.textContent = "out_shopping_cart";
        plusShoppingCar.style.display = '';
        minusShoppingCar.style.display = '';
        countFeatureShoppingCar.style.display = '';
    }
    else{
        homeCardShoppingCar.textContent = "add_shopping_cart";
        plusShoppingCar.style.display = 'none';
        minusShoppingCar.style.display = 'none';
        countFeatureShoppingCar.style.display = 'none';
    }

    plusShoppingCar.classList.add( "material-icons");
    plusShoppingCar.setAttribute('data-toggle', 'tooltip');
    plusShoppingCar.setAttribute('data-placement', 'tooltip');
    plusShoppingCar.setAttribute('title', 'plus one');
    plusShoppingCar.id = "plus_" + object.id;

    homeCardFeatureShoppingCar.appendChild(plusShoppingCar);
    plusShoppingCar.textContent = "add";

    countFeatureShoppingCar.classList.add( "homeCardFeature", "countShoppingCar");
    homeCardFeatureShoppingCar.appendChild(countFeatureShoppingCar);
    countFeatureShoppingCar.setAttribute('data-toggle', 'tooltip');
    countFeatureShoppingCar.setAttribute('data-placement', 'tooltip');
    countFeatureShoppingCar.setAttribute('title', 'count');
    countFeatureShoppingCar.id = "countCart_" + object.id;
    countFeatureShoppingCar.textContent =  product.count;

    minusShoppingCar.classList.add( "material-icons");
    minusShoppingCar.setAttribute('data-toggle', 'tooltip');
    minusShoppingCar.setAttribute('data-placement', 'tooltip');
    minusShoppingCar.setAttribute('title', 'minus one');
    minusShoppingCar.id = "minus_" + object.id;

    homeCardFeatureShoppingCar.appendChild(minusShoppingCar);
    minusShoppingCar.textContent = "remove";

    homeCardShoppingCar.classList.add( "material-icons");
    homeCardShoppingCar.setAttribute('data-toggle', 'tooltip');
    homeCardShoppingCar.setAttribute('data-placement', 'top');
    homeCardShoppingCar.setAttribute('title', 'to order');
    homeCardShoppingCar.id = "order_" + object.id;
    homeCardFeatureShoppingCar.appendChild(homeCardShoppingCar);
}