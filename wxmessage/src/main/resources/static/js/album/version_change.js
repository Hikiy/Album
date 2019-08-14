var browser={
    versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        //移动设备浏览器版本信息
        return {
            //IE内核
            trident: u.indexOf('Trident') > -1,
            //opera内核
            presto: u.indexOf('Presto') > -1,
            //苹果、谷歌内核
            webKit: u.indexOf('AppleWebKit') > -1,
            //火狐内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
            //是否为移动终端
            mobile: !!u.match(/AppleWebKit.*Mobile.*/),
            //ios终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
            //android终端或者uc浏览器
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
            //是否为iPhone或者QQHD浏览器
            iPhone: u.indexOf('iPhone') > -1 ,
            //是否iPad
            iPad: u.indexOf('iPad') > -1,
            //是否web应该程序，没有头部与底部
            webApp: u.indexOf('Safari') == -1
        };
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
}

if(browser.versions.mobile || browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad){    cssMobileChange();
}else{
    cssPCChange();
}

function cssMobileChange(){
    var link = document.getElementsByTagName('link')[0];
    link.setAttribute('href','../../css/style1.css');
}
function cssPCChange(){
    var link = document.getElementsByTagName('link')[0];
    link.setAttribute('href','../../css/style2.css');
}