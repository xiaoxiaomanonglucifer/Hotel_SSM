package per.zpp.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zpp.domain.Employee;
import per.zpp.service.EmployeeService;
import per.zpp.utils.DataGridViewResult;
import per.zpp.utils.SystemConstant;
import per.zpp.vo.EmployeeVo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/5/29 17:47
 * @Version 1.0 （版本号）
 */
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Employee employee = employeeService.login(username, password);
        if (employee != null) {
            //保存当前登录
            session.setAttribute(SystemConstant.LOGINUSER, employee);
            map.put(SystemConstant.SUCCESS, true);
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "账号密码错误，登陆失败");
        }
        return JSON.toJSONString(map);
    }


    /**
     * 员工列表
     *
     * @param employeeVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(EmployeeVo employeeVo) {
        //设置分页信息
        PageHelper.startPage(employeeVo.getPage(), employeeVo.getLimit());
        //调用查询方法
        List<Employee> employeeList = employeeService.findEmployeeList(employeeVo);
        //创建分页对象
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(Employee employee, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        employee.setCreatedBy(loginUser.getId());
        if (employeeService.addEmployee(employee) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "添加成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "添加失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/updateEmployee")
    public String updateEmployee(Employee employee, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //获取当前登录用户
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //设置修改人
        employee.setModifyBy(loginUser.getId());
        if (employeeService.updateEmployee(employee) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "修改成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "修改失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/deleteById")
    public String deleteById(int id) {
        Map<String, Object> map = new HashMap<>();
        if (employeeService.deleteById(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "删除成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "删除失败");
        }
        return JSON.toJSONString(map);
    }
    @RequestMapping("/resetPwd")
    public String resetPwd(int id) {
        Map<String, Object> map = new HashMap<>();
        if (employeeService.resetPwd(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "密码重置成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "密码重置失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/saveEmployeeRole")
    public String saveEmployeeRole(String roleIds,Integer empId){
        Map<String, Object> map = new HashMap<>();
        //调用保存员工角色的方法
        if(employeeService.saveEmployeeRole(roleIds,empId)){
            map.put(SystemConstant.MESSAGE,"角色分配成功");
        }else{
            map.put(SystemConstant.MESSAGE,"角色分配失败");
        }
        System.out.println(map);
        return JSON.toJSONString(map);

    }
    @RequestMapping("/checkHasEmployee")
    public String checkHasEmployee(HttpSession session,int id){
        Map<String, Object> map = new HashMap<>();
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        System.out.println(employee);
        if (employeeService.findEmployeeById(employee.getId())>0&&employee.getId()==id) {
            map.put(SystemConstant.EXIST, true);
            map.put(SystemConstant.MESSAGE, "该职员正在被使用，无法删除");
        } else {
            map.put(SystemConstant.EXIST, false);
        }
        System.out.println(map);
        return JSON.toJSONString(map);


    }

}
