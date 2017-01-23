/**
 * Created by user on 05.08.2016.
 */


$(document).on('click', '.Galary', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 8 && CSE.id.substr(0, 8) == 'gallery_') {
        productId = CSE.id.substr(8, CSE.id.length);
    }

    var checkBoxsList = $(".Galary");
    var gallery = document.getElementById("galleryId");
    gallery.innerHTML = "";
    for (var j=0;j<checkBoxsList.length;j++){
        var checkBox = checkBoxsList[j];
        if(checkBox.checked){
            if (checkBox != null && checkBox.id.length > 8 && checkBox.id.substr(0, 8) == 'gallery_') {
                productId = checkBox.id.substr(8, checkBox.id.length);
                
                var urlCount = '/pagination/galary/images/' + productId;

                if(productId!=null){


                    jQuery.ajax({
                        type: "GET",
                        url: urlCount,
                        dataType:'json',
                        async: false
                    }).done(function( objects ) {
                        for (var i = 0; i < objects.length; i++) {
                            var object = objects[i];
                            gallery.innerHTML += '<div class="item" id = "itemGallery' + object.productId + '">' +
                                '<div class="thumb">' +
                                '<a href="#" id = "imgRef' + object.id + '" class="fancyimage" rel="group">' +
                                '<img width="130px" src="" alt="" id = "imgFancy' + object.id + '" />' +
                                '</a>' +
                                '</div>' +
                                ' <button  value="remove" id = "removeImg' + object.id + '" class="RemoveImgGallary le-button huge" style=" margin-top: -20px; margin-left: 6px;width: 140px; height: 20px;">Remove</button>>' +
                                    '<input type="hidden" id = "removeInput'+ object.id + '" value="' + object.img + '">' +
                                '<div class="tools">' +
                                '<a href="#" class="ico ico-edit"></a>' +
                                '<a href="#" class="ico ico-delete"></a>' +
                                '</div>' +
                                '<div class="thumb">' +
                                '</div>' +
                                '</div>';

                            jQuery.ajax({
                                type: "GET",
                                url: "/pagination/galary/imgPatch/" + object.id,
                                async: false
                            }).done(function( m_url ) {
                                var ref = document.getElementById("imgRef" + object.id);
                                ref.href = "/images/product/" + m_url.img;
                                var imgFancy = document.getElementById("imgFancy" + object.id);
                                imgFancy.src = "/images/product/" + m_url.img;
                            });
                        }
                    });

                }


            }

        }
    }

});

$(document).on('click', '.RemoveImgGallary', function(e) {
    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var id = null;
    if (CSE != null && CSE.id.length > 9 && CSE.id.substr(0, 9) == 'removeImg') {
        id = CSE.id.substr(9, CSE.id.length);
    }
    var imgName = document.getElementById("removeInput" + id);
    var urlObjects = '/admin/removeImg/' + imgName.value;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: urlObjects,
        dataType:'json',
        async: false
    });

    var itemGallery = document.getElementById("itemGallery" + id);
    itemGallery.style.display = 'none';

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