$(function() {
    var photoUrl = '/album/getphotolistbyacid?acid=';
    var albumCategoryInfoUrl = '/albumcategory/getcategorybyacid?acid=';
    var showimageUrl = '/image/showimage';

    var acid = getQueryVariable("acid");
    if( acid == false || acid == null || acid == '' || acid < 1){
        acid = 1;
    }

    var page = getQueryVariable("page");
    if( page == false || page == null || page == '' || page < 1){
        page = 1;
    }

    var size = getQueryVariable("size");
    if( size == false || size == null || size == '' || size < 1){
        size = 21;
    }

    page = Number(page);
    size = Number(size);

    $.ajaxSettings.async = false;
    if( acid != false && acid != null && acid != '' && acid > 0){
        getInfo(acid);
        getPhoto(acid);
    }
    $.ajaxSettings.async = true;

    function getPhoto(acid) {
        var photoHtml = '<div class="grid">';
        var thephotoUrl = photoUrl + acid + '&page=' + page + '&size=' + size;
        $
            .getJSON(
                thephotoUrl,
                function(datas) {
                    if (datas.ret == 0) {
                        var photolist = datas.data;
                        photolist
                            .map(function(item, index) {
                                photoHtml += '<div class="grid__item" data-size="1280x720"> <a href="' + showimageUrl + '?filename=' + item.link + '" class="img-wrap"><img src="'+ showimageUrl + '?filename=' + item.link + '" alt="img' + count + '" />' + '<div class="description description--grid" align="center">' + item.description + '<br/>' + item.time + '</div>' + '</a></div>';
                            });
                        photoHtml += '</div>'
                            + '<div class="preview" id = "preview' + 1 +'">' +
                            '<button class="action action--close"><i class="fa fa-times">x</i><span class="text-hidden">Close</span></button>' +
                            '<div class="description description--preview"></div>' +
                            '</div>';
                        $('#content').append(photoHtml);

                        var pageHtml = '<div class="pagination-bar">';
                        if( page > 1 ){
                            pageHtml += '<a href="#" class="page-prev" onclick="getTheInfo('+ (page-1) +')">&lt;</a>';
                        }
                        var count = datas.count;
                        var pageCount = Math.ceil(count/size);
                        console.log(pageCount);
                        if( page > 2 ){
                            pageHtml += '<a href="#" onclick="getTheInfo(1)">1</a>';
                            pageHtml += '<span>...</span>';
                        }
                        if( page > 1 ){
                            pageHtml += '<a href="#" onclick="getTheInfo('+ (page-1) +')">'+ (page-1) +'</a>'
                        }

                        pageHtml += '<a href="#" class="disabled">'+ page +'</a>';

                        if( page+2 < pageCount ){
                            pageHtml += '<a href="#" onclick="getTheInfo('+ (page+1) +')">'+ (page+1) +'</a>'
                            pageHtml += '<span>...</span>';
                        }

                        if( page < pageCount ){
                            pageHtml += '<a href="#" onclick="getTheInfo('+ pageCount +')">'+ pageCount +'</a>';
                            pageHtml += '<a href="#" class="page-next" onclick="getTheInfo('+ (page+1) +')">&gt;</a>' +
                                '</div>';
                        }
                        $('#content').append(pageHtml);
                    }
                });

        return photoHtml;
    }

    function getInfo(acid) {
        $
            .getJSON(
                albumCategoryInfoUrl + acid,
                function(data) {
                    if (data.ret == 0) {
                        var albumcategory = data.data;
                        document.title = albumcategory.name;
                        var albumBannerHtml = '<div class="grid__item" style="width: 100%" data-size="1x1" align="center" ><a class="img-wrap"><img src="'
                            + showimageUrl + '?filename=' + albumcategory.banner +
                            '" />'
                            + '<div class="description description--grid"></div>'
                            + '</div>'
                            + '</a> </div>';
                        $('#content').append(albumBannerHtml);
                    }
                });
    }

    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
}(window));