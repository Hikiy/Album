$(function() {
    var categoryUrl = '/albumcategory/getbannerlist';
    var photoUrl = '/album/getphotolistbyacid?acid=';
    var showimageUrl = '/image/showimage';
    var count = 0;

    $.ajaxSettings.async = false;
    getCategory();
    $.ajaxSettings.async = true;

    function getCategory() {
        $
            .getJSON(
                categoryUrl,
                function(data) {
                    if (data.ret == 0) {
                        var bannerlist = data.data;
                        var bannerHtml = '';
                        bannerlist
                            .map(function(item, index) {
                                bannerHtml += '<div class="grid__item" style="width: 95%;padding: 30px 10px 30px 10px;" data-size="1x1" align="center" ><a class="img-wrap"><img src="'
                                + showimageUrl + '?filename=' + item.banner
                                    + '" />'
                                + '<div class="description description--grid"></div>'
                                + '</div>'
                                + '</a> </div>'
                                + '<div class="grid">';
                                var photo = getPhoto(item.acid);
                                bannerHtml = bannerHtml + photo + '</div>'
                                    + '<div class="preview" id = "preview' + count +'">' +
                                    '<button class="action action--close"><i class="fa fa-times">x</i><span class="text-hidden">Close</span></button>' +
                                    '<div class="description description--preview"></div>' +
                                    '</div>';
                            });
                        $('#content').html(bannerHtml);
                    }
                });
    }
    function getPhoto(acid) {
        var photoHtml = '';
        var thephotoUrl = photoUrl + acid;
        $
            .getJSON(
                thephotoUrl,
                function(datas) {
                    if (datas.ret == 0) {
                        var photolist = datas.data;
                        photolist
                            .map(function(item, index) {
                                photoHtml += photoHtml + '<div class="grid__item" data-size="1280x720"> <a href="' + showimageUrl + '?filename=' + item.link + '" class="img-wrap"><img src="'+ showimageUrl + '?filename=' + item.link + '" alt="img' + count + '" />' + '<div class="description description--grid" align="center">' + item.description + '<br/>' + item.time + '</div>' + '</a></div>';
                            });
                        count++;
                    }
                });

        return photoHtml;
    }
}(window));