package per.zpp.vo;

import per.zpp.domain.Dept;

/**
 * @Author 想去外太空的
 * @Date 2023/5/30 15:20
 * @Version 1.0 （版本号）
 */
public class DeptVo extends Dept {
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
