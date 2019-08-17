$(function() {
    var loginUrl = '/auth/login';

    getVerify();

    $('#submit').click(
        function() {
            var username = $('#username').val();
            var password = $('#password').val();
            var code = $('#code').val();
            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('username', username);
            formData.append('password', password);
            formData.append('code', code);

            $.ajax({
                url : loginUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.ret == 0) {
                        $.toast('登录成功！');
                    }else if(data.ret == -10){
                        $.toast('验证码错误！');
                    }else if(data.ret == -6){
                        $.toast('账号或密码错误！');
                    }else if(data.ret == -9){
                        $.toast('请勿重复登录！');
                    }else {
                        $.toast('服务器错误！');
                    }
                    getVerify();
                }
            });
        });

    $('#codeimg').click(
        function() {
            getVerify();
        });
    //获取验证码
    function getVerify(){
        var obj = document.getElementById("imgVerify");
        obj.src = "/code/getVerify?"+Math.random();
    }
});