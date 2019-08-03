package life.majiang.community.Service;

import life.majiang.community.controller.dto.PaginationDTO;
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
    public PaginationDTO getQuestionlist(Integer page, Integer size) {
        Integer totalPage = questionMapper.count();
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        if(page<1){
            page=1;
        }

        if(totalPage%size==0){
            totalPage=totalPage/size;
        }else{
            totalPage = totalPage/size+1;
        }
        if(totalPage!=0&&page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page,size);

        Integer offsize = size*(page-1);
        List<Question> questionsList = questionMapper.getQuestionlist(offsize,size);
        for (Question question:questionsList) {
            long id = question.getCreator();
            User user=userMapper.getUserById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            //工具类，快速拷贝
            BeanUtils.copyProperties(question,questionDTO);
            //questionDTO.setTitle("热部署3");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;

    }

    public PaginationDTO getQuestionByUserId(Long UserId,Integer page, Integer size) {
        System.out.println("page1:"+page);
        Integer totalPage = questionMapper.countByUserId(UserId);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        if(page<1){
            page=1;
        }
        System.out.println("tatalPage:"+totalPage);
        if(totalPage%size==0){
            totalPage=totalPage/size;
        }else{
            totalPage = totalPage/size+1;
        }
        if(totalPage!=0&&page>totalPage){
            page=totalPage;
        }
        System.out.println("totalPage1:"+totalPage);
        paginationDTO.setPagination(totalPage,page,size);
        Integer offsize = size*(page-1);
        //System.out.println("offsize:"+offsize);
        List<Question> questionsList = questionMapper.getQuestionByUserId(UserId,offsize,size);
        for (Question question:questionsList) {
            long id = question.getCreator();
            User user=userMapper.getUserById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            //工具类，快速拷贝
            BeanUtils.copyProperties(question,questionDTO);
            //questionDTO.setTitle("热部署3");
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.getQuestionById(id);
        QuestionDTO questionDTO =new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.getUserById(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
