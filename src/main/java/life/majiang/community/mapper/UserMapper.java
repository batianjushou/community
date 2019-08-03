package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface UserMapper {
    @Insert("insert  into user (name,account_Id,token,gmt_Create,gmt_Modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    public void insertUser(User user);
    //通过token拿到user
    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);
    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") long id);
    @Select("select * from user where account_id=#{accountId}")
    User findUserByAccountId(@Param("accountId") String accountId);
    @Update("update user set name=#{name},gmt_Modified=#{gmtModified},avatar_url=#{avatarUrl},token=#{token} where id=#{id}")
    void updateUser(User user);
}
