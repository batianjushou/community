package life.majiang.community.controller;

import life.majiang.community.Service.QuestionService;
import life.majiang.community.controller.dto.PaginationDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action, Model model, HttpServletRequest request,  @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "2")Integer size){
        if("questions".equals(action)){

            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("sectionName","最新回复");
        }
       User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        PaginationDTO pagination =  questionService.getQuestionByUserId(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
