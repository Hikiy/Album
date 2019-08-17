$(function() {
    var photoUrl = '/album/getphotolistbyacid?acid=';
    var imageUrl = '/image/showimage?filename=';

    var acid = getQueryVariable("acid");
    if( acid == null || acid == '' || acid < 1){
        aicd = 0;
    }

    $.ajaxSettings.async = false;
    getList();
    $.ajaxSettings.async = true;

    function getList() {
        $
            .getJSON(
                photoUrl + acid,
                function(data) {
                    if (data.ret == 0) {
                        var photolist = data.data;
                        var photoHtml = '';
                        photolist
                            .map(function(item, index) {
                                photoHtml += '<div class="card"><div class="card-content"><div class="list-block media-list"><ul><div class="item-inner">' +
                                    '<div class="item-title-row">' +
                                    '<div class="item-title">' + item.description + '</div>' +
                                    '</div>' +
                                    '<div class="item-subtitle">' + item.time + '</div>' +
                                    '<div class="card-footer no-border">' +
                                    '     <a href="' + imageUrl + item.link + '"' +
                                    '         class="item-link list-button">查看图片</a>' +
                                    '     <a href="javascript:void(0)" class="link" onclick="deletebypid('+ item.pid +')">删除</a>' +
                                    '</div>' +
                                    '</div></ul></div></div></div>';
                            });
                        $('#content').html(photoHtml);
                    }
                });
    }

    // function deletebypid(pid) {
    //     if(window.confirm('确定删除？')){
    //         var formData = new FormData();
    //         formData.append('pid', pid);
    //
    //         $.ajax({
    //             url : deletUrl,
    //             type : 'POST',
    //             data : formData,
    //             contentType : false,
    //             processData : false,
    //             cache : false,
    //             success : function(data) {
    //                 if (data.ret == 0) {
    //                     $.toast('删除成功！');
    //                 } else {
    //                     $.toast('删除失败！');
    //                 }
    //                 getList();
    //             }
    //         });
    //     }
    // }
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
});