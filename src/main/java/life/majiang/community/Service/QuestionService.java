package life.majiang.community.Service;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import life.majiang.community.controller.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDTO> getQuestionlist() {
        List<Question> questionsList = questionMapper.getQuestionlist();
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for (Question question:questionsList) {
            long id = question.getCreator();
            User user=userMapper.getUserById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            //工具类，快速拷贝
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setTitle("热部署3");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;

    }
}
