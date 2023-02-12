package alex.UTFProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import alex.UTFProject.entity.Member;
@Mapper
public interface MemberMapper {
    String changeMemberNameById(@Param("id") int id,@Param("name") String name);
    String changeMemberSignatureById(@Param("id")int id,@Param("signature") String signature);
    String changeMemberContact_WayById(@Param("id")int id,@Param("contact_way") String contact_way);
    String changeMemberHobbyById(@Param("id")int id,@Param("hobby") String hobby);
    String changeMemberPicUrlById(@Param("id")int id,@Param("picUrl") String picUrl);
    String changeMemberWebById(@Param("id")int id,@Param("web") String web);
    Member getMemberById(@Param("id")int id);
    int getMemberScoreById(@Param("id")int id);
}
