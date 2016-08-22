/**
 * Created by user on 07.08.2016.
 */


$(document).on('click', '.checkBoxField', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 14 && CSE.id.substr(0, 14) == 'check_compare_') {
        productId = CSE.id.substr(14, CSE.id.length);
    }
    var checkBox = document.getElementById("check_compare_" + productId);

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
                    checkBox.checked = "checked";
                }else{
                    checkBox.checked = "";
                }
            });
            

        });
    }


});


$(document).on('click', '.buttonCompare', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 8 && CSE.id.substr(0, 8) == 'compare_') {
        productId = CSE.id.substr(8, CSE.id.length);
    }

    window.open("compare/table/" + productId);

});