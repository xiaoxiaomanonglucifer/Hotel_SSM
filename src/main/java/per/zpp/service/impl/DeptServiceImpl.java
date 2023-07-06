package per.zpp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.zpp.domain.Dept;
import per.zpp.mapper.DeptMapper;
import per.zpp.service.DeptService;
import per.zpp.vo.DeptVo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:37
 * @Version 1.0 （版本号）
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    public List<Dept> findDeptListByPage(DeptVo deptVo) {
        return deptMapper.findDeptListByPage(deptVo);
    }



    @Override
    public int addDept(Dept dept) {
        //保存创建事件
        dept.setCreateDate(new Date());
    return deptMapper.addDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateDept(dept);
    }

    @Override
    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }
}
