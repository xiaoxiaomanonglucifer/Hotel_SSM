package per.zpp.vo;

import per.zpp.domain.Role;

/**
 * @Author 想去外太空的
 * @Date 2023/6/1 15:33
 * @Version 1.0 （版本号）
 */
public class RoleVo extends Role {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
