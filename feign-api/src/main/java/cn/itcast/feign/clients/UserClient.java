package cn.itcast.feign.clients;

import cn.itcast.feign.clients.fallback.UserClientFallBackFactory;
import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName UserClient
 * @Description TODO
 * @Author 18451
 * @Date 2023/6/10 20:26
 * @Version 1.0
 **/
@FeignClient(value = "userservice", fallbackFactory = UserClientFallBackFactory.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
