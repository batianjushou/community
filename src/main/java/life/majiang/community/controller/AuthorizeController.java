package life.majiang.community.controller;

import life.majiang.community.controller.dto.AccessTokenDTO;
import life.majiang.community.controller.dto.GitHubUser;
import life.majiang.community.controller.provider.GithubProvider;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;
    @RequestMapping("/callback")
    public String callBack(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser githubUser = githubProvider.getUser(accessToken);
        //System.out.println(gitHubUser.getName()+":"+user.getBio()+":"+user.getId());
        if(githubUser!=null){
            System.out.println("登录成功，写session  coocie");
        /*    HttpSession session = request.getSession();
            session.setAttribute("user",githubUser);*/
            //写入数据库
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            System.out.println("登录失败");
            return "redirect:/";
        }
    }
}
