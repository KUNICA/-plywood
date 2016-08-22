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
                    type: "POST",
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