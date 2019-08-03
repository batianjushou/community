package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,creator,gmt_create,gmt_modified,tag) values(#{title},#{description},#{creator},#{gmtCreate},#{gmtModified},#{tag})")
    public void insertQuestion(Question question);
    @Select("select * from question limit #{offsize},#{size}")
    List<Question> getQuestionlist(@Param("offsize") Integer offsize, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

   @Select("select * from question where creator=#{id} limit #{offsize},#{size}")
    List<Question> getQuestionByUserId(@Param("id") Long id, @Param("offsize") Integer offsize, @Param("size") Integer size);
    @Select("select count(1) from question where creator=#{userId} ")
    Integer countByUserId(@Param("userId")Long userId);
    @Select("select * from question where id=#{id} ")
    Question getQuestionById(@Param("id")Integer id);
}
