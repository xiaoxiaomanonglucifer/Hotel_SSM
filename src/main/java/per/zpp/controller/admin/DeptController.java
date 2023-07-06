package per.zpp.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zpp.domain.Dept;
import per.zpp.service.DeptService;
import per.zpp.service.EmployeeService;
import per.zpp.utils.DataGridViewResult;
import per.zpp.utils.SystemConstant;
import per.zpp.vo.DeptVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:39
 * @Version 1.0 （版本号）
 */

@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询部门列表
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo) {
        //设置分页信息
        PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
        //调用分页查询的方法
        List<Dept> deptList = deptService.findDeptListByPage(deptVo);
        System.out.println(deptList.toString());
        //创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
        //因为layui需要我们返回这么样子的东西，所有用到了DataGridViewResult
    }

    /**
     * 添加部门操作
     *
     * @param dept
     * @return
     */
    @RequestMapping("/addDept")
    public String addDept(Dept dept) {
        Map<String, Object> map = new HashMap<>();
        if (deptService.addDept(dept) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "添加成功");
        } else {

            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改部门
     * @param dept
     */
    @RequestMapping("/editDept")
    public String updateDept(Dept dept){
        Map<String, Object> map = new HashMap<>();
        if (deptService.updateDept(dept) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "修改成功");
        } else {

            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "修改失败");
        }
        return JSON.toJSONString(map);

    }

    /**
     * 检查该部门下是否存在员工信息
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasEmployee")
    public String checkDeptHasEmployee(Integer id){
//        参数名要和$.get("admin/dept/checkDeptHasEmployee",{"id":data.id},function (resp)  给的数据中的参数名一致   {"id":data.id}
        Map<String, Object> map = new HashMap<>();
        //调用方法
        if(employeeService.getEmployeeCountByDeptId(id)>0){
            map.put(SystemConstant.EXIST, true);
            map.put(SystemConstant.MESSAGE, "该部门存在员工信息，无法删除");
        }else{
            map.put(SystemConstant.EXIST, false);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/deleteDeptById")
    public String deleteDeptById(Integer id){
        Map<String, Object> map = new HashMap<>();
        if(employeeService.deleteDeptById(id)>0){
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "删除成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "删除失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/deptList")
    public String deptList(){
        List<Dept> deptList = deptService.findDeptList();
//        System.out.println(deptList);
        return JSON.toJSONString(deptService.findDeptList());
    }
}
