/**
 * Created by user on 05.08.2016.
 */


$(document).on('click', '.Galary', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 7 && CSE.id.substr(0, 7) == 'galary_') {
        productId = CSE.id.substr(7, CSE.id.length);
    }

    var mainRow = document.getElementById("mainRow");
    $('.modal-title').textContent = 'photos room';
    mainRow.textContent = '';

    var urlCount = '/pagination/galary/images/' + productId;

    if(productId!=null){


        jQuery.ajax({
            type: "GET",
            url: urlCount,
            dataType:'json',
            async: false
        }).done(function( objects ) {
            $('.modal-title').textContent = 'photos room';
            for (var i = 0; i < objects.length; i++) {
                var thumb = document.createElement("div");
                thumb.classList.add("col-md-3", "col-sm-4", "col-xs-6", "thumb");
                mainRow.appendChild(thumb);

                jQuery.ajax({
                    type: "GET",
                    url: "/pagination/galary/imgPatch/" + objects[i].id,
                    async: false
                }).done(function( m_url ) {
                    var ref = document.createElement("a");
                    ref.classList.add( "fancyimage");
                    ref.rel = "group";
                    ref.href = "/images/product/" + m_url.img;
                    thumb.appendChild(ref);

                    var img = document.createElement("img");
                    img.classList.add( "img-responsive");
                    img.src = "/images/product/" + m_url.img;ref.appendChild(img);
                    
                });
            }
            $('#galary').modal();
            $("a.fancyimage").fancybox();
        });

    }
});


function initGalary(){
    var gallaryMainDiv = $(".gallaryMain")[0];
    var productId = null;
    if (gallaryMainDiv != null && gallaryMainDiv.id.length > 11 && gallaryMainDiv.id.substr(0, 11) == 'gallaryMain') {
        productId = gallaryMainDiv.id.substr(11, gallaryMainDiv.id.length);
    }

    var urlCount = '/pagination/galary/images/' + productId;

    if(productId!=null){


        jQuery.ajax({
            type: "GET",
            url: urlCount,
            dataType:'json',
            async: false
        }).done(function( objects ) {
            var gallaryDiv = $(".gallary")[0];
            for (var i = 0; i < objects.length; i++) {
                gallaryDiv.innerHTML = '<div class="owl-wrapper" style=" max-width:360px; left: 0px; display: block;">' +
                    '<div class="owl-item" style="width: 88px;">' +
                    '<a class="horizontal-thumb" data-target="#owl-single-product" data-slide="0" href="#slide' + (i + 1) + '">' +
                    '<img width="67" id = "img' + objects[i].id  + '" alt="" src="">' +
                    '</a>' +
                    '</div>' +
                    '</div>';

                     gallaryMainDiv.innerHTML = '<div class="owl-wrapper" style="max-width:360px; left: 0px; display: block;">' +
                    '<div class="owl-item" style="width: 353px;">' +
                    '<div class="single-product-gallery-item" id="slide' + (i + 1) + '">' +
                    '<a id = "aImg' + + objects[i].id + '" data-rel="prettyphoto" href="">' +
                    '<img class="img-responsive" id = "imgMain' + + objects[i].id + '"alt="" src="">' +
                    '</a>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

                var object = objects[i];
                jQuery.ajax({
                    type: "GET",
                    url: "/pagination/galary/imgPatch/" + object.id,
                    async: false
                }).done(function( m_url ) {
                    var img = document.getElementById("img" + object.id);
                    img.src = "/images/product/" + m_url.img;
                    var aImg = document.getElementById("aImg" + object.id);
                    aImg.src = "/images/product/" + m_url.img;
                    var imgMain = document.getElementById("imgMain" + object.id);
                    imgMain.src = "/images/product/" + m_url.img;
                });
            }
        });

    }

    

}