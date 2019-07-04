package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello( String name,Model model){
        System.out.println("1111");
        model.addAttribute("name",name);
        return "index";
    }

}
