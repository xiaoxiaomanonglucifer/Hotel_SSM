package per.zpp.mapper;

import per.zpp.domain.Dept;
import per.zpp.vo.DeptVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:19
 * @Version 1.0 （版本号）
 */
public interface DeptMapper {

    /**
     * 查询部门列表
     * @param deptvo
     * @return
     */
    List<Dept> findDeptListByPage(DeptVo deptvo);

        int addDept(Dept dept);

        int updateDept(Dept dept);


    List<Dept> findDeptList();
}
