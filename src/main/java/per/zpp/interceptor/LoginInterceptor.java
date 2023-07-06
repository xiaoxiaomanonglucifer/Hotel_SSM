package per.zpp.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import per.zpp.utils.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 10:15
 * @Version 1.0 （版本号）
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session是否为空
        if(request.getSession().getAttribute(SystemConstant.LOGINUSER)==null){
            //没有登陆的话
            response.sendRedirect(request.getContextPath()+"/admin/login.html");
            return false;

        }
        return true;
    }
}
