package org.kevin;

import org.kevin.service.SocketService;
import org.kevin.service.SocketService2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("org.kevin.dao")
@ImportResource("classpath:SpringContext.xml")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        applicationContext.getBean(SocketService2.class).start();
        System.out.println(String.format("Server is running ,%s", new Date()));
    }
}
