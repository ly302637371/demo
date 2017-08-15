package com.liyuan.interceptor;

import com.liyuan.utils.LogUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 全局静态类 可用于初始化web时缓存数据。
 * 存放全局变量，系统参数初始化
 * 获取spring context 中的bean
 */
@Component
public class WebServlet implements ApplicationContextAware {
    private static ApplicationContext appContext;
    private static ServletContext servletContext;
    private static Map configMap = new HashMap();
    private static String password = "";
    /*资源关键词*/
    public static final String RES = "res";
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        appContext = context;
     }
    /**
     * 注入servlet context
     */
    public static void init(ServletContext context) {
        servletContext = context;
        Properties props = new Properties();
        try {
            InputStream is = new BufferedInputStream(servletContext.getResourceAsStream("/WEB-INF/classes/config.properties"));
            props.load(is);
            Iterator it = props.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                configMap.put(key, props.get(key));
            }
            WebServlet.password=(configMap.get("sys.password").toString());
        } catch (Exception ex) {
            LogUtil.error(ex.getMessage());
        }
    }

    public static String getPassword() {
        return password;
    }

//    /**
//     * 获取菜单下的按钮资源
//     * @param resId
//     * @return
//     */
//    public static List<Resources> getNextRes(String resId, HttpServletRequest request) {
//        List<Resources> resources = (List<Resources>) request.getSession().getAttribute(WebServlet.RES);
//        List<Resources> ress = new ArrayList<Resources>();
//        if (resources != null && resources.size() > 0) {
//            for (Resources res : resources) {
//                if (res.getrParentId().equals(resId)) {
//                    ress.add(res);
//                }
//            }
//        }
//        return ress;
//    }
}
