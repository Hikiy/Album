$(function() {
    var photoManagementUrl = '/admin/photosmanagement?acid=';
    var albumListUrl = '/album/getalbumlist';
    var albumCategoryListUrl = '/albumcategory/getcategorylistbyaid?aid=';
    var albumInfoUrl = '/admin/albumedit?aid=';

    $.ajaxSettings.async = false;
    getList();
    $.ajaxSettings.async = true;

    function getList() {
        $
            .getJSON(
                albumListUrl,
                function(data) {
                    if (data.ret == 0) {
                        var albumlist = data.data;
                        var albumHtml = '';
                        albumlist
                            .map(function(item, index) {
                                albumHtml += '<div class="content-block-title"><span>' + item.name + '</span><a href="' + albumInfoUrl + item.aid + '">  编辑</a><a href="javascript:void(0)" onclick="deletebyaid('+ item.aid +')">  删除</a></div>\n'
                                + getCategoryList(item.aid);
                            });
                        $('#content').html(albumHtml);
                    }
                });
    }
    function getCategoryList(aid) {
        var albumCategoryHtml = '';
        $
            .getJSON(
                albumCategoryListUrl + aid,
                function(data) {
                    if (data.ret == 0) {
                        var albumCategorylist = data.data;
                        albumCategorylist
                            .map(function(item, index) {
                                albumCategoryHtml += '<div class="card">\n' +
                                    '        <div class="card-content">\n' +
                                    '            <div class="list-block">\n' +
                                    '                <ul>\n' +
                                    '                    <li>\n' +
                                    '                        <a href="' + photoManagementUrl + item.acid  + '" class="item-link item-content">\n' +
                                    '                            <div class="item-media"><i class="icon icon-f7"></i></div>\n' +
                                    '                            <div class="item-inner">\n' +
                                    '                                <div class="item-title">' + item.name + '</div>\n' +
                                    '                            </div>\n' +
                                    '                        </a>\n' +
                                    '                    </li>\n' +
                                    '                </ul>\n' +
                                    '            </div>\n' +
                                    '        </div>\n' +
                                    '    </div>';
                            });
                    }
                });
        return albumCategoryHtml;
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