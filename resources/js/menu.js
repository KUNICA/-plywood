/**
 * Created by user on 13.08.2016.
 */
// With JQuery
$("#ex2").slider({
    formatter: function(value) {
    return 'Current value: ' + value;
}});
$('#ex1').slider({
    formatter: function(value) {
        return 'Persons: ' + value;
    }
});
$('#ex3').slider({
    formatter: function(value) {
        return 'Badrooms: ' + value;
    }
});
$('#ex4').slider({
    formatter: function(value) {
        return 'Bathrooms: ' + value;
    }
});

$(".slider-handle").mouseup(function()   {
    initPagination('/pagination/countActualObjects');
});

