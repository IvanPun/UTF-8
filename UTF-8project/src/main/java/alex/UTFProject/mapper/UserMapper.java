package alex.UTFProject.mapper;
import alex.UTFProject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface UserMapper {
    List<User> getAllUser();

    void insertUser(@Param("name")String name, @Param("password")String password, @Param("email") String email);
    void deleteUser(@Param("name")String name, @Param("password")String password);
    void changeUserPassword(@Param("name")String name, @Param("email")String email,@Param("newPassword")String newPassword);
    String getAccessByName(@Param("name")String name);

    void userPlay(@Param("name")String name,@Param("method")int method);
    void memberPlay(@Param("id")int id,@Param("method")int method);
    int getScoreByName(@Param("name")String name);

}
