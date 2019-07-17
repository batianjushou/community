package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController {

    @Autowired
    QuestionMapper questionMapper;


}
