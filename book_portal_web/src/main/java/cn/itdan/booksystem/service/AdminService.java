package cn.itdan.booksystem.service;

import cn.itdan.booksystem.api.ApiAdminService;
import cn.itdan.booksystem.pojo.Admin;
import cn.itdan.booksystem.pojo.AdminLoginLog;
import cn.itdan.booksystem.pojo.Reslut;
import cn.itdan.booksystem.utils.CookieUtils;
import cn.itdan.booksystem.utils.JsonUtils;
import cn.itdan.booksystem.utils.RedisUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@PropertySource("classpath:client.properties")
public class AdminService {

    @Reference(version = "${dubbo.service.version}")
    private ApiAdminService apiAdminService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${ADMIN_EXPIRE}")
    private Integer ADMIN_EXPIRE;

    @Value("${ADMIN_COOKIE_ID}")
    private String ADMIN_COOKIE_ID;

    @Value("${ADMIN_COOKIE_PASSWROD}")
    private String ADMIN_COOKIE_PASSWROD;

    @Value("${ADMIN_COOKIE_TOKEN}")
    private String ADMIN_COOKIE_TOKEN;

    @Value("${ADMIN_COOKIE_TIME}")
    private Integer ADMIN_COOKIE_TIME;

    /**
     * 登入操作
     * @param request
     * @param response
     * @param id
     * @param password
     * @return
     */
    public Reslut login(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer id,
                        String password){

        //1.根据ID获取用户的密码
       Admin admin= apiAdminService.getById(id);
        //2.对照密码是否正确
       if(null==admin){
           return  Reslut.build(400,"用户名或密码错误");
       }
        //先取出密码加密后再校验
        String newPassword=DigestUtils.md5DigestAsHex(password.getBytes());
       if(!admin.getPassword().equals(newPassword)){
           return  Reslut.build(400,"用户名或密码错误");
       }

        //生成token
        String admin_token=UUID.randomUUID().toString();
        //把用户信息写入redis中，key: token value值为用户提示
        //将密码设为null
        admin.setPassword(null);
        redisUtil.set("SESSION"+admin_token,JsonUtils.objectToJson(admin));
        //设置admin过期时间
        redisUtil.expire("SESSION"+admin_token,ADMIN_EXPIRE);

        //保存登入信息
        saveAdminLog(request,id);

        //将admin存入cookie,以便于下次自动登入操作
        CookieUtils.setCookie(request,response,ADMIN_COOKIE_ID,id.toString(),ADMIN_COOKIE_TIME);
        CookieUtils.setCookie(request,response,ADMIN_COOKIE_PASSWROD,newPassword,ADMIN_COOKIE_TIME);
        CookieUtils.setCookie(request,response,ADMIN_COOKIE_TOKEN,admin_token,ADMIN_COOKIE_TIME);
       return Reslut.ok(admin_token);
    }

    /**
     * 保存的登入信息
     * @param adminId
     */
    public void  saveAdminLog(HttpServletRequest request,Integer adminId){
        AdminLoginLog adminLoginLog=new AdminLoginLog();
        //添加信息
        adminLoginLog.setAdminId(adminId);
        adminLoginLog.setIp(request.getRemoteAddr());
        apiAdminService.addLoginLog(adminLoginLog);
    }


    /**
     * 使用Cookie实现自动登入
     * @param request
     * @return
     */
    public String  autoLogin(HttpServletRequest request){
         //从cookie中获取上次登入的信息
        String cookie_id=CookieUtils.getCookieValue(request,ADMIN_COOKIE_ID);
        String cookie_password=CookieUtils.getCookieValue(request,ADMIN_COOKIE_PASSWROD);
        String cookie_token=CookieUtils.getCookieValue(request,ADMIN_COOKIE_TOKEN);
        //检测cookie中获取的值是都为空
        if(StringUtils.isBlank(cookie_id) || StringUtils.isBlank(cookie_password)|| StringUtils.isBlank(cookie_token) ){
             return null;
        }
        //保存登入信息
        Integer id=Integer.parseInt(cookie_id);
        saveAdminLog(request,id);
        return cookie_token;
    }

    /**
     * 退出登入
     */
    public void logout(HttpServletRequest request,HttpServletResponse response){
        //退出登入就将cookie中的值清掉
        CookieUtils.deleteCookie(request,response,ADMIN_COOKIE_ID);
        CookieUtils.deleteCookie(request,response,ADMIN_COOKIE_PASSWROD);
        CookieUtils.deleteCookie(request,response,ADMIN_COOKIE_TOKEN);
   }

    /**
     * 跳转至后台主页
     * @param request
     * @return
     */
    public HashMap<String,Object> toMain(HttpServletRequest request,String admin_token) {

        HashMap<String, Object> map = new HashMap<>();
        //检测传入的token是否有值
        if (StringUtils.isBlank(admin_token)) {
            //查看cookie中是否有值
            String cookie_token = CookieUtils.getCookieValue(request, ADMIN_COOKIE_TOKEN);
            if (StringUtils.isNotBlank(cookie_token)) {
                admin_token = cookie_token;
            } else {
                map.put("err", "error");
                return map;
            }
        }

        String clientIp = request.getRemoteAddr();    //获取客户端IP，如：127.0.0.1
        map.put("clientIp", clientIp);
        //获取本机地址
        String hostIp = request.getLocalAddr();
        map.put("hostIp", hostIp);
        //获取本机端口
        int hostPort = request.getLocalPort();
        map.put("hostPort", hostPort);
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String dates = df.format(date);
        map.put("dates", dates);
        int articleCount = articleService.countAllNum();
        map.put("articleCount", articleCount);
        int commentCount = commentService.countAllNum();
        map.put("commentCount", commentCount);

        //获取redis中的用户对象
        String token = redisUtil.get("SESSION" + admin_token).toString();
        Admin admin = JsonUtils.jsonToPojo(token, Admin.class);
        int loginNum = apiAdminService.selectCountByAdminId(admin.getId());
        map.put("loginNum", loginNum);

        AdminLoginLog adminLoginLog = null;
        if (apiAdminService.selectRencent(admin.getId()) != null && apiAdminService.selectRencent(admin.getId()).size() == 2) {
            List<AdminLoginLog> adminLoginLogs = apiAdminService.selectRencent(admin.getId());
            adminLoginLog = adminLoginLogs.get(1);
        }
        map.put("adminLoginLog", adminLoginLog);
        return map;
    }
    /**
     * 通过token获取
     * @param admin_token
     * @return
     */
    public Boolean getAdminByToken(String admin_token) {
        //查看cookie中是否有值
         if(StringUtils.isBlank(admin_token)){
             return false;
         }
        //获取redis中的用户对象
        String token= redisUtil.get("SESSION"+admin_token).toString();
        Admin admin= JsonUtils.jsonToPojo(token,Admin.class);
        if (null!=admin){
            //更新admin过期时间
            redisUtil.expire("SESSION"+admin_token,ADMIN_EXPIRE);
            return true;
        }
        return false;
    }

}
