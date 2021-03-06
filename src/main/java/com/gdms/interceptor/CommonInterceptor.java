package com.gdms.interceptor;

import com.gdms.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器，用于处理后台请求的权限或者异常处理
 *
 * @author bigben
 * @date 2018.2.6
 */
public class CommonInterceptor implements HandlerInterceptor{

    /**
     * 执行Handler方法之前执行
     * 用于身份认证、身份授权
     * 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=(User)request.getSession().getAttribute("user");
        if(user.getUsername()==null||user.getUsername().equals("")){ // 如果为空 拦截  跳转登录
            System.out.println("登录拦截");
            response.sendRedirect(request.getContextPath()+"/view/login");
            return false;
        }else{
            // 如果用户 ID 不为空
            // 利用用户的id查找用户的 groupid
            // 获取前台传来的 limitID
            // 通过 limitid and groupid 查找 up_g_group_limit 表
            // 如果可以找到返回true，否则返回false
            return true;
        }
    }

    /**
     * 进入Handler方法之后，返回modelAndView之前执行
     * 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里
     * 传到视图，也可以在这里统一指定视图
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("TestInterceptor......postHandle");

    }

    /**
     * 执行Handler完成执行此方法
     * 应用场景：统一异常处理，统一日志处理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("22222222");
    }
}
