package per.zpp.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import per.zpp.domain.Role;
import per.zpp.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/6/1 15:34
 * @Version 1.0 （版本号）
 */
public interface RoleMapper {

    List<Role> findRoleList(RoleVo roleVo);

    int addRole(Role role);

    int update(Role role);

    int deleteById(Integer id);


    void deleteRoleMenu(Integer roleId);

    void addRoleMenu(@Param("roleId") Integer roleId,@Param("menuId") String menuId);


    List<Map<String,Object>> findRoleListByMap();

    /**
     * 根据员工id查询 该员工拥有的角色列表
     * @param id
     * @return
     */
    List<Integer> findEmployeeRoleByEmployeeId(Integer id);
}
