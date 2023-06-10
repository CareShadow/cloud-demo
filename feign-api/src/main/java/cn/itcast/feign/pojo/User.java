package cn.itcast.feign.pojo;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author 18451
 * @Date 2023/6/10 20:27
 * @Version 1.0
 **/
@Data
public class User {
    private Long id;
    private String username;
    private String address;
}
