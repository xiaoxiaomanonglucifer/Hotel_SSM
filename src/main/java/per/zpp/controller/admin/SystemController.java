package per.zpp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import per.zpp.utils.SystemConstant;

import javax.servlet.http.HttpSession;

/**
 * @Author 想去外太空的
 * @Date 2023/5/29 17:16
 * @Version 1.0 （版本号）
 */

/**
 * 控制器，跳转后台管理页面
 */
@Controller
@RequestMapping("/admin") //相当于访问的一级目录 http://localhost/admin/login.html
//如果没有上面这个，那么将会是 http://localhost/login.html
public class SystemController {

    /**
     * 去到登陆页面
     *
     * @return
     */
    @RequestMapping("/login.html")//浏览器访问的真正路径 即使是一个jsp页面，但是有这个 显示的也是html
//                   http://localhost/admin/login2.html11
    public String login() {
        return "admin/login";
//             /WEB-INF/jsp/login.jsp-----/login
//          实际上是在/WEB-INF/jsp/admin/login.jsp------"admin/login"
//             路径上http://localhost/admin/login.html但实际上访问的是login.jsp页面
    }//这个是访问的真正的静态资源

    @RequestMapping("/home.html")
    public String home() {
        return "admin/home";
    }


    /**
     * 退出功能
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SystemConstant.LOGINUSER);//方法一清空session
//          session.invalidate(); 方法二清空session
        //重定向到登陆页面
        return "redirect:/admin/login.html";
    }

    /**
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager() {
        return "admin/dept/deptManager";//自动会在后面补一个   deptManager.jsp
//        return到 WEB-INF/jsp/admin/dept/deptManager.jsp这个页面
    }

    /**
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "admin/role/roleManager";
    }

    @RequestMapping("/toEmployeeManager")
    public String toEmployeeManager() {
        return "admin/employee/employeeManager";
    }

    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return "admin/menu/MenuManager";
    }

    @RequestMapping("/toFloorManager")
    public String toFloorManager(){
        return "admin/floor/floorManager";
    }
}
