package cn.itdan.booksystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * book启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.itdan.booksystem.mapper")
public class BlogProvider {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BlogProvider.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}