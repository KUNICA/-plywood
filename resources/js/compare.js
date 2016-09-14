/**
 * Created by user on 07.08.2016.
 */


$(document).on('click', '.checkBoxField', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    var checkBox = null;
    if (CSE != null && CSE.id.length > 14 && CSE.id.substr(0, 14) == 'check_compare_') {
        productId = CSE.id.substr(14, CSE.id.length);
        checkBox = document.getElementById("check_compare_" + productId);
        var checkBox2 = document.getElementById("check_compareList_" + productId);
        if(checkBox2!=undefined) {
            checkBox2.checked = checkBox.checked;
        }
    }
    else if (CSE != null && CSE.id.length > 18 && CSE.id.substr(0, 18) == 'check_compareList_'){
        productId = CSE.id.substr(18, CSE.id.length);
        checkBox = document.getElementById("check_compareList_" + productId);
        checkBox2 = document.getElementById("check_compare_" + productId);
        if(checkBox!=undefined) {
            checkBox2.checked = checkBox.checked;
        }
    }
    if(checkBox.checked){
        if(checkBox!=undefined) {
            checkBox.checked = "";
        }
        if(checkBox2!=undefined) {
            checkBox2.checked = "";
        }
    }else{
        if(checkBox!=undefined) {
            checkBox.checked = "checked";
        }
        if(checkBox2!=undefined) {
            checkBox2.checked = "checked";
        }
    }

    if(productId!=null) {
        var urlObjects = "/compare/isProduct/" + productId;
        jQuery.ajax({
            type: "GET",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (checkProduct) {

            if(checkProduct==undefined || !checkProduct){
                urlObjects = "/compare/save/" + productId;
            }else{
                urlObjects = "/compare/remove/" + productId;
            }
            jQuery.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "POST",
                url: urlObjects,
                dataType:'json',
                async: false
            }).done(function( check ) {
                if(checkProduct==undefined || !checkProduct){
                    if(checkBox!=undefined) {
                        checkBox.checked = "checked";
                    }
                    if(checkBox2!=undefined) {
                        checkBox2.checked = "checked";
                    }
                }else{
                    if(checkBox!=undefined) {
                        checkBox.checked = "";
                    }
                    if(checkBox2!=undefined) {
                        checkBox2.checked = "checked";
                    }
                }

                var typeProduct = document.getElementById("typeProduct");
                jQuery.ajax({
                    type: "GET",
                    url: "/compare/count/" + typeProduct.value,
                    dataType:'json',
                    async: true
                }).done(function(count) {
                    var compareCount = document.getElementById("compareCount");
                    compareCount.textContent = '(' + count + ')';
                });
                
            });
            

        });
    }
    


});


$(document).on('click', '.buttonCompare', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var type = null;
    if (CSE != null && CSE.id.length > 8 && CSE.id.substr(0, 8) == 'compare_') {
        type = CSE.id.substr(8, CSE.id.length);
    }

    window.open("/compare/table/" + type);

});