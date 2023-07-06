package per.zpp.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zpp.domain.Employee;
import per.zpp.domain.Menu;
import per.zpp.service.MenuService;
import per.zpp.utils.*;
import per.zpp.vo.MenuVo;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 11:19
 * @Version 1.0 （版本号）
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    //注入menuService
    @Autowired
    private MenuService menuService;

    /**
     * 加载首页左侧的菜单的导航
     *
     * @return
     */
    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session) {
        //获取当前登录员工
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //调用查询查询所有菜单的方法
//        List<Menu> menuList = menuService.findMenuList();

        /**
         * 应该根据当前角色动态 显示菜单列表
         */
        List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());

        //创建Map集合，保存MenuInfo菜单信息
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        //创建Map集合，保存homeInfo信息
        Map<String, Object> homeInfo = new LinkedHashMap<String, Object>();
        //创建Map集合，保存logoInfo信息
        Map<String, Object> logoInfo = new LinkedHashMap<String, Object>();
        //创建集合，保存菜单的关系
        List<MenuNode> menuInfo = new ArrayList<>();
        //循环遍历菜单列表，目的是创建菜单之间的层级关系
        for (Menu m : menuList) {
            //创建一个菜单节点对象
            MenuNode menuNode = new MenuNode();
            menuNode.setHref(m.getHref());//链接
            menuNode.setIcon(m.getIcon());//菜单的图标
            menuNode.setId(m.getId());//菜单编号
            menuNode.setPid(m.getPid());//父级菜单编号
            menuNode.setSpread(m.getSpread());//是否展开
            menuNode.setTarget(m.getTarget());//打开方式
            menuNode.setTitle(m.getTitle());//菜单名称
            //将对象添加到集合
            menuInfo.add(menuNode);
        }
        //下面的各种INfo要和 init.json里面的数据格式 对的上
        //保存HomeInfo信息
        homeInfo.put("title", "首页");
        homeInfo.put("href", "/admin");
        //保存logoInfo信息
        logoInfo.put("title", "DGUT酒店管理系统");
        logoInfo.put("image", "/statics/layui/images/logo.png");
        logoInfo.put("href", "/admin/home.html");//首页地址

//将菜单信息 添加到menuInfo集合中
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
        map.put("homeInfo", homeInfo);
        map.put("logoInfo", logoInfo);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //调用查询所有菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();
        //循环遍历菜单列表集合
        for (Menu menu : menuList) {
            //判断当前菜单是否展开
            boolean spread = menu.getSpread() == null || menu.getSpread() == 1;
            //将菜单信息保存到treeNode集合中
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread));
        }

        return new DataGridViewResult(treeNodes);

    }

    @RequestMapping("/list")
    public DataGridViewResult list(MenuVo menuVo) {
        //设置分页信息
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuListByPage(menuVo);
        //创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    @RequestMapping("/addMenu")
    public String addMenu(Menu menu){
        Map<String, Object> map = new HashMap<>();
        if(menuService.addMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }


    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        Map<String, Object> map = new HashMap<>();
        if(menuService.updateMenu(menu)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/deleteMenuById")
    public String deleteMenuById(Integer id){
        Map<String, Object> map = new HashMap<>();
        if(menuService.deleteMenuById(id)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/checkMenuHasChild")
    public String checkMenuHasChild(Integer id){
        Map<String, Object> map = new HashMap<>();
        if(menuService.getMenuCountByMenuId(id)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该菜单下有子菜单，无法删除");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }
}
