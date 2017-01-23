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

      //  var url = "/admin/run";
       // $( location ).attr("href", url);
    }

    var tr = document.getElementById("tr_" + productId);
    tr.style.display = 'none';

});

$(document).on('click', '.RemoveVideoButton', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 12 && CSE.id.substr(0, 12) == 'removeVideo_') {
        productId = CSE.id.substr(12, CSE.id.length);
    }
    if(productId!=null) {
        var urlObjects = "/admin/video-remove/" + productId;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
        });

        //  var url = "/admin/run";
        // $( location ).attr("href", url);
    }

    var tr = document.getElementById("tr_" + productId);
    tr.style.display = 'none';

});

$(document).on('click', '.RemoveContactButton', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var id = null;
    if (CSE != null && CSE.id.length > 14 && CSE.id.substr(0, 14) == 'removeContact_') {
        id = CSE.id.substr(14, CSE.id.length);
    }
    if(id!=null) {
        var urlObjects = "/admin/contact-remove/" + id;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
        });

        //  var url = "/admin/run";
        // $( location ).attr("href", url);
    }

    var tr = document.getElementById("tr_" + id);
    tr.style.display = 'none';

});

$(document).on('click', '.RemoveEmailDeliveryButton', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var id = null;
    if (CSE != null && CSE.id.length > 20 && CSE.id.substr(0, 20) == 'removeEmailDelivery_') {
        id = CSE.id.substr(20, CSE.id.length);
    }
    if(id!=null) {
        var urlObjects = "/admin/mail-delivery-remove/" + id;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
        });

        //  var url = "/admin/run";
        // $( location ).attr("href", url);
    }

    var tr = document.getElementById("tr_" + id);
    tr.style.display = 'none';

});

$(document).on('click', '.SendMessageEmail', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var id = null;
    if (CSE != null && CSE.id.length > 5 && CSE.id.substr(0, 5) == 'send_') {
        id = CSE.id.substr(5, CSE.id.length);
    }
    if(id!=null) {
        var urlObjects = "/admin/send-update/" + id;
        jQuery.ajax({
            type: "POST",
            url: urlObjects,
            dataType: 'json',
            async: false
        }).done(function (check) {
           var send = document.getElementById("send_" + id);
            send.textContent = check ? "to send":"do not send";
            send.style.color = check ? 'yellowgreen' :'red';
        });

        //  var url = "/admin/run";
        // $( location ).attr("href", url);
    }

});
