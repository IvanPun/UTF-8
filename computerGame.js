$("#imgs>img").click(function(){
    checkImg($(this).attr("src"));
});

var imgs = ["img/jiandao.png","img/shitou.png","img/bu.png"];
function checkImg(imgSrc){
$("#myImg").attr("src",imgSrc);
var id = setInterval(function(){
    var num = parseInt(Math.random()*3);
    $("#computer").attr("src",imgs[num]);
},20);
setTimeout(function(){
    clearInterval(id);
    var my = imgs.indexOf(imgSrc);
    var com = imgs.indexOf($("#computer").attr("src"));
    if(my==0&&com==2 || my==1&&com==0 || my==2&&com==1){
        $("#score").html("恭喜！您赢啦！");
        var mf = parseInt($("#scoreFen span:eq(0)").text())+1;
        mf = mf<10 ? "0"+mf : mf;
        $("#scoreFen span:eq(0)").text(mf);
    }else if(my==0&&com==0 || my==1&&com==1 || my==2&&com==2){
        $("#score").html("平局！再战一轮吧！");
    }else{
        $("#score").html("哈哈哈哈你输啦！！！");
        var cf = parseInt($("#scoreFen span:eq(1)").text())+1;
        cf = cf<10 ? "0"+cf : cf;
        $("#scoreFen span:eq(1)").text(cf);
    }
},500);
}

function back() {
    window.location.replace("index.html");
}