$(function() {
    var registerUrl = '/admin/auth/register';
    var loginUrl = '/auth/loginindex';

    getVerify();

    $('#submit').click(
        function() {
            var name = $('#name').val();
            var username = $('#username').val();
            var password = $('#password').val();
            var password2 = $('#password2').val();
            var code = $('#code').val();

            if( password != password2){
                $.toast('两次输入密码不一致！');
                return;
            }

            //创建表单对象，用于传递给后端
            var formData = new FormData();
            formData.append('name', name);
            formData.append('username', username);
            formData.append('password', password);
            formData.append('code', code);

            $.ajax({
                url : registerUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.ret == 0) {
                        $.toast('注册成功！跳转登录');
                        setTimeout(function(){ window.location.href=loginUrl;}, 3000);
                    }else if(data.ret == -10){
                        $.toast('验证码错误！');
                    }else if(data.ret == -7){
                        $.toast('账号已存在！');
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