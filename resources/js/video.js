/**
 * Created by user on 05.08.2016.
 */
$(document).on('click', '.Video', function(e) {

    e ? evt = e : evt = event;
    var CSE = evt.target ? evt.target : evt.srcElement;
    var productId = null;
    if (CSE != null && CSE.id.length > 6 && CSE.id.substr(0, 6) == 'video_') {
        productId = CSE.id.substr(6, CSE.id.length);
    }

    var mainRow = document.getElementById("video12");
    $('.modal-title').textContent = 'videos room';
    mainRow.textContent = '';

    var urlCount = '/video/videos/' + productId;

    if(productId!=null){
        jQuery.ajax({
            type: "GET",
            url: urlCount,
            dataType:'json',
            async: false
        }).done(function( objects ) {
            $('.modal-title').textContent = 'photos room';

            var iframe = document.createElement("iframe");
            if(objects.length >0){
                iframe.src = "http://www.youtube.com/embed/" + objects[0].link + "?rel=0";
            }
            iframe.allowfullscreen = "";
            iframe.frameborder = "0";
            mainRow.appendChild(iframe);

            var div = document.createElement("div");
            mainRow.appendChild(div);

            for (var i = 0; i < objects.length; i++) {
                var img1 = document.createElement("img");
                img1.src = "http://img.youtube.com/vi/" + objects[i].link + "/1.jpg";
                img1.tabindex = "2";
                div.appendChild(img1);
            }

        });

        var IMG = document.querySelectorAll('#video12 img'),
            IFRAME = document.querySelector('#video12 iframe');
        for (var i = 0; i < IMG.length; i++) {
            IMG[i].onclick = function() {
                var idIMG = this.src.replace(/http...img.youtube.com.vi.([\s\S]*?).1.jpg/g, '$1');
                IFRAME.src = 'http://www.youtube.com/embed/' + idIMG + '?rel=0&autoplay=1';
                if(this.dataset.end) IFRAME.src = IFRAME.src.replace(/([\s\S]*)/g, '$1&end=' + this.dataset.end);
                if(this.dataset.start) IFRAME.src = IFRAME.src.replace(/([\s\S]*)/g, '$1&start=' + this.dataset.start);
                this.style.backgroundColor='#555';
            }
        }

        $('#video').modal();
    }
});