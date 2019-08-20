$(function() {
    var authListUrl = '/admin/auth/getuserslist';
    var updateUrl = '/admin/authupdatepassword?uid=';

    $.ajaxSettings.async = false;
    getList();
    $.ajaxSettings.async = true;

    function getList() {
        $
            .getJSON(
                authListUrl,
                function(data) {
                    if (data.ret == 0) {
                        var authList = data.data;
                        var authHtml = '';
                        authList
                            .map(function(item, index) {
                                authHtml += '<div class="card"><div class="card-content"><div class="list-block media-list"><ul><div class="item-inner">' +
                                    '<div class="item-title-row">' +
                                    '<div class="item-title">' + item.name + '</div>' +
                                    '</div>' +
                                    '<div class="item-subtitle">' + item.username + '</div>' +
                                    '<div class="card-footer no-border">' +
                                    '     <a href="' + updateUrl + item.uid + '"' +
                                    '         class="item-link list-button">修改密码</a>' +
                                    '     <a href="javascript:void(0)" class="link" onclick="deletebyuid('+ item.uid +')">删除</a>' +
                                    '</div>' +
                                    '</div></ul></div></div></div>';
                            });
                        $('#content').html(authHtml);
                    }
                });
    }
});