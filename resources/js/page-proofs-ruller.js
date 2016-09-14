/**
 * Created by user on 26.08.2016.
 */


var ScriptRuller = function(index){
    
}


function addDataMainPage(homeCardInfo,parentListElement,object){


    var urlImg = "/images/product/";
/*
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
                    */

    homeCardInfo.innerHTML =
                '<div class="col-xs-12 col-sm-4 no-margin product-item-holder hover">' +
                        '<div class="product-item">' +
                                //'<div class="ribbon red"><span>sale</span></div>' +
                                        '<div class="image">' +
                                                '<img style=" width: 200px; height: 200px;" id = "item_img' + object.id + '" alt="" src="/viewproduct/product/' + object.id + '">' +
                                        '</div>' +
                                '<div class="body">' +
                                       // '<div class="label-discount green">-50% sale</div>' +
                                        '<div class="title">' +
                                                '<a href="/viewproduct/product/' + object.id + '">' + object.name + '</a>' +
                                        '</div>' +
                                '<div class="item-all-title" id = "params' + object.id + '">' +
                                '</div>' + 
                        '</div>' +
                        '<div class="prices">' +
                                '<div class="price-prev">$' + object.price + '</div>' +
                                '<div class="price-current pull-right">$' + object.price + '</div>' +
                        '</div>' +
                        '<div class="hover-area">' +
                                '<div class="add-cart-button" id="shop' + object.id + '" style="display: none">' +
                                        '<a href="/viewproduct/product/' + object.id + '" class="le-button">add to cart</a>' +
                                '</div>' +
                                '<div class="wish-compare">' +
                                        '<div class="item-all-title" id = "compare' + object.id + '">' +
                                        '</div>' +
                                '</div>' +
                        '</div>' +
                '</div>' +
                '</div>';



    parentListElement.innerHTML = '<div class="product-item product-item-holder">' +
                                 // '<div class="ribbon red"><span>sale</span></div>' +
                                 // '<div class="ribbon blue"><span>new!</span></div>' +
                                        '<div class="row">' +
                                            '<div class="no-margin col-xs-12 col-sm-4 image-holder">' +
                                                    '<div class="image">' +
                                                            '<img id = "item_imgList' + object.id + '" alt="" src="/viewproduct/product/' + object.id + '">' +
                                                    '</div>' +
                                            '</div>' +
                                            '<div class="no-margin col-xs-12 col-sm-5 body-holder">' +
                                                '<div class="body">' +
                                                   // '<div class="label-discount green">-50% sale</div>' +
                                                    '<div class="title">' +
                                                        '<a href="/viewproduct/product/' + object.id + '">'+ object.name +'</a>' +
                                                    '</div>' +
                                                   // '<div class="brand">sony</div>' +
                                                    '<div class="excerpt" id = "paramsList' + object.id + '">' +
                                                    '</div>' +
                                                    '<div class="addto-compare">' +
                                                            '<div class="item-all-title" id = "compareList' + object.id + '">' +
                                                            '</div>' +
                                                    '</div>' +
                                                '</div>' +
                                        '</div>' +
                                        '<div class="no-margin col-xs-12 col-sm-3 price-area" >' +
                                                '<div class="right-clmn">' +
                                                        '<div class="price-current">$' + object.price + '</div>' +
                                                        '<div class="price-prev">$' + object.price + '</div>' +
                                                        '<div class="availability"><label>availability:</label><span class="available">  in stock</span></div>' +
                                                        '<a style="display:none" id="shopList' + object.id + '" class="le-button" href="/viewproduct/product/' + object.id + '">add to cart</a>' +
                                                       // '<a class="btn-add-to-wishlist" href="#">add to wishlist</a>' +
                                                '</div>' +
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
        var imgList = document.getElementById("item_imgList" + object.id);
        imgList.src = urlImg + m_url.img;
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




    var paramsList = document.getElementById('paramsList' + object.id);

    var lengthList = document.createElement("i");
    lengthList.classList.add( "material-icons");
    lengthList.textContent = "L";
    lengthList.style.cursor =  "pointer";
    lengthList.setAttribute('data-toggle', 'tooltip');
    length.setAttribute('data-placement', 'tooltip');
    lengthList.setAttribute('title', 'Length');
    paramsList.appendChild(lengthList);
    var lengthSpanList = document.createElement("span");
    lengthSpanList.textContent = object.length;
    paramsList.appendChild(lengthSpanList);

    var cross1List = document.createElement("i");
    cross1List.classList.add( "material-icons");
    cross1List.textContent = " ";
    cross1List.style.cursor =  "pointer";
    cross1List.setAttribute('data-toggle', 'tooltip');
    cross1List.setAttribute('data-placement', 'tooltip');
    cross1List.setAttribute('title', 'Width');
    paramsList.appendChild(cross1List);


    var widthList = document.createElement("i");
    widthList.classList.add( "material-icons");
    widthList.textContent = "W";
    widthList.setAttribute('data-toggle', 'tooltip');
    widthList.setAttribute('data-placement', 'tooltip');
    widthList.setAttribute('title', 'Width');
    widthList.style.cursor =  "pointer";
    paramsList.appendChild(widthList);
    var widthSpanList = document.createElement("span");
    widthSpanList.textContent = object.width;
    paramsList.appendChild(widthSpanList);

    var cross2List = document.createElement("i");
    cross2List.classList.add( "material-icons");
    cross2List.textContent = " ";
    cross2List.style.cursor =  "pointer";
    cross2List.setAttribute('data-toggle', 'tooltip');
    cross2List.setAttribute('data-placement', 'tooltip');
    cross2List.setAttribute('title', 'Width');
    paramsList.appendChild(cross2List);

    var depthList = document.createElement("i");
    depthList.classList.add( "material-icons");
    depthList.textContent = "D";
    depthList.style.cursor =  "pointer";
    depthList.setAttribute('data-toggle', 'tooltip');
    depthList.setAttribute('data-placement', 'tooltip');
    depthList.setAttribute('title', 'Depth');
    paramsList.appendChild(depthList);
    var depthSpanList = document.createElement("span");
    depthSpanList.textContent = object.depth;
    paramsList.appendChild(depthSpanList);


    
}

function addDataComparePage(homeCardInfo,object,checkProduct){
    var parentCheck = document.getElementById('compare' + object.id);
    var checkBoxCompare = document.createElement("div");
    checkBoxCompare.classList.add( "checkbox", "checkBoxCompare");
    parentCheck.appendChild(checkBoxCompare);


    var divCompr = document.createElement("div");
    divCompr.style = "height: 20px;";
    checkBoxCompare.appendChild(divCompr);

    var inputCheckBoxCompare = document.createElement("input");
    inputCheckBoxCompare.style = "margin-top: -10px; margin-left: 50px;";
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
    labelCompare.style="margin-top: -15px;";
    labelCompare.classList.add( "labelCompare");
    labelCompare.textContent = "compare";
    divCompr.appendChild(labelCompare);



    var parentCheckList = document.getElementById('compareList' + object.id);
    var checkBoxCompareList = document.createElement("div");
    checkBoxCompareList.classList.add( "checkbox", "checkBoxCompare");
    parentCheckList.appendChild(checkBoxCompareList);

    var divComprList = document.createElement("div");
    checkBoxCompareList.appendChild(divComprList);

    var inputCheckBoxCompareList = document.createElement("input");
    inputCheckBoxCompareList.type = "checkbox";
    inputCheckBoxCompareList.classList.add( "checkBoxField");
    if(checkProduct!= undefined && checkProduct){
        inputCheckBoxCompareList.checked = "checked";
    } else{
        inputCheckBoxCompareList.checked = "";
    }
    inputCheckBoxCompareList.id = "check_compareList_" + object.id;

    divComprList.appendChild(inputCheckBoxCompareList);

    var labelCompareList = document.createElement("label");
    labelCompareList.classList.add( "labelCompare");
    labelCompareList.textContent = "compare";
    divComprList.appendChild(labelCompareList);
    

}


function addDataShopingPage(parentElement,object,product){

    var parentShop = document.getElementById('shop' + object.id);
    parentShop.style.display = "block";
    var parentShopList = document.getElementById('shopList' + object.id);
    parentShopList.style.display = "block";


    /*

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
    */
}