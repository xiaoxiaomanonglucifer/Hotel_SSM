package per.zpp.vo;

import org.springframework.format.annotation.DateTimeFormat;
import per.zpp.domain.Employee;

import java.util.Date;

/**
 * @Author 想去外太空的
 * @Date 2023/6/3 15:38
 * @Version 1.0 （版本号）
 */
public class EmployeeVo extends Employee {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
