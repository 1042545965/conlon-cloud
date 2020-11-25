package conlon.cloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 用户模块启动类
 * @Author: conlon
 * @create 2020/2/8 11:24
 * conlon.cloud.user  本身自己的模块
 * conlon.cloud.common  公共需要加载的模块
 * conlon.cloud.api  公共api 模块
 */
@SpringBootApplication(scanBasePackages = {"conlon.cloud.user.*", "conlon.cloud.common.*", "conlon.cloud.api.*"})
@MapperScan({"conlon.cloud.user.dao", "conlon.cloud.common.dao"})
@EnableFeignClients(basePackages = {"conlon.cloud.api.*"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
