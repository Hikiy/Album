$(function() {
    var albumListUrl = '/album/getalbumlist';
    var albumIndexUrl = '/album/index?aid=';

    getList();

    function getList() {
        $
            .getJSON(
                albumListUrl,
                function(data) {
                    if (data.ret == 0) {
                        var albumList = data.data;
                        var albumListHtml = '';
                        albumList
                            .map(function(item, index) {
                                albumListHtml += '<div class="card">\n' +
                                    '        <div class="card-content">\n' +
                                    '            <div class="list-block">\n' +
                                    '                <ul>\n' +
                                    '                    <li>\n' +
                                    '                        <a href="' + albumIndexUrl + item.aid + '" target="_blank" class="item-link item-content">\n' +
                                    '                            <div class="item-media"><i class="icon icon-f7"></i></div>\n' +
                                    '                            <div class="item-inner">\n' +
                                    '                                <div class="item-title" >' + item.name + '</div>\n' +
                                    '                            </div>\n' +
                                    '                        </a>\n' +
                                    '                    </li>\n' +
                                    '                </ul>\n' +
                                    '            </div>\n' +
                                    '        </div>\n' +
                                    '    </div>';
                            });
                        $('#content').html(albumListHtml);
                    }
                });
    }
});