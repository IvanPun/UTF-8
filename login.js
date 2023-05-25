//login
$("#login").click(function(){
    var name=$("#logname").val();
    var password=$('#logpass').val();
    if (name!="" && password!="") {
        const Url='http://124.71.207.55:8081/userLogin/'+name+'/'+password;
        $.ajax({
            url: Url,
            type: "POST",
            success:function(result){
                console.log(result);
                if(result=="登录成功"){
                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                    alert("welcome!");
                    location.href=("index.html");
                  }else if(result=="你的密码错误"){
                    $("#warning").html("<h5>你的密码错误</h5>");
                  }else if(result=="该用户不存在"){
                    $("#warning").html("<h5>该用户不存在</h5>");
                  }
            }
        })
    }else if(name==''&&password==''){
        $("#warning").html("<h5>用戶名和密码都不能空缺！</h5>");
      }else if(name==''){
        $("#warning").html("<h5>用戶名不能空缺！</h5>");
      }else{
        $("#warning").html("<h5>密码不能空缺！</h5>");
      }
})

//check login
$(document).ready(function(){
    var name=localStorage.getItem("name");
    var password=localStorage.getItem("password");
    if(name!=""&&password!=""){
        const Url='http://124.71.207.55:8081/userLogin/'+name+'/'+password;
        $.ajax({
            url: Url,
            type: "POST",
            success:function(result){
                console.log(result);
                if(result=="登录成功"){
                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                    location.href=("index.html");
                  }else{
                    localStorage.setItem("name", "");
                    localStorage.setItem("password", "");
                  }
            }
        })
    }else{
        localStorage.setItem("name", "");
        localStorage.setItem("password", "");
    }
})


//register
$("#register").click(function(){
    var name=$("#regname").val();
    var email=$("#regemail").val();
    var password=$('#regpass').val();
    console.log(name);
    console.log(email);
    console.log(password);
    if (name!="" && password!=""&&email!="") {
        const Url='http://124.71.207.55:8081/InsertUser/'+name+'/'+password+'/'+email;
        $.ajax({
            url: Url,
            type: "POST",
            success:function(result){
                console.log(result);
                if(result=="用户名重复"){
                    $("#register-warning").html("<h5>用户名重复</h5>");
                }else if(result=="邮箱重复"){
                    $("#register-warning").html("<h5>邮箱重复</h5>");
                }else{
                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                    alert("welcome!");
                    location.href=("index.html");
                }
                
            }
        })
    }else if(name==''){
        $("#register-warning").html("<h5>用戶名不能空缺！</h5>");
      }else if(email==''){
        $("#register-warning").html("<h5>邮箱不能空缺！</h5>");
      }else{
        $("#register-warning").html("<h5>密码不能空缺！</h5>");
      }
})
