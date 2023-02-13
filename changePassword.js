//chnage
$("#change").click(function(){
    var name=$("#logname").val();
    var password=$('#logpass').val();
    var email=$('#logemail').val();
    if (name!="" && password!=""&& email!="") {
        const Url='https://3a60cb8f.r8.cpolar.top/changeUserPassword/'+name+'/'+email+'/'+password;
        $.ajax({
            url: Url,
            type: "GET",
            success:function(result){
                console.log(result);
                if(result=="用户名或邮箱不正确"){
                    $("#warning").html("<h5>用户名或邮箱不正确</h5>");
                }else if(result=="修改用户密码成功"){
                    alert("修改用户密码成功!");
                    location.href=("login.html");
                }
            }
        })
    }else if(name==''){
        $("#warning").html("<h5>用戶名不能空缺！</h5>");
      }else if(email==''){
        $("#warning").html("<h5>邮箱不能空缺！</h5>");
      }else{
        $("#warning").html("<h5>新密码不能空缺！</h5>");
      }
})
