$(function() {
    var infoUrl = '/albumcategory/getcategorylistbyaid';
    var uploadPhotoUrl = '/admin/image/uploadphoto';
    var albumUrl = '/album/getalbumlist';

    $.ajaxSettings.async = false;
    getAlbum();
    var aid = $('#album').find("option:checked").attr("id");
    getCategory(aid);
    $.ajaxSettings.async = true;

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

    function getCategory(aid) {
        $
            .getJSON(
                infoUrl + '?aid='+ aid,
                function(data) {
                    if (data.ret == 0) {
                        var categorylist = data.data;
                        var optionHtml = '';
                        categorylist
                            .map(function(item, index) {
                                optionHtml += '<option id="'
                                    + item.acid
                                    + '"'
                                    + '>'
                                    + item.name
                                    + '</option>';
                            });
                        $('#category').html(optionHtml);
                    }
                });
    }

    $('#album').change(
        function() {
            var aid = $('#album').find("option:checked").attr("id");
            getCategory(aid);
        }
    );

    $('#submit').click(
        function() {
            $.ajaxSettings.async = false;
            var acid = $('#category').find("option:checked").attr("id");
            var description = $('#description').val();
            var time = $('#time').val();
            var file = $('#file')[0].files[0];
            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('file', file);
            formData.append('acid', acid);
            formData.append('description', description);
            formData.append('time', time);
            $.ajax({
                url : uploadPhotoUrl,
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
            document.getElementById("file").value='';
            document.getElementById("description").value='';
            $.ajaxSettings.async = true;
        });
});