$(function() {
    var addUrl = '/admin/albumcategory/addalbumcategory';
    var albumUrl = '/album/getalbumlist';
    var getAlbumCategoryUrl = '/albumcategory/getcategorybyacid';
    var updateUrl = '/admin/albumcategory/updatealbumcategorybyacid';

    var isEdit = false;
    var acid = getQueryVariable("acid");
    $.ajaxSettings.async = false;
    getAlbum();
    $.ajaxSettings.async = true;
    if( acid != null && acid != "" && acid > 0){
        isEdit = true;
        $('#code').attr('disabled',true);
        getAlbumCategory(acid);
        $('#album').attr('disabled',true);
    }

    function getAlbum() {
        $
            .getJSON(
                albumUrl,
                function(data) {
                    if (data.ret == 0) {
                            var albumlist = data.data;
                            var optionHtml = '';
                            albumlist
                                .map(function(item, index) {
                                    optionHtml += '<option id="'
                                        + item.aid
                                        + '"'
                                        + '>'
                                        + item.name
                                        + '</option>';
                                });
                        $('#album').html(optionHtml);
                    }
                });
    }

    function getAlbumCategory(acid) {
        $
            .getJSON(
                getAlbumCategoryUrl + "?acid=" + acid,
                function(data) {
                    if (data.ret == 0) {
                        var albumcategory = data.data;
                        document.getElementById("name").value=albumcategory.name;
                        document.getElementById("code").value=albumcategory.code;
                        document.getElementById("priority").value=albumcategory.priority;
                        var theaid = albumcategory.aid;
                        var album_options = document.getElementById("album").options;
                        var length = album_options.length;
                        var i = 0;
                        for (i; i<length; i++){
                            if (album_options[i].id == theaid)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
                            {
                                album_options[i].selected = true;
                            }
                        }
                    }
                });
    }

    $('#submit').click(
        function() {
            var theUrl = addUrl;
            if( isEdit ){
                theUrl = updateUrl;
            }
            var aid = $('#album').find("option:checked").attr("id");
            var name = $('#name').val();
            var code = $('#code').val();
            var priority = $('#priority').val();
            var file = $('#file')[0].files[0];
            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('file', file);
            formData.append('aid', aid);
            formData.append('name', name);
            formData.append('code', code);
            formData.append('priority', priority);

            $.ajax({
                url : theUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.ret == 0) {
                        $.toast('上传成功！');
                    } else {
                        $.toast('上传失败！');
                    }
                }
            });
        });

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