package per.zpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.zpp.domain.Menu;
import per.zpp.mapper.MenuMapper;
import per.zpp.service.MenuService;
import per.zpp.vo.MenuVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 11:18
 * @Version 1.0 （版本号）
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findMenuList() {
        return menuMapper.findMenuList();
    }

    @Override
    public List<Integer> findMenuIdListByRoleId(int roleId) {
        return menuMapper.findMenuIdListByRoleId(roleId);
    }

    @Override
    public List<Menu> findMenuByMenuId(List<Integer> currentRoleMenuIds) {
        return menuMapper.findMenuByMenuId(currentRoleMenuIds);
    }

    @Override
    public List<Menu> findMenuListByPage(MenuVo menuVo) {
        return menuMapper.findMenuListByPage(menuVo);
    }

    @Override
    public int addMenu(Menu menu) {
        //如果没有选择父级菜单，默认设置父级菜单为0
        if(menu.getPid()==null){
            menu.setPid(0);
        }
        return menuMapper.addMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(int id) {
        return menuMapper.deleteMenuById(id);
    }

    @Override
    public int getMenuCountByMenuId(Integer id) {
        return menuMapper.getMenuCountByMenuId(id);
    }

    @Override
    public List<Menu> findMenuListByEmployeeId(Integer employeeId) {
        return menuMapper.findMenuListByEmployeeId(employeeId);
    }
}
