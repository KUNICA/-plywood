/**
 * Created by user on 10.08.2016.
 */

$(document).on('click', '.RemoveButton', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 7 && CSE.id.substr(0, 7) == 'remove_') {
        productId = CSE.id.substr(7, CSE.id.length);
    }
    if(productId!=null) {
        var urlObjects = "/admin/remove/" + productId;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
        });

        var url = "/admin/run";
        $( location ).attr("href", url);
    }

});
