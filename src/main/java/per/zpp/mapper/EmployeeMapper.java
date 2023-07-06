package per.zpp.mapper;

import org.apache.ibatis.annotations.Param;
import per.zpp.domain.Employee;
import per.zpp.vo.EmployeeVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/29 17:03
 * @Version 1.0 （版本号）
 */
public interface EmployeeMapper {

    /**
     * 根据员工姓名查询员工信息
     * @param loginName
     * @return
     */
        Employee findEmployeeByLoginName(String loginName);


        /*
        根据部门编号查询员工编号
         */
        int getEmployeeCountByDeptId(Integer deptId);

    int deleteDeptById(Integer id);

    /**
     * 根据角色编号查询员工的数量
     * @param roleId
     * @return
     */
    int getEmployeeCountByRoleId(Integer roleId);


    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    //添加员工
    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteById(Integer id);

    void deleteEmployeeAndRole(Integer id);

    void addEmployeeRole(@Param("roleId") String roleId, @Param("empId") Integer empId);

    int findEmployeeById(Integer id);
}
