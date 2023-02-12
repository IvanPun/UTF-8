//check login
$(document).ready(function(){
    var name=localStorage.getItem("name");
    var password=localStorage.getItem("password");
    if(name!=""&&password!=""){
        const Url='https://5db55609.r7.cpolar.top/userLogin/'+name+'/'+password;
        $.ajax({
            url: Url,
            type: "GET",
            success:function(result){
                console.log(result);
                if(result=="登录成功"){
                    localStorage.setItem("name", name);
                    localStorage.setItem("password", password);
                  }else{
                    localStorage.setItem("name", "");
                    localStorage.setItem("password", "");
                    location.href=("login.html");
                  }
            }
        })
    }else{
        localStorage.setItem("name", "");
        localStorage.setItem("password", "");
        location.href=("login.html");
    }
})

//logout
$(".logout").click(function(){
    localStorage.setItem("name", "");
    localStorage.setItem("password", "");
    alert("登出成功!");
    location.href=("login.html");
})
