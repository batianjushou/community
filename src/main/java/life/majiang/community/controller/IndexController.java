package life.majiang.community.controller;

import life.majiang.community.Service.QuestionService;
import life.majiang.community.controller.dto.PaginationDTO;
import life.majiang.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name="size",defaultValue = "2")Integer size
                        ){
        PaginationDTO pagination = questionService.getQuestionlist(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
