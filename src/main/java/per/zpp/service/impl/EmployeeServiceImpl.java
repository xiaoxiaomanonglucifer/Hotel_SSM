package per.zpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.zpp.domain.Employee;
import per.zpp.mapper.EmployeeMapper;
import per.zpp.service.EmployeeService;
import per.zpp.utils.PasswordUtil;
import per.zpp.utils.SystemConstant;
import per.zpp.utils.UUIDUtils;
import per.zpp.vo.EmployeeVo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 想去外太空的
 * @Date 2023/5/29 17:41
 * @Version 1.0 （版本号）
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    @Override
    public Employee login(String loginName, String loginPwd) {
        //查询是否存在这个员工
        Employee employee = employeeMapper.findEmployeeByLoginName(loginName);
        //判断对象是否为空
        if(employee!=null){
            //将密码加密处理
            String newPassword = PasswordUtil.md5(loginPwd, employee.getSalt(), SystemConstant.PASSWORD_COUNT);
    //比较密码是否一致
            if(employee.getLoginPwd().equals(newPassword)){
                return employee;//登录成功
            }

        }
        //登陆失败
return null;

    }

    @Override
    public int getEmployeeCountByDeptId(Integer deptId) {
        return employeeMapper.getEmployeeCountByDeptId(deptId);
    }

    @Override
    public int deleteDeptById(Integer id) {
        return employeeMapper.deleteDeptById(id);
    }

    @Override
    public int getEmployeeCountByRoleId(Integer roleId) {
      return  employeeMapper.getEmployeeCountByRoleId(roleId);
    }

    @Override
    public List<Employee> findEmployeeList(EmployeeVo employeeVo) {
       return employeeMapper.findEmployeeList(employeeVo);

    }

    @Override
    public int addEmployee(Employee employee) {
        employee.setCreateDate(new Date());
        employee.setSalt(UUIDUtils.randomUUID());
employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        employee.setModifyDate(new Date());
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteById(Integer id) {
        //删除员工角色关系表的数据
        employeeMapper.deleteEmployeeAndRole(id);
        return employeeMapper.deleteById(id);
    }

    @Override
    public int resetPwd(int id) {
        Employee employee = new Employee();
        employee.setSalt(UUIDUtils.randomUUID());
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        employee.setId(id);
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public boolean saveEmployeeRole(String roleIds, Integer empId) {
        //先删除员工关系表内的数据
        try {
            employeeMapper.deleteEmployeeAndRole(empId);
            //再保存员工角色关系
            String idsStr[]=roleIds.split(",");

            for (int i = 0; i < idsStr.length; i++) {
                employeeMapper.addEmployeeRole(idsStr[i],empId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int findEmployeeById(Integer id) {
        return employeeMapper.findEmployeeById(id);
    }


}
