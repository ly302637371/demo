package com.liyuan.controller;

import com.liyuan.entity.User;
import com.liyuan.service.IUserService;
import com.liyuan.session.UserSession;
import com.liyuan.utils.Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liy on 2017/8/15.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IUserService userService;

    /*登陆页面*/
    @RequestMapping("toLogin")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("toWelcome")
    public String toWelcome(HttpServletRequest request){
        request.setAttribute("user",request.getSession().getAttribute("User"));
        return "welcome";
    }

    /*登陆验证*/
    @RequestMapping("loginVlidate")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response, String userName, String password, String vcode){
        Map<String,Object> data = new HashMap<String,Object>();
//        User user = userService.findUserByCode(userName);
        HttpSession session = request.getSession();
//        String sessionCode = (String) session.getAttribute("vcode");
//        if (user == null || !user.getUserPw().equals(Encrypt.md5(password))){//忽略验证码大小写
//            data.put("status","false");
//            data.put("msg","用户名或密码或验证码错误");
//            return data;
//        }
        session.setAttribute("User", "");
//        UserSession.setSession(session,user.getId(),user.getUserAccent());
//        List<Resources> resources = findUserRes(user.getId());
//        LogUtil.debug(JSONArray.fromObject(resources).toString());
//        session.setAttribute(WebServlet.RES, resources);
        data.put("status","true");
        data.put("msg","登录成功");
        return data;
    }
}
