package cn.itcast.feign.config;

import cn.itcast.feign.clients.fallback.UserClientFallBackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DefaultFeignConfiguration
 * @Description TODO
 * @Author 18451
 * @Date 2023/6/10 20:26
 * @Version 1.0
 **/
@Configuration
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC; // 日志级别为BASIC
    }

    @Bean
    public UserClientFallBackFactory userClientFallBackFactory() {
        return new UserClientFallBackFactory();
    }
}
