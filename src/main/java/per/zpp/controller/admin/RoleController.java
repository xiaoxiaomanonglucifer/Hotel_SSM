package per.zpp.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zpp.domain.Menu;
import per.zpp.domain.Role;
import per.zpp.service.EmployeeService;
import per.zpp.service.MenuService;
import per.zpp.service.RoleService;
import per.zpp.utils.DataGridViewResult;
import per.zpp.utils.SystemConstant;
import per.zpp.utils.TreeNode;
import per.zpp.vo.RoleVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/6/1 15:41
 * @Version 1.0 （版本号）
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MenuService menuService;

    /**
     * 展示菜单列表 并且做到自动分页
     *
     * @param roleVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo) {
        //设置分页
        PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        //调用查询角色列表的方法
        List<Role> roleList = roleService.findRoleList(roleVo);
        //创建分页信息对象
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加方法
     *
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public String addRole(Role role) {
        Map<String, Object> map = new HashMap<>();

        if (roleService.addRole(role) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "添加成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "添加失败");

        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改角色方法
     *
     * @param role
     * @return
     */
    @RequestMapping("/updateRole")
    public String updateRole(Role role) {
        Map<String, Object> map = new HashMap<>();
        if (roleService.update(role) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "修改成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "修改失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/checkRoleHasEmployee")
    public String checkRoleHasEmployee(Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (employeeService.getEmployeeCountByRoleId(id) > 0) {
            map.put(SystemConstant.EXIST, true);
            map.put(SystemConstant.MESSAGE, "该角色正在被使用，无法删除");
        } else {
            map.put(SystemConstant.EXIST, false);
        }
//        System.out.println(map);
        return JSON.toJSONString(map);
    }

    /**
     * 删除角色方法
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoleById")
    public String deleteRoleById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        if (roleService.deleteById(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "删除成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "删除失败");
        }
        return JSON.toJSONString(map);
    }
//
//    @RequestMapping("/initMenuTree")
//    public DataGridViewResult initMenuTree(){
//        //调用查询菜单列表的方法
//        List<Menu> menuList = menuService.findMenuList();
//        //根据角色id查询当前角色所拥有的菜单id
//
//        //创建集合保存树节点 循环遍历集合
//        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
//        for (Menu menu : menuList) {
//            //是否展开
//            Boolean spread= (menu.getSpread()==null || menu.getSpread()==1? true :false);
//            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
//
//        }
//        return new DataGridViewResult(treeNodes);
//    }

    /**
     * 初始化菜单树
     *
     * @return
     */
    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(int roleId) {
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
        //根据角色id查询当前角色所拥有的菜单id
        List<Integer> currentRoleMenuIds = menuService.findMenuIdListByRoleId(roleId);
        //定义集合  保存菜单信息
        List<Menu> currentMenus = new ArrayList<>();
        if (currentRoleMenuIds != null && currentRoleMenuIds.size() > 0) {
            //根据菜单id查询该菜单的信息
            currentMenus = menuService.findMenuByMenuId(currentRoleMenuIds);
        }
        //构建treeNode节点菜单
        List<TreeNode> treeNodes = new ArrayList<>();
        //循环遍历所有菜单
        for (Menu m1 : menuList) {
            //定义变量，保存信息是否被选中
            String checkArr = "0";//不选中
            //内层循环遍历当前角色拥有的权限菜单
            //循环遍历的原因 比较两个集合 的数据是否相同 ，有相同的表示当前角色 拥有这个权限、
            for (Menu m2 : currentMenus) {
                if (m1.getId() == m2.getId()) {
                    checkArr = "1";
                    break;
                }
            }
            Boolean spread = (m1.getSpread() == null || m1.getSpread() == 1);
            //将选中的集合添加到节点集合中
            treeNodes.add(new TreeNode(m1.getId(), m1.getPid(), m1.getTitle(), spread, checkArr));

        }
        return new DataGridViewResult(treeNodes);

    }

    /**
     * 保存角色菜单
     *
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(String ids, Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        if (roleService.saveRoleMenu(ids, roleId) > 0) {
            map.put(SystemConstant.MESSAGE, "菜单分配成功");
        } else {
            map.put(SystemConstant.MESSAGE, "菜单分配失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据员工id查拥有的角色列表
     *
     * @param id
     * @return
     */
    @RequestMapping("/initRoleListByEmpId")
    public DataGridViewResult initRoleListByEmpId(int id) {
//查询 所有角色列表的方法
        List<Map<String, Object>> roleList = roleService.findRoleListByMap();
        //根据员工id查询
        List<Integer> roleIds = roleService.findEmployeeRoleByEmployeeId(id);
        //循环比较两个集合中的 角色id值是否相等 相等则选中该角色
        for (Map<String, Object> map : roleList) {
            //定义变量 标识是否选中
            boolean flag=false;
            //获取每一个角色id
            int rid = (int) map.get("id");
            //内层循环遍历该员工拥有的角色列表
            for (Integer roleId : roleIds) {
                //判断两个集合中是否存在相同的角色id
                if(rid ==roleId){
                    flag=true;
                    break;
                }
            }
            map.put("LAY_CHECKED",flag);
        }

        return new DataGridViewResult(Long.valueOf(roleList.size()),roleList);
    }

}
