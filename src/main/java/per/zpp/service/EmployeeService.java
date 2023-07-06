package per.zpp.service;

import per.zpp.domain.Employee;
import per.zpp.vo.EmployeeVo;

import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/5/29 17:40
 * @Version 1.0 （版本号）
 */
public interface EmployeeService {

    /**
     * 员工登录的账号和密码
     * @param loginName
     * @param loginPwd
     * @return
     */
    Employee login(String loginName,String loginPwd);

    int getEmployeeCountByDeptId(Integer deptId);

    int deleteDeptById(Integer id);


    int getEmployeeCountByRoleId(Integer roleId);

    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);
    int deleteById(Integer id);

    int resetPwd(int id);

    boolean saveEmployeeRole(String roleIds, Integer empId);

    int findEmployeeById(Integer id);
}
