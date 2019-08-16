$(function() {
    var albumUrl = '/album/getalbumbyaid';
    var addAlbumUrl = '/admin/album/addalbum';
    var updateAlbumUrl = '/admin/album/updatealbumbyaid'
    var isEdit = false;

    var aid = getQueryVariable("aid");
    if( aid != null && aid != '' && aid > 0 ){
        isEdit = true;
        $('#code').attr('disabled',true);
        getAlbum(aid);
    }

    function getAlbum(aid) {
        $
            .getJSON(
                albumUrl + "?aid=" + aid,
                function(data) {
                    if (data.ret == 0) {
                        var album = data.data;
                        document.getElementById("name").value=album.name;
                        document.getElementById("code").value=album.code;
                    }
                });
    }

    //提交按钮事件对商品编辑和商品添加采取不同操作
    $('#submit').click(
        function() {
            var name = $('#name').val();
            var code = $('#code').val();
            var file = $('#file')[0].files[0];
            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('file', file);
            formData.append('name', name);
            formData.append('code', code);

            var theUrl = addAlbumUrl;
            if( isEdit ){
                theUrl = updateAlbumUrl + "?aid=" + aid;
            }
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