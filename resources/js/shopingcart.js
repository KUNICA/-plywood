/**
 * Created by user on 06.08.2016.
 */

$(document).on('click', '.ShoppingCar', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 10 && CSE.id.substr(0, 10) == 'addto-cart') {
        productId = CSE.id.substr(10, CSE.id.length);
    }

    var shop = document.getElementById("addto-cart" + productId);
    var viewShop = document.getElementById("viewShop_" + productId);
    var plus = document.getElementById("plus_" + productId);
    var minus = document.getElementById("minus_" + productId);
    var quant = document.getElementById("le-quantity" + productId);
    var countShoppingCar = document.getElementById("countCart_" + productId);

    if(productId!=null){
        var urlObjects = "/shoping/ischeck/" + productId;
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType:'json',
            async: false
        }).done(function( checkProduct ) {

            urlObjects = "/shoping/cart/" + productId;

        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: urlObjects,
            dataType:'json',
            async: true
        }).done(function(product) {
            if(product != undefined && !product.actual){
                var id = null;
                var username = product.username;
                var totalAmount = null;
                var actual = null;

                if(product.check){
                    // удалить выделение
                    // абновить с  actual == null
                    actual = null;
                    id = product.id;
                    shop.classList.remove("ShoppingCartCheck");
                    shop.textContent = "Add to cart";
                    quant.style.display = 'none';
                    viewShop.style.display = 'none';
                }
                else if(product.check == undefined && product.id!= undefined){
                    // выделить и обновить с actual == 1
                    actual = 1;
                    id = product.id;
                    shop.classList.add("ShoppingCartCheck");
                    shop.textContent = "Remove to cart";
                    quant.style.display = 'block';
                    viewShop.style.display = 'block';
                }
                else {
                    //внести запись с actual == 1
                    actual = 1;
                    shop.classList.add("ShoppingCartCheck");
                    viewShop.style.display = 'block';
                    shop.textContent = "Remove to cart";
                    quant.style.display = 'block';
                    
                }

                var  data={
                        "id": id,
                        "username": username,
                        "productId": productId,
                        "totalAmount": totalAmount,
                        "actual": null,
                        "check": actual
                };
                urlObjects = "/shoping/save";
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
                }).done(function( product ) {
                    countShoppingCar.value=product.count;
                });

            }
        });

        });



    }
});



$(document).on('click', '#pay', function(e) {

    var sumPay = document.getElementById("sumPay");

    var tableSumPay = document.getElementById("tableSumPay");
    tableSumPay.textContent = '';

    var urlObjects = "/shoping/products";
    jQuery.ajax({
        type: "GET",
        url: urlObjects,
        dataType:'json',
        async: false
    }).done(function( products ) {
        var sum = 0;
        for(var i=0; i<products.length; i++){

            var tr = document.createElement("tr");
            tableSumPay.appendChild(tr);
            var th = document.createElement("th");
            th.textContent = products[i].name + ': ';
            tr.appendChild(th);
            var th2 = document.createElement("th");
            th2.textContent = products[i].count*products[i].price + '$';
            tr.appendChild(th2);
            sum+=products[i].count*products[i].price;
        }

        var trSum = document.createElement("tr");
        tableSumPay.appendChild(trSum);
        var thSum = document.createElement("th");
        thSum.textContent = 'The total amount of the order: ';
        trSum.appendChild(thSum);
        var th2Sum = document.createElement("th");
        th2Sum.textContent = sum + '$';
        trSum.appendChild(th2Sum);

        var trButton = document.createElement("tr");
        tableSumPay.appendChild(trButton);
        var thButton = document.createElement("th");
        trButton.appendChild(thButton);

        jQuery.ajax({
            type: "POST",
            url: "/offer/isOffer",
            dataType:'json',
            async: false
        }).done(function( nocheck ) {
            if(nocheck && products!=undefined && products.length>0){
                var button = document.createElement("button");
                button.type = "button";
                button.id = "offer";
                button.style =  'margin-left:auto; margin-right:auto;';
                button.textContent = 'Pay';
                button.classList.add( "btn","btn-success");
                trButton.appendChild(button);


                var thButtonPrint = document.createElement("th");
                trButton.appendChild(thButtonPrint);

                var buttonPrint = document.createElement("button");
                buttonPrint.type = "button";
                buttonPrint.id = "print";
                buttonPrint.style =  'margin-left:auto; margin-right:auto;';
                buttonPrint.textContent = 'Print';
                buttonPrint.classList.add( "btn","btn-info");
                trButton.appendChild(buttonPrint);
            }else if(!nocheck){
                var thButtonOffer = document.createElement("th");
                trButton.appendChild(thButtonOffer);

                var buttonOffer = document.createElement("button");
                buttonOffer.type = "button";
                buttonOffer.id = "offerPrint";
                buttonOffer.style =  'margin-left:auto; margin-right:auto;';
                buttonOffer.textContent = 'Offer';
                buttonOffer.classList.add( "btn","btn-info");
                trButton.appendChild(buttonOffer);
            }
        });

        $('#shoppingCart').modal();

    });

});

$(document).on('click', '.plusShoppingCart', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 5 && CSE.id.substr(0, 5) == 'plus_') {
        productId = CSE.id.substr(5, CSE.id.length);
    }

    var urlPlus = "/shoping/plus";
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlPlus,
        dataType:'json',
        data:  $.toJSON(productId),
        async: true
    }).done(function(count) {
        var countShoppingCar = document.getElementById("countCart_" + productId);
        countShoppingCar.textContent = count;
    });
});

$(document).on('click', '.minusShoppingCart', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 6 && CSE.id.substr(0, 6) == 'minus_') {
        productId = CSE.id.substr(6, CSE.id.length);
    }

    var urlMinus = "/shoping/minus";
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlMinus,
        dataType:'json',
        data: $.toJSON(productId),
        async: true
    }).done(function(count) {
        var countShoppingCar = document.getElementById("countCart_" + productId);
        countShoppingCar.textContent = count;
    });
});

$(document).on('click', '#print', function(e) {
    $('#shoppingCart').modal("hide");
    window.open("/report/print");

});

$(document).on('click', '#offer', function(e) {
    var url = "/offer/create";
    $( location ).attr("href", url);

});

$(document).on('click', '#offerPrint', function(e) {
    var url = "/offer/print";
    $( location ).attr("href", url);

});
