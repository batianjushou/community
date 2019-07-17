package life.majiang.community.model;

import lombok.Data;

@Data   //自动生成get/set/tostring方法
public class User {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;//头像地址


}