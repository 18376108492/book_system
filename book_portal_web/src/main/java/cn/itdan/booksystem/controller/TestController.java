package cn.itdan.booksystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    private Logger logger=LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    public String TestController(){
        logger.info("访问index");
        return "index";
    }

    @GetMapping("/test01")
    @ResponseBody
    public String Test01(){
        return "hello";
    }

    @RequestMapping("/test02")
    public ModelAndView test02 (){
        ModelAndView view=new ModelAndView( "about");
        return view;
    }
}
