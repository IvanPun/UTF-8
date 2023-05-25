function play(){
    var type = +prompt("请选择游戏类型：1为系统游戏，2为积分游戏");
    if(type==1) {
        window.location.replace("computerGame.html");
    } else if(type==2) {
        var name=localStorage.getItem("name");
        $.ajax({
            method: "get",
            url: "http://124.71.207.55:8081/getScoreByName/"+name,
            success:function(res){
                var yourScore = res;
                console.log(yourScore);
                alert("您的积分为"+ yourScore);
            }
        })
        var id = +prompt("请选择您的对手：\n输入1，2，或3，分别对应潘卓言，唐雪婷，谢瑞阳");
        if(id==1||id==2||id==3){
            $.ajax({
                method: "get",
                url: "http://124.71.207.55:8081/getMemberScoreById/"+id,
                success:function(res){
                    var memberScore = res;
                    console.log("对方的积分为",memberScore);
                    alert("对方的积分为："+memberScore);
                }
            })
            var number = +prompt("请输入石头，剪刀，或布，分别对应1，2，3");
            $.ajax({
                method: "get",
                url: "http://124.71.207.55:8081/userPlay/"+name+"/"+id+"/"+number,
                success:function(res){
                    console.log(res);
                    alert(res);
                }
            })
        }
        else {
            alert("请输入1，2，或3");
        }
    } else {
        alert("请输入1或2");
    }
}
