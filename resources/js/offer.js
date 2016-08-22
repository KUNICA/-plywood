/**
 * Created by user on 12.08.2016.
 */


$(document).on('click', '#removeOffer', function(e) {
    var url = "/home/remove";
    $( location ).attr("href", url);
});