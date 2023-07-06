package per.zpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.zpp.domain.Role;
import per.zpp.mapper.RoleMapper;
import per.zpp.service.RoleService;
import per.zpp.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/6/1 15:40
 * @Version 1.0 （版本号）
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }


    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public int saveRoleMenu(String ids, Integer roleId) {
        try {
            //删除原有的关系 在进行添加，不然 会一直重复加
            roleMapper.deleteRoleMenu(roleId);
            //将ids拆分成数组
            String []idsStr=ids.split(",");
            //循环调用  保存菜单角色的方法
            for (int i = 0; i < idsStr.length; i++) {
                roleMapper.addRoleMenu(roleId,idsStr[i]);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;

    }


    @Override
    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    @Override
    public List<Integer> findEmployeeRoleByEmployeeId(Integer id) {
        return roleMapper.findEmployeeRoleByEmployeeId(id);
    }
}
