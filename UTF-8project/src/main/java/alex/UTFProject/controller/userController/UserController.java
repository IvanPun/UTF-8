package alex.UTFProject.controller.userController;
import alex.UTFProject.entity.User;
import alex.UTFProject.mapper.MemberMapper;
import alex.UTFProject.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Random;

@RestController
@Api(tags = "传输用户信息")
@CrossOrigin("*")
@Slf4j
public class UserController {
    @Autowired
    UserMapper userMapper;
    MemberMapper memberMapper;
    @GetMapping("getAccessByName/{name}")
    @ApiOperation("验证管理员权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
    })
    public String getAccessByName(@PathVariable("name")String name){
        return userMapper.getAccessByName(name);
    }
    @GetMapping("/InsertUser/{name}/{password}/{email}")
    @ApiOperation("插入用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true)
    })
    public String insertUser(@PathVariable("name")String name,@PathVariable("password")String password,@PathVariable("email")String email){
        try {
            //连接数据库的驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utf?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", "root", "1928374655");
            PreparedStatement ps = conn.prepareStatement("select * from user where name=?");
            PreparedStatement ps2 = conn.prepareStatement("select * from user where email=?");
            ps2.setString(1,email);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            if(rs.next()){
                return "用户名重复";
            }
            if(rs2.next()){
                return "邮箱重复";
            }
            conn.close();
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        userMapper.insertUser(name,password,email);
        return "注册成功！";
    }
    @GetMapping("/getAllUser")
    @ApiOperation("传输用户信息")
    public List<User> getUserByNameAndPassword(){
        return userMapper.getAllUser();
    }

    @GetMapping("/deleteUser/{name}/{password}")
    @ApiOperation("删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    public String deleteUser(@PathVariable("name")String name,@PathVariable("password")String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utf?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", "root", "1928374655");
            PreparedStatement ps = conn.prepareStatement("select * from user where name=?");
            PreparedStatement ps2 = conn.prepareStatement("select * from user where password=?");
            ps2.setString(1,password);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            if(!rs.next()||!rs2.next()){
                return "用户名或密码不正确";
            }
        }
        catch(Exception e){
            return "wrong";
        }
        userMapper.deleteUser(name,password);
        return "删除用户成功";
    }
    @GetMapping("/changeUserPassword/{name}/{email}/{newPassword}")
    @ApiOperation("用户忘记密码/修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "newPassword",value = "新密码",required = true)
    })
    public String changeUserPassword(@PathVariable("name")String name,@PathVariable("email")String email,@PathVariable("newPassword")String newPassword){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utf?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", "root", "1928374655");
            PreparedStatement ps = conn.prepareStatement("select id from user where name=? and email=?");
            ps.setString(1,name);
            ps.setString(2,email);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                return "用户名或邮箱不正确";
            }
        }
        catch(Exception e){
            return "wrong";
        }
        userMapper.changeUserPassword(name,email,newPassword);
        return "修改用户密码成功";
    }
    @GetMapping("/userLogin/{name}/{password}")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    public String userLogin(@PathVariable("name")String name,@PathVariable("password")String password){
        try {
            //连接数据库的驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/utf?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", "root", "1928374655");
            PreparedStatement ps = conn.prepareStatement("select * from user where name=?");
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            //查询的用户下面没有内容，返回用户不存在
            if(!rs.next()){
                return "该用户不存在";
            }
            //刚才的rs中已经读取到了关于用户的所有信息，我们将他通过数据库中的字段名称获取后，通过User类的构造方法储存
            User user = new User(
                    rs.getString("name"),
                    rs.getString("password"));
            //如果数据库中存储对应的密码与你输入的密码不匹配，返回密码错误，把不为空的值放在前可以有效避免空指针异常，
            if(!(user.getPassword()).equals(password)){
                return "你的密码错误";
            }
            conn.close();
            ps.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "登录成功";
    }

    @GetMapping("/userPlay/{name}/{id}/{method}")
    @ApiOperation("游玩石头剪刀布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
            @ApiImplicitParam(name = "id",value = "挑战对象id",required = true,dataType = "int"),
            @ApiImplicitParam(name = "method",value = "使用石头，剪刀，或布，分别对应1，2，3",required = true,dataType = "int")
    })
    public String userPlay(@PathVariable("name")String name,@PathVariable("id")int id,@PathVariable("method")int method) {
            Random numList = new Random();
            int a=numList.nextInt(3)+1;
            int result=0;
            if(a==method)result=0;
            else if(method-a==1||(a==3&&method==1))result=-1;
            else result=1;
            String method2;
            if(a==1)method2="石头";
            else if (a==2) {
                method2="剪刀";
            } else{
                method2="布";
            }
            if(id%2==1)method2="他出了" + method2;
            else method2="她出了" + method2;
        if(result==0){
                return method2 + "!\n平局！积分不变！";
            }
        userMapper.userPlay(name,result);
        userMapper.memberPlay(id,result);
            if(result==1)return method2 + "!\n你赢了！积分增加！";
            return method2 + "!\n哦不，你输了，积分减少了，再试一次吧？";
    }
    @GetMapping("/getScoreByName/{name}")
    @ApiOperation("查询用户得分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
    })
    public int getScoreByName(@PathVariable("name")String name) {
        return userMapper.getScoreByName(name);
    }

}
