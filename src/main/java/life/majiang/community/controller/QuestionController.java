package life.majiang.community.controller;

import life.majiang.community.Service.QuestionService;
import life.majiang.community.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id, Model model, HttpServletRequest request){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }


}
