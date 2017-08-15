package com.liyuan.interceptor;

import com.liyuan.utils.ComUtil;
import com.liyuan.utils.LogUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by liy on 2017/8/15.
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        /*后台session控制*/
        String[] noFilters = new String[]{"login/toLogin", "login/vcodeImg", "login/loginVlidate"};
        String uri = request.getRequestURI();
        boolean beFilter = true;
        for (String s : noFilters) {
            if (uri.indexOf(s) != -1) {
                beFilter = false;
                break;
            }
        }
        if (beFilter) {
            Object obj = null;
            String sessionId = request.getParameter("sessionId");
            MySessionContext myc = MySessionContext.getInstance();
            HttpSession session = myc.getSession(sessionId);
            if (session != null) {
                obj = session.getAttribute("User");
            } else {
                obj = request.getSession().getAttribute("User");
            }
            if (null == obj) {
                if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    response.setHeader("sessionstatus", "sessionOut");
                    return false;
                    //response.getWriter().print("sessionOut");
                } else {
                    /*用户未登陆*/
                    LogUtil.debug("user not find!");
                    PrintWriter out = response.getWriter();
                    StringBuilder builder = new StringBuilder();
                    builder.append("<script>");
                    builder.append("alert(\"请您先登录\");");
                    builder.append("window.location.href=\"");
                    builder.append(ComUtil.getPath(request));
                    builder.append("/system/login/toLogin\";</script>");
                    out.write(builder.toString());
                    return false;
                }
            } else {
                return true;
                // 添加日志
                //1.解析URL
                //2.对象赋值
                //3.插入数据库
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
