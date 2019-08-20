$(function() {
    var updateUrl = '/admin/auth/updatepassword';

    var uid = getQueryVariable("uid");
    if( uid == null || uid == '' || uid < 1){
        uid = 0;
    }

    $('#submit').click(
        function() {
            var name = $('#name').val();
            var oldpassword = $('#oldpassword').val();
            var newpassword = $('#newpassword').val();
            var newpassword2 = $('#newpassword2').val();

            if( newpassword != newpassword2){
                $.toast('两次输入密码不一致！');
                return;
            }

            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('uid', uid);
            formData.append('oldpassword', oldpassword);
            formData.append('newpassword', newpassword);

            $.ajax({
                url : updateUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.ret == 0) {
                        $.toast('修改成功！');
                    }else {
                        $.toast('检查是否输错旧密码！');
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