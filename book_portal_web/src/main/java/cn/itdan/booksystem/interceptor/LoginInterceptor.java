package cn.itdan.booksystem.interceptor;

import cn.itdan.booksystem.service.AdminService;
import cn.itdan.booksystem.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登入拦截器
 */
@Component
@PropertySource("classpath:client.properties")
public class LoginInterceptor implements HandlerInterceptor {


    @Value("${ADMIN_COOKIE_TOKEN}")
    private String ADMIN_COOKIE_TOKEN;

    @Autowired
    private AdminService adminService;

    /**
     * 前处理，执行handle之前执行的方法
     * 返回true：放行，false：拦截
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public  boolean preHandle(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Object handler){
      //检查cookie中获取token
        String admin_token=CookieUtils.getCookieValue(request,ADMIN_COOKIE_TOKEN);
        Boolean flag= adminService.getAdminByToken(admin_token);
          if(flag){
            return flag;
          }
          try {
              // 通过接口跳转登录页面, 注:重定向后下边的代码还会执行 ;
              response.sendRedirect("/admin");
          }catch (Exception e){
              e.printStackTrace();
          }
          return flag;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
}
