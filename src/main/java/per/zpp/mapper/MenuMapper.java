package per.zpp.mapper;

import per.zpp.domain.Employee;
import per.zpp.domain.Menu;
import per.zpp.vo.MenuVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 11:16
 * @Version 1.0 （版本号）
 */
public interface MenuMapper {
    //查询所有菜单列表
    List<Menu> findMenuList();

    /**
     * 根据角色id查询所拥有的菜单id集合
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdListByRoleId(int roleId);

    List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds);

    List<Menu> findMenuListByPage(MenuVo menuVo);

    int addMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenuById(int id);

    int getMenuCountByMenuId(Integer id);

    List<Menu> findMenuListByEmployeeId(Integer EmployeeId);
}
