// 刷新图片
function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", changeUrl(src));
}

//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function changeUrl(url) {
    var timestamp = (new Date()).valueOf();
    var index = url.indexOf("?");
    if (index > 0) {
        url = url.substring(0, url.indexOf("?"));
    }
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}

//提交登录
function sub(path){
    var userName =$("#userName").val();
    var passwd = $("#passwd").val();
    var vcode = $("#v_code").val();
    if (userName == "" || passwd == "" || vcode == ""){
        alert("您没有填写用户名或密码或验证码哟...");
        $("#userName").val("");
        $("#passwd").val("");
        $("#v_code").val("");
        changeImg();
        return;
    }
    //ajax 提交
    $.ajax({
        url:"login/loginVlidate",
        type:"POST",
        async: true,
        data:{"userName":userName,"password":passwd,"vcode":vcode},
        dataType:'json',
        success:function(data){
            if(data.status == "false"){
                alert(data.msg);
                $("#userName").val("");
                $("#passwd").val("");
                $("#v_code").val("");
                changeImg();
                return;
            }
            if (data.status == "true"){
                location.href=path+"login/toWelcome";
            }

        },
        error:function (error) {
            console.log(error);
        }
    });
}