package conlon.cloud.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"conlon.cloud.auth.*", "conlon.cloud.common.*"})
@EnableFeignClients(basePackages = "conlon.cloud.api.connect")
@MapperScan({"conlon.cloud.auth.dao" , "conlon.cloud.common.dao"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
