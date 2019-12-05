package cn.itdan.booksystem.controller;

import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class AdminController {

    private Logger logger=LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 跳转至登入界面
     * @param request
     * @return
     */
    @RequestMapping(value = {"/admin/index","/admin","/admin/login"})
    public String toIndex(HttpServletRequest request) {

        logger.info("跳转至登入界面:/admin");
        //自动登入
        if(StringUtils.isNotBlank(adminService.autoLogin(request))){
            String token=adminService.autoLogin(request);
             return "redirect:/admin/main?admin_token="+token;
        }
        return "admin/login";
    }

    /**
     * admin登入操作
     */
    @RequestMapping(value = "/api/loginCheck",method = RequestMethod.POST)
    @ResponseBody
    public Reslut  loginCheck(HttpServletRequest request, HttpServletResponse response) {
       logger.info("admin登入操作:/api/loginCheck");
        Integer id=Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("password");
         Reslut reslut= adminService.login(request,response,id,passwd);
        return reslut;
    }

    /**
     * admin退出操作
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/admin/logout"})
    public String logout(HttpServletRequest request,HttpServletResponse response) {
      //  request.getSession().removeAttribute("admin");
        adminService.logout(request,response);
        return "redirect:/admin";
    }

    /**
     * 博客系统后台主页展示
     * @param request
     * @return
     */
    @GetMapping(value = "/admin/main")
    public ModelAndView toMain(HttpServletRequest request){
        logger.info("博客系统后台主页:/admin/main");

        ModelAndView modelAndView=new ModelAndView("admin/main");
        String admin_token=request.getParameter("admin_token");
        HashMap<String,Object> map= adminService.toMain(request,admin_token);

        if(map.get("err")!=null){
            if(map.get("err").toString().equals("error")){
                return new ModelAndView("admin/login");
            }
        }

        modelAndView.addObject("clientIp",map.get("clientIp"));
        modelAndView.addObject("date",map.get("dates"));
        modelAndView.addObject("hostIp",map.get("hostIp"));
        modelAndView.addObject("hostPort",map.get("hostPort"));
        modelAndView.addObject("articleCount",map.get("articleCount"));
        modelAndView.addObject("commentCount",map.get("commentCount"));
        modelAndView.addObject("loginLog",map.get("adminLoginLog"));
        modelAndView.addObject("loginNum",map.get("loginNum"));
        return modelAndView;
    }


}
