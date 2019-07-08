package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

        @Insert("insert  into user (name,account_Id,token,gmt_Create,gmt_Modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
        public void insertUser(User user);
        //通过token拿到user
        @Select("select * from user where token=#{token}")
        User findUserByToken(@Param("token") String token);
}
