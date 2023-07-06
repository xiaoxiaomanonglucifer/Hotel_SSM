package per.zpp.service;

import per.zpp.domain.Dept;
import per.zpp.vo.DeptVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:37
 * @Version 1.0 （版本号）
 */
public interface DeptService {

    List<Dept> findDeptListByPage(DeptVo deptvo);


    int addDept(Dept dept);

    int updateDept(Dept dept);

    List<Dept> findDeptList();
}
