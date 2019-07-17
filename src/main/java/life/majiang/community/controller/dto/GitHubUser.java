package life.majiang.community.controller.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private String bio;//描述
    private long id;
    private String avatar_url;//头像地址
}
