//check login
$(document).ready(function(){
    var name=localStorage.getItem("name");
    var password=localStorage.getItem("password");
    if(name!=""&&password!=""){
        const Url='http://2b87aafb.r2.cpolar.top/userLogin/'+name+'/'+password;
        $.ajax({
            url: Url,
            type: "GET",
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