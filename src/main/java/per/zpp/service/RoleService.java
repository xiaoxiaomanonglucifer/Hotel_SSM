package per.zpp.service;

import per.zpp.domain.Role;
import per.zpp.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/6/1 15:40
 * @Version 1.0 （版本号）
 */
public interface RoleService {

    List<Role> findRoleList(RoleVo roleVo);


    int addRole(Role role);

    int update(Role role);

    int deleteById(Integer id);

    int saveRoleMenu(String ids, Integer roleId);

    List<Map<String,Object>> findRoleListByMap();

    List<Integer> findEmployeeRoleByEmployeeId(Integer id);
}
