package cn.itdan.booksystem.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

@Configuration
public class MyTimer  {

    @Bean
    public Timer timer(){
        return new Timer();
    }
}
