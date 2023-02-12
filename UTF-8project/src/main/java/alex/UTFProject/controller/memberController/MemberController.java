package alex.UTFProject.controller.memberController;
import alex.UTFProject.entity.Member;
import alex.UTFProject.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "传输成员信息")
@CrossOrigin("*")
@Slf4j
public class MemberController {

    @Autowired
    MemberMapper memberMapper;
    @GetMapping("/getMemberById/{id}")
    @ApiOperation("传输单成员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int")
    })
    public Member getMemberById(@PathVariable("id")int id){
        return memberMapper.getMemberById(id);
    }
    @GetMapping("/changeMemberNameById/{id}/{name}")
    @ApiOperation("修改名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "name",value = "用户名",required = true),
    })
    public String changeMemberNameById(@PathVariable("id")int id,@PathVariable("name")String name){
        return memberMapper.changeMemberNameById(id,name);
    }
    @GetMapping("/changeMemberPicUrlById/{id}/{picUrl}")
    @ApiOperation("修改图片网络地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "picUrl",value = "图片网址",required = true),
    })
    public String changeMemberPicUrlById(@PathVariable("id")int id,@PathVariable("picUrl")String picUrl){
        return memberMapper.changeMemberPicUrlById(id,picUrl);
    }
    @GetMapping("/changeMemberWebById/{id}/{web}")
    @ApiOperation("修改个人网站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "web",value = "网站名",required = true),
    })
    public String changeMemberWebById(@PathVariable("id")int id,@PathVariable("web")String web){
        return memberMapper.changeMemberWebById(id,web);
    }

    @GetMapping("/changeMemberSignatureById/{id}/{signature}")
    @ApiOperation("修改个性签名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "signature",value = "个性签名",required = true),
    })
    public String changeMemberSignatureById(@PathVariable("id")int id,@PathVariable("signature")String signature){
        return memberMapper.changeMemberSignatureById(id,signature);
    }

    @GetMapping("/changeMemberHobbyById/{id}/{hobby}")
    @ApiOperation("修改爱好")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "hobby",value = "爱好",required = true),
    })
    public String changeMemberHobbyById(@PathVariable("id")int id,@PathVariable("hobby")String hobby){
        return memberMapper.changeMemberHobbyById(id,hobby);
    }
    @GetMapping("/changeMemberContact_WayById/{id}/{contact_way}")
    @ApiOperation("修改联系方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "序号",required = true,dataType = "int"),
            @ApiImplicitParam(name = "contact_way",value = "联系方式",required = true),
    })
    public String changeMemberContact_WayById(@PathVariable("id")int id,@PathVariable("contact_way")String contact_way){
        return memberMapper.changeMemberContact_WayById(id,contact_way);
    }
    @GetMapping("/getMemberScoreById/{id}")
    @ApiOperation("查询成员得分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "成员序号（1，2，3）",required = true,dataType = "int"),
    })
    public int getMemberScoreById(@PathVariable("id")int id) {
        return memberMapper.getMemberScoreById(id);
    }

}
